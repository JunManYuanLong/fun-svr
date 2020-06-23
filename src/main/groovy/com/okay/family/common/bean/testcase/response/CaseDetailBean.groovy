package com.okay.family.common.bean.testcase.response

import com.alibaba.fastjson.JSONArray
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.fun.base.bean.AbstractBean

class CaseDetailBean extends AbstractBean {

    private static final long serialVersionUID = -23489723928974L;


    int id

    int apiId

    String name

    String apiName

    int envId

    String envName

    int serviceId

    String serviceName

    int moduleId

    String moduleName

    String httpType

    String readType

    JSONArray header

    List<CaseVerifyBean> testWish

    JSONArray upData

    String url


}
