package com.okay.family.common.bean.pubdata

import com.alibaba.fastjson.JSONArray
import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.config.Constant

import javax.validation.constraints.NotNull

/**
 * 用于存储公共数据的每一条对象,后面属性默认方便前端展示处理
 */
class PubDataDetailBean extends AbstractBean {

    private static final long serialVersionUID = 62931721071606L;

    @NotNull
    String name

    @NotNull
    String demo

    String requirement = "R"

    JSONArray children = new JSONArray(0)

    String dataType = "string"

    String remark = Constant.EMPTY

}
