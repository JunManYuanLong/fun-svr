package com.okay.family.service;

import com.github.pagehelper.PageInfo;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.bean.testcase.request.CaseSearchBean;
import com.okay.family.common.bean.testcase.request.EditCaseAttributeBean;
import com.okay.family.common.bean.testcase.response.CaseEditRecord;
import com.okay.family.common.bean.testcase.response.TestCaseAttributeBean;
import com.okay.family.common.bean.testcase.response.TestCaseListBean;

import java.util.concurrent.CountDownLatch;

public interface ITestCaseService {


    int addCase(EditCaseAttributeBean bean);

    int copyCase(EditCaseAttributeBean bean);

    int updateCase(EditCaseAttributeBean bean);

    int delCase(DelBean bean);

    void addCaseProjectRelation(EditCaseAttributeBean bean);

    void delCaseProjectRelation(DelBean bean);

    void updateCaseProjectRelation(EditCaseAttributeBean bean);

    void copyCaseProjectRelation(int source, int target);

    void addEditRecord(CaseEditRecord record);

    int updateCaseData(CaseDataBean bean);

    PageInfo<TestCaseListBean> searchCases(CaseSearchBean bean);

    TestCaseAttributeBean getAttributeById(int id);

    void getAttributeById(TestCaseAttributeBean bean, CountDownLatch countDownLatch);

    void getCaseProjectRelation(TestCaseAttributeBean bean, CountDownLatch countDownLatch);


}
