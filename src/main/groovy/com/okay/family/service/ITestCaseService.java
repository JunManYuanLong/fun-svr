package com.okay.family.service;

import com.okay.family.common.bean.testcase.RunCaseRecordBean;
import com.okay.family.common.bean.testcase.TestCaseBean;

import java.util.List;

public interface ITestCaseService {


    int saveCase(TestCaseBean bean);

    /**
     * 获取当前用户某个接口的用例详情
     *
     * @param uid
     * @param api_id
     * @return
     */
    List<TestCaseBean> findMy(int uid, int api_id);

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
    RunCaseRecordBean runCase(int id);


    void editCase(TestCaseBean bean);

    void addRunCaseRecord(RunCaseRecordBean bean);

    List<TestCaseBean> search(String name);

}
