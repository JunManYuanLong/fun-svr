package com.okay.family.common.bean.testuser.request

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.config.Constant
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

/**
 * 编辑测试用户信息
 */
class EditUserBean extends AbstractBean {

    private static final long serialVersionUID = 548951651651L;

    @Min(value = 1L)
    Integer id

    @NotNull(message = "uid不能为空")
    @Min(value = 1L, message = "用户ID错误")
    Integer uid

    @NotNull(message = "envId不能为空")
    @Range(min = 1L, max = OkayConstant.ENV, message = "环境变量错误")
    Integer envId

    @NotNull(message = "roleId不能为空")
    @Range(min = 1L, max = OkayConstant.ROLE, message = "不支持的用户身份")
    Integer roleId

    @NotBlank(message = "user不能为空")
    @Length(min = 6, max = 11, message = "用户名长度应为6-11位")
    String user

    @NotBlank(message = "type不能为空")
    @Pattern(regexp = "add|update|delete", message = "请输入正确的类型:add|update|delete")
    String type

    @NotBlank(message = "password不能为空")
    @Length(min = 6, max = 20, message = "用户密码长度应为6-20位")
    String password

    String phone = Constant.EMPTY

    @Length(min = 0, max = 100, message = "用户描述内容长度错误!1-100")
    String des = Constant.EMPTY


}
