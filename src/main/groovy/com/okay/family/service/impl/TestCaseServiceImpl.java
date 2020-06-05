package com.okay.family.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.testcase.RunCaseHistoryBean;
import com.okay.family.common.bean.testcase.TestCaseBean;
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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseServiceImpl implements ITestCaseService {

    private static Logger logger = LoggerFactory.getLogger(TestCaseServiceImpl.class);

    @Autowired
    TestCaseMapper testCaseMapper;

    @Autowired
    TestUserMapper testUserMapper;

    @Autowired
    ITestUserService testUserService;

    @Override
    public int saveCase(TestCaseBean bean) {
        return testCaseMapper.saveCase(bean);
    }

    @Override
    public List<TestCaseBean> findMy(int uid, int api_id) {
        List<TestCaseBean> my = testCaseMapper.findMy(uid, api_id);
        return my;
    }

    @Override
    public TestCaseBean getCase(int id) {
        TestCaseBean caseBean = testCaseMapper.findCase(id);
        return caseBean;
    }

    @Override
    public RunCaseHistoryBean runCase(int id) {

        RunCaseHistoryBean run = new RunCaseHistoryBean();

        TestCaseBean testCaseBean = testCaseMapper.findCase(id);

        int server = testCaseBean.getServer();

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
        testCaseMapper.saveCaseRunHistory(run);
        return run;
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
