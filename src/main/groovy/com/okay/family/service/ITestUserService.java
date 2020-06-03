package com.okay.family.service;

import com.okay.family.common.bean.testuser.TestUserBean;
import com.okay.family.fun.base.interfaces.ReturnCode;

import java.util.List;

public interface ITestUserService {

    List<TestUserBean> findUsers(int owner);

    int add(TestUserBean user);

    TestUserBean findUser(int id);

    int updateUser(TestUserBean bean);

    ReturnCode checkUser(TestUserBean bean);


}
