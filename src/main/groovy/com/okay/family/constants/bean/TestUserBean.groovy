package com.okay.family.constants.bean

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min

/**
 * 测试用户
 */
class TestUserBean extends AbstractBean {
    /**
     * 拥有者
     */
    @Min(value = 0L)
    int owner
    /**
     * 账号
     */
    int uname
    /**
     * 身份信息,包含项目
     */
    @Range(min = 1L, max = 12L, message = "测试用户身份错误!")
    int identity
    /**
     * 明文密码
     */
    String pwd
    /**
     * 手机号
     */
    long phone
    /**
     * 环境
     */
    int environment
    /**
     * 用例秒速
     */
    String desc

    /**
     * 状态,默认0
     */
    @Range(min = 0L, max = 2L, message = "测试用户状态错误!")
    int status

}
