package com.okay.family.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.basedata.UserLock;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.testuser.TestUserCheckBean;
import com.okay.family.common.bean.testuser.request.EditUserBean;
import com.okay.family.common.bean.testuser.request.SearchUserBean;
import com.okay.family.common.bean.testuser.response.TestUserBean;
import com.okay.family.common.code.TestUserCode;
import com.okay.family.common.enums.UserState;
import com.okay.family.common.exception.UserStatusException;
import com.okay.family.fun.utils.Time;
import com.okay.family.mapper.TestUserMapper;
import com.okay.family.service.ITestUserService;
import com.okay.family.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestUserServiceImpl implements ITestUserService {

    private static Logger logger = LoggerFactory.getLogger(TestUserServiceImpl.class);

    TestUserMapper testUserMapper;

    @Autowired
    public TestUserServiceImpl(TestUserMapper testUserMapper) {
        this.testUserMapper = testUserMapper;
    }

    @Override
    public PageInfo<TestUserBean> findUsers(SearchUserBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        List<TestUserBean> users = testUserMapper.findUsers(bean);
        PageInfo<TestUserBean> result = new PageInfo(users);
        return result;
    }

    @Override
    public int addUser(EditUserBean user) {
        int add = testUserMapper.addUser(user);
        if (add == 1) {
            TestUserCheckBean userCheckBean = new TestUserCheckBean();
            userCheckBean.copyFrom(user);
            int i = updateUserStatus(userCheckBean);
            if (i != 1 || StringUtils.isEmpty(userCheckBean.getCertificate())) {
                UserStatusException.fail(TestUserCode.CHECK_FAIL.getDesc());
            }
        } else {
            UserStatusException.fail(TestUserCode.ADD_USER_FAIL.getDesc());
        }
        return user.getId();
    }

    @Override
    public TestUserCheckBean findUser(int id) {
        return testUserMapper.findUser(id);
    }

    @Override
    public int updateUser(EditUserBean bean) {
        TestUserCheckBean checkBean = new TestUserCheckBean();
        checkBean.copyFrom(bean);
        updateUserStatus(checkBean);
        int i = testUserMapper.updateUser(bean);
        return i;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public int updateUserStatus(TestUserCheckBean bean) {
        UserUtil.updateUserStatus(bean);
        int i = testUserMapper.updateUserStatus(bean);
        return i;
    }

    @Override
    public boolean checkUser(TestUserCheckBean bean) {
        return UserUtil.checkUserLoginStatus(bean);
    }

    @Override
    public boolean checkUser(int id) {
        TestUserCheckBean user = testUserMapper.findUser(id);
        return checkUser(user);
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public TestUserCheckBean getCertificate(int id) {
        Object o = UserLock.get(id);
        synchronized (o) {
            TestUserCheckBean user = testUserMapper.findUser(id);
            if (user == null) UserStatusException.fail("用户不存在,ID:" + id);
            String create_time = user.getCreate_time();
            long create = Time.getTimestamp(create_time);
            long now = Time.getTimeStamp();
            if (now - create < OkayConstant.CERTIFICATE_TIMEOUT && user.getStatus() == UserState.OK.getCode())
                return user;
            boolean b = UserUtil.checkUserLoginStatus(user);
            if (!b) {
                UserUtil.updateUserStatus(user);
            }
            testUserMapper.updateUserStatus(user);
            return user;
        }
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public String getCertificate(int id, ConcurrentHashMap<Integer, String> map) {
        Object o = UserLock.get(id);
        synchronized (o) {
            if (map.contains(id)) return map.get(id);
            TestUserCheckBean user = testUserMapper.findUser(id);
            if (user == null) UserStatusException.fail("用户不存在,ID:" + id);
            String create_time = user.getCreate_time();
            long create = Time.getTimestamp(create_time);
            long now = Time.getTimeStamp();
            if (now - create < OkayConstant.CERTIFICATE_TIMEOUT && user.getStatus() == UserState.OK.getCode()) {
                map.put(id, user.getCertificate());
                return user.getCertificate();
            }
            boolean b = UserUtil.checkUserLoginStatus(user);
            if (!b) {
                UserUtil.updateUserStatus(user);
                if (user.getStatus() != UserState.OK.getCode()) UserStatusException.fail();
            }
            map.put(id, user.getCertificate());
            testUserMapper.updateUserStatus(user);
            return user.getCertificate();
        }
    }

    @Override
    public int delUesr(DelBean bean) {
        int i = testUserMapper.delUser(bean);
        return i;
    }


}
