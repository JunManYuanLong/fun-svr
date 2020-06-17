package com.okay.family.service;

import com.okay.family.common.bean.testcase.request.CaseAttributeBean;

public interface ITestCaseService {


    int addCase(CaseAttributeBean bean);

    int copyCase(CaseAttributeBean bean);

    int updateCase(CaseAttributeBean bean);


}
