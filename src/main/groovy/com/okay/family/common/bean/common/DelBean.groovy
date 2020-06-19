package com.okay.family.common.bean.common

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min

class DelBean extends AbstractBean {

    private static final long serialVersionUID = 89458151651564747L;

    @Min(value = 0L, message = "id参数错误")
    int id

    int uid

}
