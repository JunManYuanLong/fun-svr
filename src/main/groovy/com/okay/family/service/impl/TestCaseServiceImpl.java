package com.okay.family.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.testcase.request.CaseAttributeBean;
import com.okay.family.common.bean.testcase.response.CaseEditRecord;
import com.okay.family.common.bean.testuser.TestUserCheckBean;
import com.okay.family.common.enums.CaseEditType;
import com.okay.family.fun.config.Constant;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.mapper.TestCaseMapper;
import com.okay.family.mapper.TestUserMapper;
import com.okay.family.service.ITestCaseService;
import com.okay.family.service.ITestUserService;
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
    public int addCase(CaseAttributeBean bean) {
        int i = testCaseMapper.addCase(bean);
        List<Integer> projectList = bean.getProjectList();
        if (projectList != null && projectList.size() > 0 && bean.getId() > 0) {
            addEditRecord(new CaseEditRecord(bean.getId(),bean.getUid(),CaseEditType.CREATE.getDesc()));
            addCaseProjectRelation(bean);
        }
        return i;
    }

    @Override
    public int copyCase(CaseAttributeBean bean) {
        int source = bean.getId();
        int i = testCaseMapper.copyCase(bean);
        if (i > 0) {
            copyCaseProjectRelation(source, bean.getId());
            addEditRecord(new CaseEditRecord(bean.getId(),bean.getUid(),CaseEditType.CREATE.getDesc()));
        }
        return i;
    }

    @Override
    public int updateCase(CaseAttributeBean bean) {
        int i = testCaseMapper.updateCase(bean);
        if (i == 1) {
            addEditRecord(new CaseEditRecord(bean.getId(), bean.getUid(), CaseEditType.EDIT_ATTRIBUTE.getDesc()));
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
    public void addCaseProjectRelation(CaseAttributeBean bean) {
        testCaseMapper.addCaseProjectRelation(bean);
    }

    @Override
    public void delCaseProjectRelation(DelBean bean) {
        testCaseMapper.delCaseProjectRelation(bean);
    }

    @Override
    public void updateCaseProjectRelation(CaseAttributeBean bean) {
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
                TestUserCheckBean userCheckBean = testUserService.getCertificate(id);
                params.put(key, userCheckBean.getCertificate());
            } else if (value.startsWith(OkayConstant.RANDOM_KEY)) {
                String replace = value.replace(OkayConstant.RANDOM_KEY, Constant.EMPTY);
                String[] split = replace.split(",", 2);
                params.put(key, SourceCode.getRandomIntRange(SourceCode.changeStringToInt(split[0]), SourceCode.changeStringToInt(split[1])));
            }
        });
    }


}
