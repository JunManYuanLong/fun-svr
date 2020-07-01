package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * 添加用例
 */
class AddCollectionBean extends AbstractBean{

    private static final long serialVersionUID = 589469783232L;

    Integer id

    @Min(value = 1L)
    Integer uid

    @Min(value = 1L)
    Integer envId

    @NotNull
    Set<Integer> apiList

    @NotNull
    String groupName

}
