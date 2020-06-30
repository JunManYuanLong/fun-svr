package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min

class RunCollectionBean extends AbstractBean {

    private static final long serialVersionUID = 9733934389389749;

    @Min(value = 1L)
    Integer groupId

    @Min(value = 1L)
    Integer uid

    @Range(min = 1L,max = 5L)
    Integer envId

}
