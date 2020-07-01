package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min

/**
 * 用例用例集和用例关联关系
 */
class DelCaseCollectionRelationBean extends AbstractBean {

    private static final long serialVersionUID = 38498348343489;

    @Min(value = 1L)
    Integer uid

    @Min(value = 1L)
    Integer apiId

    @Min(value = 1L)
    Integer groupId

}
