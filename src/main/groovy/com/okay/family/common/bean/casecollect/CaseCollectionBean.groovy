package com.okay.family.common.bean.casecollect

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Length
import org.hibernate.validator.constraints.Range

class CaseCollectionBean extends AbstractBean {

    private static final long serialVersionUID = 5853160930717016429L;

    int id

    int owner
    @Range(min = 1L, max = 4L, message = "环境设置错误!")
    int environment

//    @Pattern(regexp = "(\\d+,)+\\d+", message = "用例id集格式错误!")
    Set<Integer> caseids

    @Length(min = 1, max = 32, message = "用例集名称长度错误")
    String name

    /**
     * 是否被共享,默认0不同享,1共享
     */
    int pub
    /**
     * 是否被删除,默认0,1删除
     */
    int isdel

    int lasteditor

}
