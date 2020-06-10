package com.okay.family.service;

import com.okay.family.common.bean.testcase.CaseRunRecord;
import com.okay.family.common.bean.testcase.TestCaseBean;

import java.util.List;

public interface ITestCaseService {


    int addCase(TestCaseBean bean);

    /**
     * 获取当前用户某个接口的用例详情
     *
     * @param environment
     * @param api_id
     * @return
     */
    //TODO:未完成
    List<TestCaseBean> findMy(int environment, int api_id);

    /**
     * 获取某个用例详情
     *
     * @param id
     * @return
     */
    TestCaseBean getCase(int id);

    /**
     * 运行某一个case
     * @param id
     * @return
     */
    CaseRunRecord runCase(int id);


    void editCase(TestCaseBean bean);

    void addRunCaseRecord(CaseRunRecord bean);

    List<TestCaseBean> search(String name);

}
