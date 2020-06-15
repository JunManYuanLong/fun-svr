package com.okay.family.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.testuser.EditUserBean;
import com.okay.family.common.bean.testuser.SearchUserBean;
import com.okay.family.common.bean.testuser.TestUserBean;
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

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
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
    public TestUserBean findUser(int id) {
        return testUserMapper.findUser(id);
    }

    @Override
    public int updateUser(EditUserBean bean) {
        int i = testUserMapper.updateUser(bean);
        return i;
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public int updateUserStatus(TestUserBean bean) {
        UserUtil.updateUserStatus(bean);
        int i = testUserMapper.updateUserStatus(bean);
        return i;
    }

    @Override
    public int checkUser(TestUserBean bean) {
        boolean b = UserUtil.checkUserLoginStatus(bean);
        if (b) {
//            int i = testUserMapper.updateUserStatus(bean);
            return 1;
        } else {
            return 0;
        }
    }


    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public String getCertificate(int id) {
        TestUserBean user = testUserMapper.findUser(id);
//        if (user.getStatus() != UserState.OK.getCode())
//            UserStatusException.fail();
        //todo:处理单独查询certificate
        return user.getDec();

        /**
         * 暂时采取直接读取数据看的方式,如果多用例运行,采取用例集方法缓存机制
         */
//        UserCertificate.certificates.compute(id, (key, value) ->
//        {
//            if (value == null) {
//                TestUserBean user = testUserMapper.findUser(id);
//                if (user.getStatus() == UserState.OK.getCode())
//                    return user;
//                else UserStatusException.fail();
//            }
//            return value;
//        });
//
//        return UserCertificate.certificates.get(id).getCertificate();


    }

    @Override
    public int delUsr(DelBean bean) {
        int i = testUserMapper.delUser(bean);
        return i;
    }


}
