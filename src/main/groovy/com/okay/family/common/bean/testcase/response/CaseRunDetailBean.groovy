package com.okay.family.common.bean.testcase.response

import com.alibaba.fastjson.JSONObject
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.fun.base.bean.AbstractBean

class CaseRunDetailBean extends AbstractBean{

    private static final long serialVersionUID = -12987746646546;

    Integer runId

    Integer mark

    Integer caseId

    Integer code

    String requestid

    Integer httpCode

    Integer cost

    List<CaseVerifyBean> verify

    JSONObject headers

    JSONObject params

    JSONObject response

    String create_time

}
