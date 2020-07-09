package com.okay.family.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okay.family.common.CaseRunThread;
import com.okay.family.common.OkayThreadPool;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.casecollect.request.*;
import com.okay.family.common.bean.casecollect.response.CollectionCaseInfoBean;
import com.okay.family.common.bean.casecollect.response.CollectionRunResultDetailBean;
import com.okay.family.common.bean.casecollect.response.CollectionRunSimpleResutl;
import com.okay.family.common.bean.casecollect.response.ListCollectionBean;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.common.SimpleBean;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.bean.casecollect.request.DelCollectionBean;
import com.okay.family.common.enums.CaseAvailableStatus;
import com.okay.family.common.enums.CollectionEditType;
import com.okay.family.common.enums.CollectionStatus;
import com.okay.family.common.enums.RunResult;
import com.okay.family.common.exception.CaseCollecionException;
import com.okay.family.common.exception.CaseException;
import com.okay.family.common.exception.CommonException;
import com.okay.family.common.exception.UserStatusException;
import com.okay.family.fun.utils.Time;
import com.okay.family.mapper.CaseCollectionMapper;
import com.okay.family.service.ICaseCollectionService;
import com.okay.family.service.ITestCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.stream.Collectors;

@Service
@Transactional(rollbackFor = Exception.class)
public class CaseCollectionServiceImpl implements ICaseCollectionService {

    private static Logger logger = LoggerFactory.getLogger(CaseCollectionServiceImpl.class);

    CaseCollectionMapper caseCollectionMapper;

    ITestCaseService caseService;

    @Autowired
    public CaseCollectionServiceImpl(CaseCollectionMapper caseCollectionMapper, ITestCaseService caseService) {
        this.caseCollectionMapper = caseCollectionMapper;
        this.caseService = caseService;
    }

    @Override
    public int addCollection(AddCollectionBean bean) {
        int i = caseCollectionMapper.addCollection(bean);
        if (i < 1) CaseCollecionException.fail("添加用例集失败!");
        addCollectionCaseRelation(bean);
        addCollectionEditRecord(new CaseCollectionEditRecord(bean.getId(), bean.getUid(), CollectionEditType.CREATE.getCode()));
        return i;
    }

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

    @Async
    @Override
    public void delCollectionCaseRelation(DelCollectionBean bean) {
        caseCollectionMapper.delCollectionCaseRelation(bean);
    }

    @Override
    public int delCaseFromCollection(DelCaseCollectionRelationBean bean) {
        int i = caseCollectionMapper.delCaseFromCollection(bean);
        if (i > 0)
            addCollectionEditRecord(new CaseCollectionEditRecord(bean.getGroupId(), bean.getUid(), CollectionEditType.DEL_CASE.getCode()));
        return i;
    }

    @Override
    public CollectionCaseInfoBean getCases(int collectionId, int uid) {
        CollectionCaseInfoBean infoBean = new CollectionCaseInfoBean();
        infoBean.setId(collectionId);
        CountDownLatch countDownLatch = new CountDownLatch(1);
        getCollectionInfo(infoBean, countDownLatch);
        infoBean.setList(caseCollectionMapper.getCases(collectionId, uid));
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            CommonException.fail("查询用例集关联用例出错");
        }
        return infoBean;
    }

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

    @Override
    public CollectionRunSimpleResutl runCollection(RunCollectionBean bean) {
        List<CaseDataBean> casesDeatil = getCasesDeatil(bean);
        int userErrorNum = casesDeatil.stream().filter(x -> x.getAvailable() == RunResult.USER_ERROR.getCode()).collect(Collectors.toList()).size();
        List<CaseDataBean> cases = casesDeatil.stream().filter(x -> x.getEnvId() == bean.getEnvId() && x.getAvailable() == CaseAvailableStatus.OK.getCode()).collect(Collectors.toList());
        CountDownLatch countDownLatch = new CountDownLatch(cases.size());
        int runId = OkayConstant.COLLECTION_MARK.getAndIncrement();
        List<CaseRunThread> results = new ArrayList<>();
        String start = Time.getDate();
        cases.forEach(x -> {
            x.setUid(bean.getUid());//设置执行用户ID
            CaseRunThread caseRunThread = new CaseRunThread(x, countDownLatch, runId);
            OkayThreadPool.addSyncWork(caseRunThread);
            results.add(caseRunThread);
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            CaseCollecionException.fail("执行用例集失败!");
        }
        String end = Time.getDate();
        results.forEach(x -> caseService.addRunRecord(x.getRecord()));
        Map<Integer, List<Integer>> collect = results.stream().map(x -> x.getRecord().getResult()).collect(Collectors.groupingBy(x -> x));
        int success = collect.getOrDefault(RunResult.SUCCESS.getCode(), new ArrayList<>(0)).size();
        int fail = collect.getOrDefault(RunResult.FAIL.getCode(), new ArrayList<>(0)).size();
        int unrun = collect.getOrDefault(RunResult.UNRUN.getCode(), new ArrayList<>(0)).size() - userErrorNum;
        int userError = collect.getOrDefault(RunResult.USER_ERROR.getCode(), new ArrayList<>(0)).size() + userErrorNum;
        CollectionStatus collectionStatus = casesDeatil.size() == success ? CollectionStatus.SUCCESS : CollectionStatus.FAIL;
        CollectionRunSimpleResutl res = new CollectionRunSimpleResutl();
        res.setRunId(runId);
        res.setCaseNum(casesDeatil.size());
        res.setSuccess(success);
        res.setStart(start);
        res.setEnd(end);
        res.setResult(collectionStatus.getDesc());
        res.setFail(fail);
        res.setUnrun(casesDeatil.size() - cases.size() + unrun);
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
        cases.forEach(x -> {
            new Thread(() -> {
                try {
                    caseService.handleParams(x, certificates);
                } catch (UserStatusException e) {
                    x.setAvailable(RunResult.USER_ERROR.getCode());
                } catch (Exception e) {
                    logger.error("处理用例参数发生错误!", e);
                    x.setAvailable(RunResult.UNRUN.getCode());
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            CaseException.fail("初始化用例信息失败!");
        }
        return cases;
    }

    @Async
    @Override
    public void updateCollectionStatus(int id, int status) {
        caseCollectionMapper.updateCollectionStatus(id, status);
    }

    @Override
    public PageInfo<ListCollectionBean> findCollecions(SearchCollectionBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        List<ListCollectionBean> collecions = caseCollectionMapper.findCollecions(bean);
        PageInfo<ListCollectionBean> pageInfo = new PageInfo(collecions);
        return pageInfo;
    }

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

    @Override
    public CollectionRunResultDetailBean getCollectionRunDetail(int runId) {
        CollectionRunResultDetailBean detailBean = new CollectionRunResultDetailBean();
        detailBean.setRunId(runId);
        CountDownLatch countDownLatch = new CountDownLatch(2);
        getCollectionRunResult(detailBean, countDownLatch);
        getCaseRunRecord(detailBean, countDownLatch);
        try {
            countDownLatch.await();
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
            bean.setCaseList(caseCollectionMapper.getCaseRunRecord(bean.getRunId()));
        } catch (Exception e) {
            logger.error("查询用例集中用例运行结果失败", e);
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
        } finally {
            countDownLatch.countDown();
        }
    }


}
