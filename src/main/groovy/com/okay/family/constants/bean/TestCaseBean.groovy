package com.okay.family.constants.bean

import com.alibaba.fastjson.JSONObject
import com.okay.family.fun.base.bean.AbstractBean

class TestCaseBean extends AbstractBean {

    int uid

    int environment

    int project

    int apiId

    String host

    String apiName

    JSONObject method

    JSONObject headers

    JSONObject params


}
