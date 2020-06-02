package com.okay.family.service.impl;

import com.okay.family.constants.bean.TestUserBean;
import com.okay.family.mapper.TestUserMapper;
import com.okay.family.service.ITestUserService;
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


}
