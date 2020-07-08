package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class DelCollectionBean extends AbstractBean {

    private static final long serialVersionUID = -15498489416;

    @NotNull
    @Min(value = 1L)
    Integer uid

    @NotNull
    @Min(value = 1L)
    Integer groupId


}
