package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.NotNull

class CollectionEditBean extends AbstractBean{

    private static final long serialVersionUID = 2342423490869045;

    int uid

    int groupId

    @NotNull
    String name

}
