package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class AddCollectionBean extends AbstractBean{

    private static final long serialVersionUID = 589469783232L;

    int id

    @Min(value = 1L)
    int uid

    @Min(value = 1L)
    int envId

    @NotNull
    Set<Integer> apiList

    @NotNull
    String groupName

}
