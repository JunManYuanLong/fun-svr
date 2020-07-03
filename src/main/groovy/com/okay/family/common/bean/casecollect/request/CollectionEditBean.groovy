package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
/**
 * 编辑用例集
 */
class CollectionEditBean extends AbstractBean {

    private static final long serialVersionUID = 2342423490869045;

    @Min(value = 1L)
    Integer uid

    @Min(value = 1L)
    Integer groupId

    @NotBlank
    String name

}
