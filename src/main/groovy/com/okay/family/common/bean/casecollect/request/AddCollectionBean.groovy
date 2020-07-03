package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * 添加用例
 */
class AddCollectionBean extends AbstractBean{

    private static final long serialVersionUID = 589469783232L;

    Integer id

    @NotNull
    @Min(value = 1L)
    Integer uid

    @NotNull
    @Min(value = 1L)
    Integer envId

    @NotEmpty(message = "用例ID不能为空")
    Set<Integer> apiList

    @NotBlank
    String groupName

}
