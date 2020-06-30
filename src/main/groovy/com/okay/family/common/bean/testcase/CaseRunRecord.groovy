package com.okay.family.common.bean.testcase

import com.alibaba.fastjson.JSONObject
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.fun.base.bean.AbstractBean

class CaseRunRecord extends AbstractBean {

    private static final long serialVersionUID = -159435949821153477L;

    Integer id
    /**
     * 预留字段给用例集运行的时候用的
     */
    Integer runId

    Integer mark

    Integer uid

    Integer caseId

    Integer code

    Integer result

    List<CaseVerifyBean> verify

    JSONObject params

    JSONObject headers

    JSONObject response

}
