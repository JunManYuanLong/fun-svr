package com.okay.family.service;

import com.github.pagehelper.PageInfo;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.testuser.request.EditUserBean;
import com.okay.family.common.bean.testuser.request.SearchUserBean;
import com.okay.family.common.bean.testuser.response.TestUserBean;
import com.okay.family.common.bean.testuser.TestUserCheckBean;

import java.util.concurrent.ConcurrentHashMap;

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
    int delUesr(DelBean bean);

    TestUserCheckBean findUser(int id);

    int updateUserStatus(TestUserCheckBean bean);

    /**
     * 验证测试用户是否可用
     *
     * @param bean
     * @return
     */
    boolean checkUser(TestUserCheckBean bean);

    boolean checkUser(int id);

    /**
     * 获取用户凭据
     *
     * @param id
     * @return
     */
    String getCertificate(int id);

    String getCertificate(int id, ConcurrentHashMap<Integer, String> certificates);


}
