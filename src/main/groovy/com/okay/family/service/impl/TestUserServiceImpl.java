package com.okay.family.service.impl;

import com.okay.family.common.code.CommonCode;
import com.okay.family.common.code.TestUserCode;
import com.okay.family.common.bean.testuser.TestUserBean;
import com.okay.family.fun.base.interfaces.ReturnCode;
import com.okay.family.mapper.TestUserMapper;
import com.okay.family.service.ITestUserService;
import com.okay.family.utils.CheckUserStatusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestUserServiceImpl implements ITestUserService {

    @Autowired
    TestUserMapper testUserMapper;

    @Override
    public List<TestUserBean> findUsers(int owner) {
        return testUserMapper.findUsers(owner);
    }

    @Override
    public int add(TestUserBean user) {
        return testUserMapper.add(user);
    }

    @Override
    public TestUserBean findUser(int id) {
        return testUserMapper.findUser(id);
    }

    @Override
    public int updateUser(TestUserBean bean) {
        return testUserMapper.updateUser(bean);
    }

    @Override
    public ReturnCode checkUser(TestUserBean bean) {
        boolean b = CheckUserStatusUtil.checkUser(bean);
        if (b) {
            int i = testUserMapper.updateUser(bean);
            return i == 1 ? CommonCode.SUCCESS : TestUserCode.UPDATE_USER_FAIL;
        } else {
            return TestUserCode.CHECK_FAIL;
        }
    }


}
