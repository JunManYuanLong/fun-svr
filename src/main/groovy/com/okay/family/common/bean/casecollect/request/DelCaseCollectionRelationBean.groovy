package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
/**
 * 用例用例集和用例关联关系
 */
class DelCaseCollectionRelationBean extends AbstractBean {

    private static final long serialVersionUID = 38498348343489;

    @NotNull(message = "uid不能为空")
    @Min(value = 1L)
    Integer uid

    @NotNull(message = "apiId不能为空")
    @Min(value = 1L)
    Integer apiId

    @NotNull(message = "groupId不能为空")
    @Min(value = 1L)
    Integer groupId

}
