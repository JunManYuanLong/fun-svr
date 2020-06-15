package com.okay.family.mapper;

import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.testuser.EditUserBean;
import com.okay.family.common.bean.testuser.SearchUserBean;
import com.okay.family.common.bean.testuser.TestUserBean;

import java.util.List;

public interface TestUserMapper {

    List<TestUserBean> findUsers(SearchUserBean bean);

    int addUser(EditUserBean user);

    TestUserBean findUser(int id);

    /**
     * 更新测试用户状态和用户凭据
     *
     * @param user
     * @return
     */
    int updateUser(EditUserBean user);

    int updateUserStatus(TestUserBean user);


    /**
     * 获取user信息
     *
     * @param environment
     * @param name
     * @return
     */
    TestUserBean getUser(int environment, String name);

    int delUser(DelBean bean);


}
