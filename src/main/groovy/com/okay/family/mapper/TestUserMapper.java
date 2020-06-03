package com.okay.family.mapper;

import com.okay.family.common.bean.testuser.TestUserBean;

import java.util.List;

public interface TestUserMapper {

    List<TestUserBean> findUsers(int owner);

    int add(TestUserBean user);

    TestUserBean findUser(int id);

    int updateUser(TestUserBean user);


}
