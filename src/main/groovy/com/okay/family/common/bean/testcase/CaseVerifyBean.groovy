package com.okay.family.common.bean.testcase

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.NotNull

/**
 * 测试期望
 */
class CaseVerifyBean extends AbstractBean {

    private static final long serialVersionUID = -1595942567071153982L;

    @NotNull
    String key

    @NotNull
    String value

    @NotNull
    String description

    String result

}
