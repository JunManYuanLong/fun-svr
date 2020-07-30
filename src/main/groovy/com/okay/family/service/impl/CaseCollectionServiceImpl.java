package com.okay.family.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.casecollect.request.*;
import com.okay.family.common.bean.casecollect.response.CollectionCaseInfoBean;
import com.okay.family.common.bean.casecollect.response.CollectionRunResultDetailBean;
import com.okay.family.common.bean.casecollect.response.CollectionRunSimpleResutl;
import com.okay.family.common.bean.casecollect.response.ListCollectionBean;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.common.SimpleBean;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.bean.testcase.response.ListCaseRunResultBean;
import com.okay.family.common.enums.CollectionEditType;
import com.okay.family.common.enums.CollectionStatus;
import com.okay.family.common.enums.RunResult;
import com.okay.family.common.exception.CaseCollecionException;
import com.okay.family.common.exception.CaseException;
import com.okay.family.common.exception.CommonException;
import com.okay.family.common.threadpool.CaseParamaThread;
import com.okay.family.common.threadpool.CaseRunThread;
import com.okay.family.common.threadpool.OkayThreadPool;
import com.okay.family.fun.utils.Time;
import com.okay.family.mapper.CaseCollectionMapper;
import com.okay.family.service.ICaseCollectionService;
import com.okay.family.service.ITestCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class CaseCollectionServiceImpl implements ICaseCollectionService {

    private static Logger logger = LoggerFactory.getLogger(CaseCollectionServiceImpl.class);

    CaseCollectionMapper caseCollectionMapper;

    ITestCaseService caseService;

    public CaseCollectionServiceImpl(CaseCollectionMapper caseCollectionMapper, ITestCaseService caseService) {
        this.caseCollectionMapper = caseCollectionMapper;
        this.caseService = caseService;
    }

    /**
     * 添加用例集
     *
     * @param bean
     * @return
     */
    @Override
    public int addCollection(AddCollectionBean bean) {
        int i = caseCollectionMapper.addCollection(bean);
        if (i < 1) CaseCollecionException.fail("添加用例集失败!");
        addCollectionCaseRelation(bean);
        addCollectionEditRecord(new CaseCollectionEditRecord(bean.getId(), bean.getUid(), CollectionEditType.CREATE.getCode()));
        return i;
    }

    /**
     * 添加用例集和用例关联
     *
     * @param bean
     */
    @Async
    @Override
    public void addCollectionCaseRelation(AddCollectionBean bean) {
        if (bean.getId() != null) {
            List<Integer> caseIds = caseCollectionMapper.getCaseIds(bean.getId());
            bean.getApiList().removeAll(caseIds);
        }
        caseCollectionMapper.addCollectionCaseRelation(bean);
        addCollectionEditRecord(new CaseCollectionEditRecord(bean.getId(), bean.getUid(), CollectionEditType.ADD_CASE.getCode()));
    }

    /**
     * 添加用例集编辑记录
     *
     * @param record
     */
    @Async
    @Override
    public void addCollectionEditRecord(CaseCollectionEditRecord record) {
        caseCollectionMapper.addEditRcord(record);
    }

    @Override
    public int editCollection(CollectionEditBean bean) {
        int i = caseCollectionMapper.editCollection(bean);
        addCollectionEditRecord(new CaseCollectionEditRecord(bean.getGroupId(), bean.getUid(), CollectionEditType.EDIT_NAME.getCode()));
        return i;
    }

    /**
     * 分享用例集
     *
     * @param bean
     * @return
     */
    @Override
    public int shareCollection(DelCollectionBean bean) {
        int i = caseCollectionMapper.shareCollection(bean);
        if (i > 0)
            addCollectionEditRecord(new CaseCollectionEditRecord(bean.getGroupId(), bean.getUid(), CollectionEditType.SHARE.getCode()));
        return i;
    }

    /**
     * 删除用例集,先删除用例集,在删除用例集和用例关联关系
     *
     * @param bean
     * @return
     */
    @Override
    public int delCollection(DelCollectionBean bean) {
        int i = caseCollectionMapper.delCollection(bean);
        delCollectionCaseRelation(bean);
        return i;
    }

    /**
     * 删除用例集和用例的关联关系
     *
     * @param bean
     */
    @Async
    @Override
    public void delCollectionCaseRelation(DelCollectionBean bean) {
        caseCollectionMapper.delCollectionCaseRelation(bean);
    }

    /**
     * 删除单个用例和用例集的关联关系
     *
     * @param bean
     * @return
     */
    @Override
    public int delCaseFromCollection(DelCaseCollectionRelationBean bean) {
        int i = caseCollectionMapper.delCaseFromCollection(bean);
        if (i > 0)
            addCollectionEditRecord(new CaseCollectionEditRecord(bean.getGroupId(), bean.getUid(), CollectionEditType.DEL_CASE.getCode()));
        return i;
    }

    /**
     * 获取用例集中case列表
     *
     * @param collectionId
     * @param uid
     * @return
     */
    @Override
    public CollectionCaseInfoBean getCases(int collectionId, int uid) {
        CollectionCaseInfoBean infoBean = new CollectionCaseInfoBean();
        infoBean.setId(collectionId);
        infoBean.setUid(uid);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        getCollectionInfo(infoBean, countDownLatch);
        infoBean.setList(caseCollectionMapper.getCases(collectionId, uid));
        try {
            countDownLatch.await(OkayConstant.SYNC_WAIT_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            CommonException.fail("查询用例集关联用例出错");
        }
        return infoBean;
    }

    /**
     * 异步获取用例集信息
     *
     * @param bean
     * @param countDownLatch
     */
    @Async
    @Override
    public void getCollectionInfo(CollectionCaseInfoBean bean, CountDownLatch countDownLatch) {
        try {
            bean.copyFrom(caseCollectionMapper.getCollectionInfo(bean));
        } catch (Exception e) {
            logger.error("异步查询collecionName失败");
        } finally {
            countDownLatch.countDown();
        }
    }

    /**
     * 运行用例集,所有用例添加运行记录
     * <p>先从数据库中读取测试用例数据,然后多线程处理接口参数中的语法标记,然后在组装成用处运行的多线程对象,用线程池运行完之后统计测试结果
     * </p>
     *
     * @param bean
     * @return 返回一个运行记录的简单统计结果
     */
    @Override
    public CollectionRunSimpleResutl runCollection(RunCollectionBean bean) {
        List<CaseDataBean> cases = getCasesDeatil(bean);
        CountDownLatch countDownLatch = new CountDownLatch(cases.size());
        int runId = OkayConstant.COLLECTION_MARK.getAndIncrement();
        Vector<CaseRunThread> results = new Vector<>();
        String start = Time.getDate();
        cases.forEach(x -> {
            x.setUid(bean.getUid());//设置执行用户ID
            CaseRunThread caseRunThread = new CaseRunThread(x, countDownLatch, runId, bean.getEnvId());
            OkayThreadPool.addSyncWork(caseRunThread);
            results.add(caseRunThread);
        });
        try {
            countDownLatch.await(OkayConstant.SYNC_WAIT_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            CaseCollecionException.fail("执行用例集失败!");
        }
        String end = Time.getDate();
        results.forEach(x -> caseService.addRunRecord(x.getRecord()));
        Map<Integer, List<Integer>> collect = results.stream().map(x -> x.getRecord().getResult()).collect(Collectors.groupingBy(x -> x));
        int success = collect.getOrDefault(RunResult.SUCCESS.getCode(), new ArrayList<>(0)).size();
        int fail = collect.getOrDefault(RunResult.FAIL.getCode(), new ArrayList<>(0)).size();
        int unrun = collect.getOrDefault(RunResult.UNRUN.getCode(), new ArrayList<>(0)).size();
        int userError = collect.getOrDefault(RunResult.USER_ERROR.getCode(), new ArrayList<>(0)).size();
        CollectionStatus collectionStatus = cases.size() == success ? CollectionStatus.SUCCESS : CollectionStatus.FAIL;
        CollectionRunSimpleResutl res = new CollectionRunSimpleResutl();
        res.setRunId(runId);
        res.setCaseNum(cases.size());
        res.setSuccess(success);
        res.setStart(start);
        res.setEnd(end);
        res.setResult(collectionStatus.getDesc());
        res.setFail(fail);
        res.setUnrun(unrun);
        res.setUserError(userError);
        CollectionRunResultRecord record = new CollectionRunResultRecord();
        record.copyFrom(res);
        record.setCollectionId(bean.getGroupId());
        record.setUid(bean.getUid());
        record.setEnvId(bean.getEnvId());
        record.setResult(collectionStatus.getCode());
        updateCollectionStatus(bean.getGroupId(), collectionStatus.getCode());
        addCollectionRunRecord(record);
        return res;
    }

    /**
     * 保存用例集运行记录
     *
     * @param record
     */
    @Async
    @Override
    public void addCollectionRunRecord(CollectionRunResultRecord record) {
        caseCollectionMapper.addCollectionRunRecord(record);
    }

    /**
     * 从数据库里面读取用例集详情,用于运行
     *
     * @param bean
     * @return
     */
    @Override
    public List<CaseDataBean> getCasesDeatil(RunCollectionBean bean) {
        ConcurrentHashMap<Integer, String> certificates = new ConcurrentHashMap<>();
        List<CaseDataBean> cases = caseCollectionMapper.getCasesDeatil(bean);
        CountDownLatch countDownLatch = new CountDownLatch(cases.size());
        cases.forEach(x->{
            CaseParamaThread caseParamaThread = new CaseParamaThread(x, certificates, countDownLatch);
            OkayThreadPool.addSyncWork(caseParamaThread);
        });
        try {
            countDownLatch.await(OkayConstant.SYNC_WAIT_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            CaseException.fail("初始化用例信息失败!");
        }
        return cases;
    }

    /**
     * 更新用例集运行状态
     *
     * @param id
     * @param status
     */
    @Async
    @Override
    public void updateCollectionStatus(int id, int status) {
        caseCollectionMapper.updateCollectionStatus(id, status);
    }

    /**
     * 搜索测试用例集,分页
     *
     * @param bean
     * @return
     */
    @Override
    public PageInfo<ListCollectionBean> findCollecions(SearchCollectionBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        List<ListCollectionBean> collecions = caseCollectionMapper.findCollecions(bean);
        PageInfo<ListCollectionBean> pageInfo = new PageInfo(collecions);
        return pageInfo;
    }

    /**
     * 获取所有用例集
     *
     * @param bean
     * @return
     */
    @Override
    public List<SimpleBean> searchCollectionNoPage(SearchCollectionNoPageBean bean) {
        return caseCollectionMapper.searchCollectionNoPage(bean);
    }

    /**
     * 获取用例集运行记录列表
     *
     * @param bean
     * @return
     */
    @Override
    public List<SimpleBean> getRecords(DelBean bean) {
        List<SimpleBean> records = caseCollectionMapper.getRecords(bean);
        return records;
    }

    /**
     * 获取用例集运行详情
     *
     * @param runId
     * @return
     */
    @Override
    public CollectionRunResultDetailBean getCollectionRunDetail(int runId) {
        CollectionRunResultDetailBean detailBean = new CollectionRunResultDetailBean();
        detailBean.setRunId(runId);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        getCollectionRunResult(detailBean, countDownLatch);
        getCaseRunRecord(detailBean, countDownLatch);
        try {
            countDownLatch.await(OkayConstant.SYNC_WAIT_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            CommonException.fail("查询用例集运行详情失败!");
        }
        return detailBean;
    }

    /**
     * 获取用例运行记录
     *
     * @param bean
     * @param countDownLatch
     */
    @Async
    @Override
    public void getCaseRunRecord(CollectionRunResultDetailBean bean, CountDownLatch countDownLatch) {
        try {
            List<ListCaseRunResultBean> caseRunRecord = caseCollectionMapper.getCaseRunRecord(bean.getRunId());
            if (caseRunRecord == null || caseRunRecord.isEmpty() || caseRunRecord.get(0) == null) bean.setCaseList(new ArrayList<>(0));
            else bean.setCaseList(caseRunRecord);
        } catch (Exception e) {
            logger.error("查询用例集中用例运行结果失败", e);
            CommonException.fail("查询用例集中用例运行结果失败");
        } finally {
            countDownLatch.countDown();
        }
    }


    /**
     * 获取用例集运行详情
     *
     * @param bean
     * @param countDownLatch
     */
    @Async
    @Override
    public void getCollectionRunResult(CollectionRunResultDetailBean bean, CountDownLatch countDownLatch) {
        try {
            bean.copyFrom(caseCollectionMapper.getCollectionRunDetail(bean.getRunId()));
        } catch (Exception e) {
            logger.error("查询用例集运行结果失败", e);
            CommonException.fail("查询用例集运行结果失败");
        } finally {
            countDownLatch.countDown();
        }
    }


}
