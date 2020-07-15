package com.okay.family.mapper;

import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.testuser.request.EditUserBean;
import com.okay.family.common.bean.testuser.request.SearchUserBean;
import com.okay.family.common.bean.testuser.response.TestUserBean;
import com.okay.family.common.bean.testuser.TestUserCheckBean;

import java.util.List;

public interface TestUserMapper {

    List<TestUserBean> findUsers(SearchUserBean bean);

    int addUser(EditUserBean user);

    TestUserCheckBean findUser(int id);

    /**
     * 更新测试用户状态和用户凭据
     *
     * @param user
     * @return
     */
    int updateUser(EditUserBean user);

    /**
     * 此处包括更新用户最近一次编辑时间,直接调用表示更新时间,如果是登录后更新,必需加分布式锁
     *
     * @param user
     * @return
     */
    int updateUserStatus(TestUserCheckBean user);

    int delUser(DelBean bean);


}
