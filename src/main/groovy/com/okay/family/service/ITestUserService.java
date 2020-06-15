package com.okay.family.service;

import com.github.pagehelper.PageInfo;
import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.testuser.EditUserBean;
import com.okay.family.common.bean.testuser.SearchUserBean;
import com.okay.family.common.bean.testuser.TestUserBean;

public interface ITestUserService {

    PageInfo<TestUserBean> findUsers(SearchUserBean bean);

    int addUser(EditUserBean user);


    int updateUser(EditUserBean user);

    /**
     * 删除测试用户
     *
     * @param bean
     * @return
     */
    int delUsr(DelBean bean);


    TestUserBean findUser(int id);

    int updateUserStatus(TestUserBean bean);

    /**
     * 验证测试用户是否可用
     *
     * @param bean
     * @return
     */
    int checkUser(TestUserBean bean);

    /**
     * 获取用户凭据
     *
     * @param id
     * @return
     */
    String getCertificate(int id);




}
