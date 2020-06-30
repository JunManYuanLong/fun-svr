package com.okay.family.common.bean.testuser.request

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

class EditUserBean extends AbstractBean {

    private static final long serialVersionUID = 548951651651L;

    Integer id

    @Min(value = 1L, message = "用户ID错误")
    Integer uid

    @Range(min = 1L, max = 5L)
    Integer envId

    @Range(min = 1L, max = 4L, message = "不支持的用户身份")
    Integer roleId

    @NotNull
    String user

    @Pattern(regexp = "add|update|delete", message = "请输入正确的类型:add|update|delete")
    String type

    @NotNull
    String password

    String phone

    @Length(min = 0, max = 128, message = "用户描述内容长度错误!")
    String des


}
