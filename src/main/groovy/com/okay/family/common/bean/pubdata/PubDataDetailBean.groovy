package com.okay.family.common.bean.pubdata

import com.okay.family.fun.base.bean.AbstractBean

import javax.validation.constraints.NotNull

/**
 * 用于存储公共数据的每一条对象
 */
class PubDataDetailBean extends AbstractBean {

    private static final long serialVersionUID = 62931721071606L;

    @NotNull
    String text

    @NotNull
    String value

}
