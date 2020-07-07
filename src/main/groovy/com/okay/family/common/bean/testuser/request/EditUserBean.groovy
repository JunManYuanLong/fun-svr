package com.okay.family.common.bean.testuser.request

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.base.bean.AbstractBean
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

    @NotNull
    @Min(value = 1L, message = "用户ID错误")
    Integer uid

    @NotNull
    @Range(min = 1L, max = OkayConstant.ENV, message = "环境变量错误")
    Integer envId

    @NotNull
    @Range(min = 1L, max = OkayConstant.ROLE, message = "不支持的用户身份")
    Integer roleId

    @NotBlank
    String user

    @NotBlank
    @Pattern(regexp = "add|update|delete", message = "请输入正确的类型:add|update|delete")
    String type

    @NotBlank
    String password

    String phone

    @NotNull
    @Length(min = 0, max = 128, message = "用户描述内容长度错误!")
    String des


}
