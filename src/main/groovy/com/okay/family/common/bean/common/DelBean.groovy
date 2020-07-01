package com.okay.family.common.bean.common

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min

/**
 * 删除
 */
class DelBean extends AbstractBean {

    private static final long serialVersionUID = 89458151651564747L;

    @Min(value = 1L, message = "id参数错误")
    Integer id

    @Min(value = 1L,message = "用户id错误")
    Integer uid

}
