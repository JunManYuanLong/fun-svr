package com.okay.family.service;

import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.testcase.request.CaseAttributeBean;
import com.okay.family.common.bean.testcase.response.CaseEditRecord;

public interface ITestCaseService {


    int addCase(CaseAttributeBean bean);

    int copyCase(CaseAttributeBean bean);

    int updateCase(CaseAttributeBean bean);

    int delCase(DelBean bean);

    void addCaseProjectRelation(CaseAttributeBean bean);

    void delCaseProjectRelation(DelBean bean);

    void updateCaseProjectRelation(CaseAttributeBean bean);

    void copyCaseProjectRelation(int source, int target);

    void addEditRecord(CaseEditRecord record);
}
