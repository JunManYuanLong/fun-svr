package com.okay.family.service.impl;

import com.okay.family.constants.bean.TestCaseBean;
import com.okay.family.mapper.TestCaseMapper;
import com.okay.family.service.ITestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestCaseServiceImpl implements ITestCaseService {

    @Autowired
    TestCaseMapper testCaseMapper;

    @Override
    public int saveCase(TestCaseBean bean) {
        return testCaseMapper.saveCase(bean);
    }


}
