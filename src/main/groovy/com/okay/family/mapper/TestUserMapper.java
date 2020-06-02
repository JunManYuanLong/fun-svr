package com.okay.family.mapper;

import com.okay.family.constants.bean.TestUserBean;

import java.util.List;

public interface TestUserMapper {

    List<TestUserBean> findUsers(int owner);

    int add(TestUserBean user);


}
