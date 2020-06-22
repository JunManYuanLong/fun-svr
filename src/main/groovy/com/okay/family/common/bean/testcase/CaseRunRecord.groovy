package com.okay.family.common.bean.testcase

import com.alibaba.fastjson.JSONObject
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.fun.base.bean.AbstractBean

class CaseRunRecord extends AbstractBean {

    private static final long serialVersionUID = -159435949821153477L;

    int id
    /**
     * 预留字段给用例集运行的时候用的
     */
    int runId

    int mark

    int uid

    int caseId

    int code

    int result

    List<CaseVerifyBean> verify

    JSONObject params

    JSONObject headers

    JSONObject response

}
