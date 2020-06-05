package com.okay.family.service.impl;

import com.okay.family.common.basedata.UserCertificate;
import com.okay.family.common.bean.testuser.TestUserBean;
import com.okay.family.common.code.CommonCode;
import com.okay.family.common.code.TestUserCode;
import com.okay.family.common.enums.UserState;
import com.okay.family.common.exception.UserStatusException;
import com.okay.family.fun.base.interfaces.ReturnCode;
import com.okay.family.mapper.TestUserMapper;
import com.okay.family.service.ITestUserService;
import com.okay.family.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestUserServiceImpl implements ITestUserService {

    private static Logger logger = LoggerFactory.getLogger(TestUserServiceImpl.class);

    @Autowired
    TestUserMapper testUserMapper;

    @Override
    public List<TestUserBean> findUsers(int owner) {
        return testUserMapper.findUsers(owner);
    }

    @Override
    public int add(TestUserBean user) {
        int add = testUserMapper.add(user);
        if (add == 1) updateUser(user);
        return add;
    }

    @Override
    public TestUserBean findUser(int id) {
        return testUserMapper.findUser(id);
    }

    @Override
    public int updateUser(TestUserBean bean) {
        UserUtil.updateUserStatus(bean);
        int i = testUserMapper.updateUser(bean);
        if (i == 1) {
            UserCertificate.certificates.put(bean.getId(), bean);
        }
        return i;
    }


    @Override
    public String getCertificate(int id) {
        TestUserBean user = testUserMapper.findUser(id);
        if (user.getStatus() != UserState.OK.getcode())
            UserStatusException.fail();
        return user.getCertificate();

        /**
         * 暂时采取直接读取数据看的方式,如果多用例运行,采取用例集方法缓存机制
         */
//        UserCertificate.certificates.compute(id, (key, value) ->
//        {
//            if (value == null) {
//                TestUserBean user = testUserMapper.findUser(id);
//                if (user.getStatus() == UserState.OK.getcode())
//                    return user;
//                else UserStatusException.fail();
//            }
//            return value;
//        });
//
//        return UserCertificate.certificates.get(id).getCertificate();


    }

    @Override
    public ReturnCode checkUser(TestUserBean bean) {
        boolean b = UserUtil.checkUserLoginStatus(bean);
        if (b) {
            int i = testUserMapper.updateUser(bean);
            return i == 1 ? CommonCode.SUCCESS : TestUserCode.UPDATE_USER_FAIL;
        } else {
            return TestUserCode.CHECK_FAIL;
        }
    }


}
