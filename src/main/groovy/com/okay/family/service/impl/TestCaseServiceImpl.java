package com.okay.family.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.testcase.request.CaseRunRecord;
import com.okay.family.common.bean.testcase.request.*;
import com.okay.family.common.bean.testcase.response.CaseDetailBean;
import com.okay.family.common.bean.testcase.response.CaseEditRetrunRecord;
import com.okay.family.common.bean.testcase.response.TestCaseAttributeBean;
import com.okay.family.common.bean.testcase.response.TestCaseListBean;
import com.okay.family.common.bean.testuser.TestUserCheckBean;
import com.okay.family.common.enums.CaseEditType;
import com.okay.family.common.exception.CaseException;
import com.okay.family.common.exception.UserStatusException;
import com.okay.family.fun.config.Constant;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.mapper.TestCaseMapper;
import com.okay.family.mapper.TestUserMapper;
import com.okay.family.service.ITestCaseService;
import com.okay.family.service.ITestUserService;
import com.okay.family.utils.RunCaseUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
@Transactional(rollbackFor = Exception.class)
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
    public int addCase(EditCaseAttributeBean bean) {
        int i = testCaseMapper.addCase(bean);
        List<Integer> projectList = bean.getProjectList();
        if (projectList != null && projectList.size() > 0 && bean.getId() > 0) {
            addEditRecord(new CaseEditRecord(bean.getId(), bean.getUid(), CaseEditType.CREATE.getCode()));
            addCaseProjectRelation(bean);
        }
        return i;
    }

    @Override
    public int copyCase(EditCaseAttributeBean bean) {
        int source = bean.getId();
        int i = testCaseMapper.copyCase(bean);
        if (i > 0) {
            copyCaseProjectRelation(source, bean.getId());
            addEditRecord(new CaseEditRecord(bean.getId(), bean.getUid(), CaseEditType.CREATE.getCode()));
        }
        return i;
    }

    @Override
    public int updateCase(EditCaseAttributeBean bean) {
        int i = testCaseMapper.updateCase(bean);
        if (i == 1) {
            addEditRecord(new CaseEditRecord(bean.getId(), bean.getUid(), CaseEditType.EDIT_ATTRIBUTE.getCode()));
            updateCaseProjectRelation(bean);
        }
        return i;
    }


    public int delCase(DelBean bean) {
        int i = testCaseMapper.delCase(bean);
        if (i > 0) delCaseProjectRelation(bean);
        return i;
    }

    @Async
    @Override
    public void addCaseProjectRelation(EditCaseAttributeBean bean) {
        testCaseMapper.addCaseProjectRelation(bean);
    }

    @Override
    public void delCaseProjectRelation(DelBean bean) {
        testCaseMapper.delCaseProjectRelation(bean);
    }

    @Override
    public void updateCaseProjectRelation(EditCaseAttributeBean bean) {
        DelBean delBean = new DelBean();
        bean.copyTo(delBean);
        delCaseProjectRelation(delBean);
        addCaseProjectRelation(bean);
    }

    @Async
    @Override
    public void copyCaseProjectRelation(int source, int target) {
        testCaseMapper.copyCaseProjectRelation(source, target);
    }

    @Async
    @Override
    public void addEditRecord(CaseEditRecord record) {
        testCaseMapper.addEditRecord(record);
    }

    @Override
    public int updateCaseData(CaseDataBean bean) {
        int i = testCaseMapper.updateCaseData(bean);
        if (i > 0) addEditRecord(new CaseEditRecord(bean.getId(), bean.getUid(), CaseEditType.EDIT_DATA.getCode()));
        return i;
    }

    @Override
    public PageInfo<TestCaseListBean> searchCases(CaseSearchBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        List<TestCaseListBean> testCaseBeans = testCaseMapper.searchCases(bean);
        PageInfo<TestCaseListBean> pageInfo = new PageInfo(testCaseBeans);
        return pageInfo;
    }

    @Override
    public TestCaseAttributeBean getAttributeById(int id) {
        TestCaseAttributeBean bean = new TestCaseAttributeBean();
        bean.setId(id);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        getAttributeById(bean, countDownLatch);
        getCaseProjectRelation(bean, countDownLatch);
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            CaseException.fail("查询用例信息发生错误:" + e.getMessage());
        }
        return bean;
    }

    @Async
    @Override
    public void getAttributeById(TestCaseAttributeBean bean, CountDownLatch countDownLatch) {
        try {
            TestCaseAttributeBean attributeById = testCaseMapper.getAttributeById(bean.getId());
            bean.copyFrom(attributeById);
        } finally {
            countDownLatch.countDown();
        }
    }

    @Async
    @Override
    public void getCaseProjectRelation(TestCaseAttributeBean bean, CountDownLatch countDownLatch) {
        try {
            bean.setVersionList(testCaseMapper.getCaseProjectRelation(bean.getId()));
        } finally {
            countDownLatch.countDown();
        }
    }

    @Override
    public PageInfo<CaseEditRetrunRecord> getCaseEditRecords(CaseEditRecordQueryBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        List<CaseEditRetrunRecord> caseEditRecords = testCaseMapper.getCaseEditRecords(bean);
        PageInfo<CaseEditRetrunRecord> pageInfo = new PageInfo(caseEditRecords);
        return pageInfo;
    }

    @Override
    public CaseDetailBean getCaseDetail(int id) {
        CaseDetailBean caseDetail = testCaseMapper.getCaseDetail(id);
        return caseDetail;
    }

    @Override
    public CaseRunRecord runCaseData(CaseDataBean bean) {
        handleParams(bean);
        CaseRunRecord record = new CaseRunRecord();
        RunCaseUtil.run(bean, record);
        addRunRecord(record);
        return record;
    }

    @Async
    @Override
    public void addRunRecord(CaseRunRecord runRecord) {
        testCaseMapper.addRunRecord(runRecord);
    }

    @Override
    public void handleParams(CaseDataBean bean) {
        JSONObject params = bean.getParams();
        JSONObject headers = bean.getHeaders();
        handleParams(params);
        handleParams(headers);
    }

    @Override
    public void handleParams(CaseDataBean bean, ConcurrentHashMap<Integer, String> map) {
        JSONObject params = bean.getParams();
        JSONObject headers = bean.getHeaders();
        handleParams(params, map);
        handleParams(headers, map);
    }

    /**
     * 处理参数中的表达式信息
     *
     * @param params
     */
    @Override
    public void handleParams(JSONObject params) {
        params.keySet().stream().forEach(key ->
        {
            String value = params.getString(key);
            if (value.startsWith(OkayConstant.USER_CERTIFICATE_KEY)) {
                int id = SourceCode.changeStringToInt(value.substring(OkayConstant.USER_CERTIFICATE_KEY.length()));
                TestUserCheckBean userCheckBean = testUserService.getCertificate(id);
                String certificate = userCheckBean.getCertificate();
                if (StringUtils.isEmpty(certificate)) UserStatusException.fail();
                params.put(key, certificate);
            } else if (value.startsWith(OkayConstant.RANDOM_KEY)) {
                String replace = value.replace(OkayConstant.RANDOM_KEY, Constant.EMPTY);
                String[] split = replace.split(",", 2);
                params.put(key, SourceCode.getRandomIntRange(SourceCode.changeStringToInt(split[0]), SourceCode.changeStringToInt(split[1])));
            }
        });
    }

    /**
     * 处理参数中的表达式信息
     *
     * @param params
     */
    @Override
    public void handleParams(JSONObject params, ConcurrentHashMap map) {
        params.keySet().stream().forEach(key ->
        {
            String value = params.getString(key);
            if (value.startsWith(OkayConstant.USER_CERTIFICATE_KEY)) {
                int id = SourceCode.changeStringToInt(value.substring(OkayConstant.USER_CERTIFICATE_KEY.length()));
                String certificate = testUserService.getCertificate(id, map);
                if (StringUtils.isEmpty(certificate)) UserStatusException.fail();
                params.put(key, certificate);
            } else if (value.startsWith(OkayConstant.RANDOM_KEY)) {
                String replace = value.replace(OkayConstant.RANDOM_KEY, Constant.EMPTY);
                String[] split = replace.split(",", 2);
                params.put(key, SourceCode.getRandomIntRange(SourceCode.changeStringToInt(split[0]), SourceCode.changeStringToInt(split[1])));
            }
        });
    }


}
