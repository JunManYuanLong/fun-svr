package com.okay.family.common.bean.testcase

import com.okay.family.fun.base.bean.AbstractBean

class RunCaseHistoryBean extends AbstractBean {

    int mark

    int operationid

    int caseid

    /**
     * 0:默认,1:成功;2:失败,3:无法运行
     */
    int status

    String params

    String headers

    String response

}
