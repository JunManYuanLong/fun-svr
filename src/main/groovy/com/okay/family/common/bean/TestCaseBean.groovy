package com.okay.family.common.bean

import com.alibaba.fastjson.JSONObject
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min

class TestCaseBean extends AbstractBean {

    @Min(value = 1L, message = "uid错误")
    int uid

    @Range(min = 1L, max = 5L, message = "环境设置错误")
    int environment

    int project

    int apiId

    String host

    String apiName
    /**
     * 接口类型,1:读;2:写
     */
    @Range(min = 1L, max = 2L)
    int apiType

    /**
     * 1:读接口,2:写接口
     */
    @Range(min = 1L, max = 2L)
    JSONObject method

    JSONObject headers

    JSONObject params


}
