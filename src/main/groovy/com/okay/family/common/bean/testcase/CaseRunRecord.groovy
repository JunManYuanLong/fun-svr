package com.okay.family.common.bean.testcase

import com.alibaba.fastjson.JSONObject
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.config.Constant

/**
 * 用例运行记录
 */
class CaseRunRecord extends AbstractBean {

    private static final long serialVersionUID = -159435949821153477L;

    Integer id

    Integer runId

    Integer mark

    Integer uid

    Integer caseId

    Integer code = Constant.TEST_ERROR_CODE

    Integer result

    List<CaseVerifyBean> checkResult

    JSONObject params

    JSONObject headers

    JSONObject responseResult

}
