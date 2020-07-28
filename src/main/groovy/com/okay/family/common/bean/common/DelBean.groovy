package com.okay.family.common.bean.common

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * 删除,一些比较简单的接口请求对象也可采用
 */
class DelBean extends AbstractBean {

    private static final long serialVersionUID = 89458151651564747L;

    @NotNull(message = "id不能为空")
    @Min(value = 1L, message = "id参数错误")
    Integer id

    @NotNull(message = "uid不能为空")
    @Min(value = 1L,message = "用户id错误")
    Integer uid

}
