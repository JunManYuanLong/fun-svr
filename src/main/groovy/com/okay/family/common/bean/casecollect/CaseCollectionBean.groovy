package com.okay.family.common.bean.casecollect

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Pattern

class CaseCollectionBean extends AbstractBean {

    private static final long serialVersionUID = 5853160930717016429L;

    int id

    int owner
    @Range(min = 1L, max = 4L, message = "环境设置错误!")
    int environment

    @Pattern(regexp = "(\\d+,)+\\d+", message = "用例id集格式错误!")
    String caseids

    /**
     * 是否被共享
     */
    int pub
}
