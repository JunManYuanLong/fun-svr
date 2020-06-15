package com.okay.family.common.bean.testuser

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range
/**
 * 测试用户
 */
class TestUserBean extends AbstractBean {

    private static final long serialVersionUID = -15924867071153477L;

    int id;
    /**
     * 环境
     */
    String env

    /**
     * 账号
     */
    String user
    /**
     * 身份信息,包含项目
     */
    @Range(min = 1L, max = 12L, message = "测试用户身份错误!")
    String role
    /**
     * 明文密码
     */
    String password
    /**
     * 手机号
     */
    String phone

    /**
     * 用例描述
     */
    @Length(min = 0, max = 128, message = "用户描述内容长度错误!")
    String dec

    String status

    String create_time

}
