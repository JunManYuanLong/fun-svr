package com.okay.family.service;

import com.okay.family.constants.bean.TestCaseBean;

import java.util.List;

public interface ITestCaseService {


    int saveCase(TestCaseBean bean);

    List<TestCaseBean> findMy(int uid, int api_id);


}
