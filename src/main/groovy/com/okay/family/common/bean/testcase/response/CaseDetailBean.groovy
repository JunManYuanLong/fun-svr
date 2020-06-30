package com.okay.family.common.bean.testcase.response

import com.alibaba.fastjson.JSONArray
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.fun.base.bean.AbstractBean

class CaseDetailBean extends AbstractBean {

    private static final long serialVersionUID = -23489723928974L;


    Integer id

    Integer apiId

    String name

    String apiName

    Integer envId

    String envName

    Integer serviceId

    String serviceName

    Integer moduleId

    String moduleName

    String httpType

    String readType

    JSONArray header

    List<CaseVerifyBean> testWish

    JSONArray upData

    String url


}
