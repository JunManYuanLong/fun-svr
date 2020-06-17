package com.okay.family.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.basedata.UserCertificate;
import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.testuser.request.EditUserBean;
import com.okay.family.common.bean.testuser.request.SearchUserBean;
import com.okay.family.common.bean.testuser.response.TestUserBean;
import com.okay.family.common.bean.testuser.TestUserCheckBean;
import com.okay.family.common.enums.UserState;
import com.okay.family.common.exception.UserStatusException;
import com.okay.family.fun.utils.Time;
import com.okay.family.mapper.TestUserMapper;
import com.okay.family.service.ITestUserService;
import com.okay.family.utils.UserUtil;
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
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
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
        PageInfo result = new PageInfo(users);
        return result;
    }

    @Override
    public int addUser(EditUserBean user) {
        int add = testUserMapper.addUser(user);
//        TestUserBean userBean = new TestUserBean();
//        user.copyTo(userBean);
//        if (add == 1) checkUser(userBean);
        return user.getId();
    }

    @Override
    public TestUserCheckBean findUser(int id) {
        return testUserMapper.findUser(id);
    }

    @Override
    public int updateUser(EditUserBean bean) {
        int i = testUserMapper.updateUser(bean);
        return i;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public int updateUserStatus(TestUserCheckBean bean) {
        //todo:正式上线增加验证
//        UserUtil.updateUserStatus(bean);
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
        Object o = UserCertificate.get(id);
        synchronized (o) {
            TestUserCheckBean user = testUserMapper.findUser(id);
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
    public String getCertificate(int id, ConcurrentHashMap<Integer, String> map) {
        Object o = UserCertificate.get(id);
        synchronized (o) {
            if (map.contains(id)) return map.get(id);
            TestUserCheckBean user = testUserMapper.findUser(id);
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
                if (user.getStatus()!=UserState.OK.getCode()) UserStatusException.fail();
            }
            map.put(id, user.getCertificate());
            testUserMapper.updateUserStatus(user);
            return user.getCertificate();
        }
    }

    @Override
    public int delUsr(DelBean bean) {
        int i = testUserMapper.delUser(bean);
        return i;
    }


}
