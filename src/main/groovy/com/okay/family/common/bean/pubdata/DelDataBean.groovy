package com.okay.family.common.bean.pubdata

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min

class DelDataBean extends AbstractBean {

    private static final long serialVersionUID = -629048126822729332L;

    @Min(value = 1L)
    int id

    @Min(value = 1L)
    int owner

}
