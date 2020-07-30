package com.okay.family.common.bean.testcase

import com.alibaba.fastjson.JSONObject
import com.okay.family.common.basedata.OkayConstant
import com.okay.family.common.bean.testcase.CaseVerifyBean
import com.okay.family.common.bean.testcase.request.CaseDataBean
import com.okay.family.common.enums.RunResult
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


/**
 * 用于通用的错误类型,各类错误无法也要有运行记录
 * @param result
 * @param bean
 * @return
 */
    def fail(RunResult result, CaseDataBean bean) {
        this.setResult(result.getCode());
        this.setResponseResult(new JSONObject());
        this.setCode(OkayConstant.TEST_ERROR_CODE);
        this.setCheckResult(bean.getTestWish());
    }

}
