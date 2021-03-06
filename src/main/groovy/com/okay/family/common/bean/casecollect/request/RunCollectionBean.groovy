package com.okay.family.common.bean.casecollect.request

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * 运行用例集
 */
class RunCollectionBean extends AbstractBean {

    private static final long serialVersionUID = 9733934389389749;

    @NotNull(message = "groupId不能为空")
    @Min(value = 1L)
    Integer groupId

    @NotNull(message = "uid不能为空")
    @Min(value = 1L)
    Integer uid

    @NotNull(message = "envId不能为空")
    @Range(min = 1L, max = OkayConstant.ENV, message = "环境ID参数错误")
    Integer envId

}
