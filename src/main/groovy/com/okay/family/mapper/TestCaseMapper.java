package com.okay.family.mapper;

import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.common.ProjectBean;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.bean.testcase.request.CaseSearchBean;
import com.okay.family.common.bean.testcase.request.EditCaseAttributeBean;
import com.okay.family.common.bean.testcase.response.CaseEditRecord;
import com.okay.family.common.bean.testcase.response.TestCaseAttributeBean;
import com.okay.family.common.bean.testcase.response.TestCaseListBean;

import java.util.List;

public interface TestCaseMapper {

    int addCase(EditCaseAttributeBean bean);

    int copyCase(EditCaseAttributeBean bean);

    int updateCase(EditCaseAttributeBean bean);

    int delCase(DelBean bean);

    void addCaseProjectRelation(EditCaseAttributeBean bean);

    void copyCaseProjectRelation(int source, int target);

    void delCaseProjectRelation(DelBean bean);

    void addEditRecord(CaseEditRecord record);

    int updateCaseData(CaseDataBean bean);

    List<TestCaseListBean> searchCases(CaseSearchBean bean);

    TestCaseAttributeBean getAttributeById(int id);

    List<ProjectBean> getCaseProjectRelation(int id);


}
