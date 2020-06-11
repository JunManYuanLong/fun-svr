package com.okay.family.service;

import com.okay.family.common.bean.testcase.*;

import java.util.List;

public interface ITestCaseService {


    int addCase(TestCaseBean bean);

    /**
     * 获取当前某个环境某个接口的用例列表,用户创建用例集时添加测试用例
     *
     * @param environment
     * @param api_id
     * @return
     */
    //TODO:未完成
    List<TestCaseBean> findCasesByApi(int environment, int api_id);

    List<TestCaseBean> findMyCases(int uid);

    /**
     * 获取某个用例详情
     *
     * @param id
     * @return
     */
    TestCaseBean getCaseInfo(int id);

    /**
     * 运行某一个case
     *
     * @param id
     * @return
     */
    CaseRunRecord runCase(int id);


    int editCaseAttribute(CaseAttributeBean bean);

    int editCaseData(CaseDataBean bean);

    void addRunCaseRecord(CaseRunRecord record);

    void addEditCaseRecord(CaseEditRecord record);

    List<TestCaseBean> search(String name);


}
