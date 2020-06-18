package com.okay.family.mapper;

import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.testcase.request.CaseAttributeBean;
import com.okay.family.common.bean.testcase.response.CaseEditRecord;

public interface TestCaseMapper {

    int addCase(CaseAttributeBean bean);

    int copyCase(CaseAttributeBean bean);

    int updateCase(CaseAttributeBean bean);

    int delCase(DelBean bean);

    void addCaseProjectRelation(CaseAttributeBean bean);

    void copyCaseProjectRelation(int source, int target);

    void delCaseProjectRelation(DelBean bean);

    void addEditRecord(CaseEditRecord record);


}
