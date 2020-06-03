package com.okay.family.service.impl;

import com.okay.family.common.bean.TestCaseBean;
import com.okay.family.mapper.TestCaseMapper;
import com.okay.family.service.ITestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseServiceImpl implements ITestCaseService {

    @Autowired
    TestCaseMapper testCaseMapper;

    @Override
    public int saveCase(TestCaseBean bean) {
        return testCaseMapper.saveCase(bean);
    }

    @Override
    public List<TestCaseBean> findMy(int uid, int api_id) {
        List<TestCaseBean> my = testCaseMapper.findMy(uid, api_id);
        return my;
    }


}
