package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class DelCollectionBean extends AbstractBean {

    private static final long serialVersionUID = -15498489416;

    @NotNull(message = "uid不能为空")
    @Min(value = 1L)
    Integer uid

    @NotNull(message = "groupId不能为空")
    @Min(value = 1L)
    Integer groupId


}
