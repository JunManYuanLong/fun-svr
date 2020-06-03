package com.okay.family.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.testcase.RunCaseHistoryBean;
import com.okay.family.common.bean.testcase.TestCaseBean;
import com.okay.family.mapper.TestCaseMapper;
import com.okay.family.mapper.TestUserMapper;
import com.okay.family.service.ITestCaseService;
import com.okay.family.utils.RunCaseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestCaseServiceImpl implements ITestCaseService {

    @Autowired
    TestCaseMapper testCaseMapper;

    @Autowired
    TestUserMapper testUserMapper;

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
    public RunCaseHistoryBean runCase(int id) {
        TestCaseBean testCaseBean = testCaseMapper.findCase(id);
        //todo:此处处理参数里面的校验值

        int server = testCaseBean.getServer();

        JSONObject headers = testCaseBean.getHeaders();
        headers.keySet().stream().forEach(key ->
        {
            String value = headers.getString(key);
            if (value.startsWith(OkayConstant.USER_CERTIFICATE_KEY)) {
                String uname = value.substring(OkayConstant.USER_CERTIFICATE_KEY.length());
                String certificate = testUserMapper.getCertificate(testCaseBean.getEnvironment(), uname);
                headers.put(key, certificate);
            }
        });
        JSONObject params = testCaseBean.getParams();

        params.keySet().stream().forEach(key ->
        {
            String value = params.getString(key);
            if (value.startsWith(OkayConstant.USER_CERTIFICATE_KEY)) {
                String uname = value.substring(OkayConstant.USER_CERTIFICATE_KEY.length());
                String certificate = testUserMapper.getCertificate(testCaseBean.getEnvironment(), uname);
                params.put(key, certificate);
            }
        });
        return RunCaseUtil.run(testCaseBean);
    }


}
