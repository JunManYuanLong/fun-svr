package com.okay.family.mapper;

import com.okay.family.common.bean.testcase.request.CaseAttributeBean;

public interface TestCaseMapper {


    int addCase(CaseAttributeBean bean);

    int copyCase(CaseAttributeBean bean);

    int updateCase(CaseAttributeBean bean);


}
