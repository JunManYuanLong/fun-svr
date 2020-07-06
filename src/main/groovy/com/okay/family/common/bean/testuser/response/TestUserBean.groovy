package com.okay.family.common.bean.testuser.response

import com.okay.family.fun.base.bean.AbstractBean
/**
 * 测试用户bean,不包含certificate
 */
class TestUserBean extends AbstractBean {

    private static final long serialVersionUID = -15924867071153477L;

    Integer id

    String envName

    Integer envId

    String user

    String roleName

    Integer roleId

    String password

    String phone

    String des

    String status

    String create_time

}
