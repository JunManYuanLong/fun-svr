package com.okay.family.common.bean.testcase.request

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class CaseSearchNoPageBean extends AbstractBean {

    private static final long serialVersionUID = -654984894165;

    @NotNull(message = "uid不能为空")
    @Min(value = 1L)
    Integer uid

    @NotNull(message = "envId不能为空")
    @Range(min = 0L, max = OkayConstant.ENV, message = "环境变量参数错误")
    Integer envId

    @NotNull(message = "apiId不能为空")
    @Min(value = 1L)
    Integer apiId

}
