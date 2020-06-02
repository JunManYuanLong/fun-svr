package com.okay.family.service;

import com.okay.family.constants.bean.TestUserBean;

import java.util.List;

public interface ITestUserService {

    List<TestUserBean> findUsers(int owner);

    int add(TestUserBean user);


}
