package com.okay.family.common.bean.testcase.response

import com.alibaba.fastjson.JSONArray
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.fun.base.bean.AbstractBean

/**
 * 用例详情,用于运行用例
 */
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

    JSONArray header_paras

    List<CaseVerifyBean> testWish

    JSONArray request_paras

    String url


}
