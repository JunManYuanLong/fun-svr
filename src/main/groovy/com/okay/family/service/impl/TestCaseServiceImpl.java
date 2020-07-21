package com.okay.family.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.common.SimpleBean;
import com.okay.family.common.bean.testcase.CaseRunRecord;
import com.okay.family.common.bean.testcase.request.*;
import com.okay.family.common.bean.testcase.response.*;
import com.okay.family.common.bean.testuser.TestUserCheckBean;
import com.okay.family.common.code.TestCaseCode;
import com.okay.family.common.enums.CaseEditType;
import com.okay.family.common.exception.CaseException;
import com.okay.family.common.exception.UserStatusException;
import com.okay.family.fun.config.Constant;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.mapper.CaseCollectionMapper;
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

    CaseCollectionMapper collectionMapper;

    @Autowired
    public TestCaseServiceImpl(TestCaseMapper testCaseMapper, TestUserMapper testUserMapper, ITestUserService testUserService, CaseCollectionMapper collectionMapper) {
        this.testCaseMapper = testCaseMapper;
        this.testUserMapper = testUserMapper;
        this.testUserService = testUserService;
        this.collectionMapper = collectionMapper;
    }

    /**
     * 添加测试用例,会初始化用例信息,默认从moco数据中读取,会记录除host以外的测试用例运行必需信息
     *
     * @param bean
     * @return
     */
    @Override
    public int addCase(EditCaseAttributeBean bean) {
        int i = testCaseMapper.addCase(bean);
        List<Integer> projectList = bean.getProjectList();
        if (i > 0) {
            if (bean.getId() != null)
                addEditRecord(new CaseEditRecord(bean.getId(), bean.getUid(), CaseEditType.CREATE.getCode()));
            addCaseProjectRelation(bean);
        } else {
            CaseException.fail(TestCaseCode.ADD_CASE_FAIL.getDesc());
        }
        return i;
    }

    /**
     * 复制测试用例,完全复制,除了uid和editor
     *
     * @param bean
     * @return
     */
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

    /**
     * 更新测试用例属性
     *
     * @param bean
     * @return
     */
    @Override
    public int updateCase(EditCaseAttributeBean bean) {
        int apiId = testCaseMapper.getCaseApiId(bean.getId());
        int i = apiId == bean.getApiId() ? testCaseMapper.updateCaseNoApiId(bean) : testCaseMapper.updateCase(bean);
        addEditRecord(new CaseEditRecord(bean.getId(), bean.getUid(), CaseEditType.EDIT_ATTRIBUTE.getCode()));
        updateCaseProjectRelation(bean);
        if (i == 1) syncApi(bean.getId());
        return i;
    }


    /**
     * 删除测试用例
     *
     * @param bean
     * @return
     */
    public int delCase(DelBean bean) {
        int i = testCaseMapper.delCase(bean);
        if (i > 0) {
            delAllCaseCollectionRelation(bean.getId());
            delCaseProjectRelation(bean);
        }
        return i;
    }

    /**
     * 添加测试用例和项目的关联
     *
     * @param bean
     */
    @Async
    @Override
    public void addCaseProjectRelation(EditCaseAttributeBean bean) {
        if (bean.getProjectList() == null || bean.getProjectList().size() == 0) return;
        testCaseMapper.addCaseProjectRelation(bean);
    }

    /**
     * 删除测试用例和项目的关联,此处用于删除用例和更新用例关联的项目(先删除后添加)
     *
     * @param bean
     */
    @Override
    public void delCaseProjectRelation(DelBean bean) {
        testCaseMapper.delCaseProjectRelation(bean);
    }

    /**
     * 更新用例关联的项目信息
     *
     * @param bean
     */
    @Async
    @Override
    public void updateCaseProjectRelation(EditCaseAttributeBean bean) {
        DelBean delBean = new DelBean();
        bean.copyTo(delBean);
        delCaseProjectRelation(delBean);
        addCaseProjectRelation(bean);
    }

    /**
     * 复制用例关联的信息,用于拷贝测试用例
     *
     * @param source
     * @param target
     */
    @Async
    @Override
    public void copyCaseProjectRelation(int source, int target) {
        testCaseMapper.copyCaseProjectRelation(source, target);
    }

    /**
     * 添加用例编辑记录
     *
     * @param record
     */
    @Async
    @Override
    public void addEditRecord(CaseEditRecord record) {
        testCaseMapper.addEditRecord(record);
    }

    /**
     * 更新用例数据
     *
     * @param bean
     * @return
     */
    @Override
    public int updateCaseData(CaseDataBean bean) {
        int i = testCaseMapper.updateCaseData(bean);
        if (i > 0) addEditRecord(new CaseEditRecord(bean.getId(), bean.getUid(), CaseEditType.EDIT_DATA.getCode()));
        return i;
    }

    /**
     * 搜索测试用例,无分页
     *
     * @param bean
     * @return
     */
    @Override
    public List<SimpleBean> searchCaseNoPage(CaseSearchNoPageBean bean) {
        List<SimpleBean> simpleBeans = testCaseMapper.searchCaseNoPage(bean);
        return simpleBeans;
    }

    /**
     * 搜索测试用例,分页
     *
     * @param bean
     * @return
     */
    @Override
    public PageInfo<TestCaseListBean> searchCases(CaseSearchBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        List<TestCaseListBean> testCaseBeans = testCaseMapper.searchCases(bean);
        PageInfo<TestCaseListBean> pageInfo = new PageInfo(testCaseBeans);
        return pageInfo;
    }

    /**
     * 获取测试用例属性信息
     *
     * @param id
     * @return
     */
    @Override
    public TestCaseAttributeBean getAttributeById(int id) {
        TestCaseAttributeBean bean = new TestCaseAttributeBean();
        bean.setId(id);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        getAttributeById(bean, countDownLatch);
        getCaseProjectRelation(bean, countDownLatch);
        try {
            countDownLatch.await(OkayConstant.SYNC_WAIT_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            CaseException.fail("查询用例信息发生错误:" + e.getMessage());
        }
        return bean;
    }

    /**
     * 异步查询用例属性信息,不包括关联项目
     *
     * @param bean
     * @param countDownLatch
     */
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

    /**
     * 异步查询用例关联的项目信息
     *
     * @param bean
     * @param countDownLatch
     */
    @Async
    @Override
    public void getCaseProjectRelation(TestCaseAttributeBean bean, CountDownLatch countDownLatch) {
        try {
            bean.setVersionList(testCaseMapper.getCaseProjectRelation(bean.getId()));
        } finally {
            countDownLatch.countDown();
        }
    }

    /**
     * 获取用例编辑记录历史
     *
     * @param bean
     * @return
     */
    @Override
    public PageInfo<CaseEditRetrunRecord> getCaseEditRecords(CaseEditRecordQueryBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        List<CaseEditRetrunRecord> caseEditRecords = testCaseMapper.getCaseEditRecords(bean);
        PageInfo<CaseEditRetrunRecord> pageInfo = new PageInfo(caseEditRecords);
        return pageInfo;
    }

    /**
     * 获取用力详情,包括用例数据
     *
     * @param id
     * @return
     */
    @Override
    public CaseDetailBean getCaseDetail(int id) {
        CaseDetailBean caseDetail = testCaseMapper.getCaseDetail(id);
        return caseDetail;
    }

    /**
     * 该方法用于直接运行数据,而非某一个用例,故无需校验case的available
     *
     * @param bean
     * @param bean
     * @return
     */
    @Override
    public CaseRunRecord runCaseData(CaseDataBean bean) {
        handleParams(bean);
        CaseRunRecord record = new CaseRunRecord();
        record.setRunId(Constant.TEST_ERROR_CODE);
        record.setUid(bean.getUid());
        record.setParams(bean.getParams());
        record.setCaseId(Constant.TEST_ERROR_CODE);
        record.setMark(OkayConstant.RUN_MARK.getAndIncrement());
        bean.getHeaders().put(OkayConstant.MARK_HEADER, record.getMark());
        record.setHeaders(bean.getHeaders());
        RunCaseUtil.run(bean, record);
        addRunRecord(record);
        return record;
    }

    /**
     * 添加用例的运行记录,这里包括运行测试用例和测试数据
     *
     * @param runRecord
     */
    @Async
    @Override
    public void addRunRecord(CaseRunRecord runRecord) {
        logger.warn("添加运行记录:{}", runRecord.toString());
        testCaseMapper.addRunRecord(runRecord);
    }

    /**
     * 处理测试数据中的标记语法
     *
     * @param bean
     */
    @Override
    public void handleParams(CaseDataBean bean) {
        JSONObject params = bean.getParams();
        JSONObject headers = bean.getHeaders();
        handleParams(params);
        handleParams(headers);
    }

    /**
     * 处理测试数据中的标记语法,用map缓存,用于运行测试用例集
     *
     * @param bean
     * @param map
     */
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
            String value = params.getString(key).trim();
            if (value.startsWith(OkayConstant.USER_CERTIFICATE_KEY)) {
                int id = SourceCode.changeStringToInt(value.substring(OkayConstant.USER_CERTIFICATE_KEY.length()));
                TestUserCheckBean userCheckBean = testUserService.getCertificate(id);
                String certificate = userCheckBean.getCertificate();
                if (StringUtils.isEmpty(certificate)) UserStatusException.fail();
                params.put(key, certificate);
            } else if (value.startsWith(OkayConstant.RANDOM_KEY)) {
                String replace = value.replace(OkayConstant.RANDOM_KEY, Constant.EMPTY);
                String[] split = replace.split(OkayConstant.COMMA, 2);
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
            String value = params.getString(key).trim();
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

    /**
     * 异步删除用例和用例集关联,用于删除测试用例
     *
     * @param id
     */
    @Async
    @Override
    public void delAllCaseCollectionRelation(int id) {
        collectionMapper.delAllCaseCollectionRelation(id);
    }

    /**
     * 获取用例的运行记录,用例集运行详情出用到
     *
     * @param id
     * @return
     */
    @Override
    public CaseRunDetailBean getCaseRunRecord(int id) {
        CaseRunDetailBean caseRunRecord = testCaseMapper.getCaseRunRecord(id);
        return caseRunRecord;
    }

    @Async
    @Override
    public void syncApi(int caseId) {
        testCaseMapper.syncApi(caseId);
    }


}
