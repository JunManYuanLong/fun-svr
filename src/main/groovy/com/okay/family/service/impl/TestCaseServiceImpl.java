package com.okay.family.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.basedata.ServiceHost;
import com.okay.family.common.bean.testcase.*;
import com.okay.family.common.enums.CaseStatus;
import com.okay.family.fun.base.exception.FailException;
import com.okay.family.fun.config.Constant;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.mapper.TestCaseMapper;
import com.okay.family.mapper.TestUserMapper;
import com.okay.family.service.ITestCaseService;
import com.okay.family.service.ITestUserService;
import com.okay.family.utils.RunCaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseServiceImpl implements ITestCaseService {

    private static Logger logger = LoggerFactory.getLogger(TestCaseServiceImpl.class);

    TestCaseMapper testCaseMapper;

    TestUserMapper testUserMapper;

    ITestUserService testUserService;

    @Autowired
    public TestCaseServiceImpl(TestCaseMapper testCaseMapper, TestUserMapper testUserMapper, ITestUserService testUserService) {
        this.testCaseMapper = testCaseMapper;
        this.testUserMapper = testUserMapper;
        this.testUserService = testUserService;
    }

    @Override
    public int addCase(TestCaseBean bean) {
        bean.setHost(ServiceHost.getHost(bean.getEnvironment(), bean.getServerid()));
        return testCaseMapper.addCase(bean);
    }

    @Override
    public List<TestCaseBean> findCasesByApi(int environment, int api_id) {
        List<TestCaseBean> my = testCaseMapper.findCasesByApi(environment, api_id);
        return my;
    }

    @Override
    public List<TestCaseBean> findMyCases(int uid) {
        List<TestCaseBean> myCase = testCaseMapper.findMyCases(uid);
        return myCase;
    }

    @Override
    public TestCaseBean getCaseInfo(int id) {
        TestCaseBean caseBean = testCaseMapper.getCaseInfo(id);
        return caseBean;
    }

    @Override
    public CaseRunRecord runCase(int id) {

        CaseRunRecord run = new CaseRunRecord();

        TestCaseBean testCaseBean = testCaseMapper.getCaseInfo(id);

        int server = testCaseBean.getServerid();

        JSONObject headers = testCaseBean.getHeaders();

        JSONObject params = testCaseBean.getParams();
        try {
            handleParams(params);
            handleParams(headers);
        } catch (FailException e) {
            logger.error("处理参数中表达式发生错误!", e);
            run.setStatus(CaseStatus.USER_EROOR.getCode());
        }
        run = RunCaseUtil.run(testCaseBean);
        addRunCaseRecord(run);
        return run;
    }

    @Override
    public int editCaseAttribute(CaseAttributeBean bean) {
        int i = testCaseMapper.editAttribute(bean);
        return i;
    }

    @Override
    public int editCaseData(CaseDataBean bean) {
        int i = testCaseMapper.editData(bean);
        return i;
    }

    @Async
    @Override
    public void addRunCaseRecord(CaseRunRecord bean) {
        testCaseMapper.saveCaseRunRecord(bean);
    }

    @Async
    @Override
    public void addEditCaseRecord(CaseEditRecord record) {
        testCaseMapper.saveEditReord(record);
    }

    @Override
    public List<TestCaseBean> search(String name) {
        List<TestCaseBean> search = testCaseMapper.search(name);

        return search;
    }


    /**
     * 处理参数中的表达式信息
     *
     * @param params
     */
    public void handleParams(JSONObject params) {
        params.keySet().stream().forEach(key ->
        {
            String value = params.getString(key);
            if (value.startsWith(OkayConstant.USER_CERTIFICATE_KEY)) {
                int id = SourceCode.changeStringToInt(value.substring(OkayConstant.USER_CERTIFICATE_KEY.length()));
                String certificate = testUserService.getCertificate(id);
                params.put(key, certificate);
            } else if (value.startsWith(OkayConstant.RANDOM_KEY)) {
                String replace = value.replace(OkayConstant.RANDOM_KEY, Constant.EMPTY);
                String[] split = replace.split(",", 2);
                params.put(key, SourceCode.getRandomIntRange(SourceCode.changeStringToInt(split[0]), SourceCode.changeStringToInt(split[1])));
            }
        });
    }


}
