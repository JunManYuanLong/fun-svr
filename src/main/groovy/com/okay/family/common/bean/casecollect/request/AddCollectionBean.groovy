package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

/**
 * 添加用例
 */
class AddCollectionBean extends AbstractBean {

    private static final long serialVersionUID = 589469783232L;

    @Min(value = 1L,message = "id参数错误")
    Integer id

    @NotNull(message = "uid不能为空")
    @Min(value = 1L)
    Integer uid

    @NotNull(message = "envId不能为空")
    @Min(value = 1L,message = "envId最小1")
    Integer envId

    @NotEmpty(message = "用例ID不能为空")
    Set<Integer> apiList

    @NotBlank(message = "用例集名称不能为空")
    String groupName

}
