package com.okay.family.common.bean.casecollect.request

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.NotNull

class SearchCollectionNoPageBean extends AbstractBean{

    private static final long serialVersionUID = -324894815616518798;


    @NotNull(message = "uid不能为空")
    Integer uid

    @NotNull(message = "envId不能为空")
    @Range(min = 1L, max = OkayConstant.ENV, message = "环境ID参数错误")
    Integer envId

    @NotNull(message = "query不能为空")
    String query

}
