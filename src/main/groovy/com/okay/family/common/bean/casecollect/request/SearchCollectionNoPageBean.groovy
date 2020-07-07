package com.okay.family.common.bean.casecollect.request

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.NotNull

class SearchCollectionNoPageBean extends AbstractBean{

    private static final long serialVersionUID = -324894815616518798;


    @NotNull
    Integer uid

    @NotNull
    @Range(min = 1L, max = OkayConstant.ENV, message = "环境ID参数错误")
    Integer envId

    @NotNull
    String query

}
