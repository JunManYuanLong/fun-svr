package com.okay.family.service.impl;

import com.okay.family.common.CaseRunThread;
import com.okay.family.common.OkayThreadPool;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.bean.casecollect.request.*;
import com.okay.family.common.bean.casecollect.response.CollectionRunSimpleResutl;
import com.okay.family.common.bean.casecollect.response.ListCaseBean;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.enums.CollectionEditType;
import com.okay.family.common.exception.CaseCollecionException;
import com.okay.family.common.exception.CommonException;
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
        if (bean.getId() > 1)
            addCollectionEditRecord(new CaseCollectionEditRecord(bean.getId(), bean.getUid(), CollectionEditType.CREATE.getCode()));
        return bean.getId();
    }

    @Async
    @Override
    public void addCollectionCaseRelation(AddCollectionBean bean) {
        caseCollectionMapper.addCollectionCaseRelation(bean);
    }

    @Async
    @Override
    public void addCollectionEditRecord(CaseCollectionEditRecord record) {
        caseCollectionMapper.addEditRcord(record);
    }

    @Override
    public int editCollection(CollectionEditBean bean) {
        int i = caseCollectionMapper.editCollection(bean);
        return i;
    }

    @Override
    public int shareCollection(CollectionEditBean bean) {
        int i = caseCollectionMapper.shareCollection(bean);
        return i;
    }

    @Override
    public int delCollection(DelBean bean) {
        int i = caseCollectionMapper.delCollection(bean);
        delCollectionCaseRelation(bean);
        return i;
    }

    @Async
    @Override
    public void delCollectionCaseRelation(DelBean bean) {
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
    public List<ListCaseBean> getCases(int collectionId, int uid) {
        List<ListCaseBean> cases = caseCollectionMapper.getCases(collectionId, uid);
        return cases;
    }

    @Override
    public CollectionRunSimpleResutl runCollection(RunCollectionBean bean) {
        List<CaseDataBean> casesDeatil = getCasesDeatil(bean);
        List<CaseDataBean> cases = casesDeatil.stream().filter(x -> x.getEnvId() == bean.getEnvId()).collect(Collectors.toList());
        CountDownLatch countDownLatch = new CountDownLatch(cases.size());
        int andIncrement = OkayConstant.COLLECTION_MARK.getAndIncrement();
        List<CaseRunThread> results = new ArrayList<>();
        String start = Time.getDate();
        cases.forEach(x -> {
            CaseRunThread caseRunThread = new CaseRunThread(x, countDownLatch, andIncrement);
            OkayThreadPool.addSyncWork(caseRunThread);
            results.add(caseRunThread);
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            CommonException.fail("执行用例集失败!");
        }
        String end = Time.getDate();
        //todo:处理结果,记录结果,返回结果
        results.forEach(x -> caseService.addRunRecord(x.getRecord()));
        Map<Integer, List<Integer>> collect = results.stream().map(x -> x.getRecord().getResult()).collect(Collectors.groupingBy(x -> x));
        CollectionRunSimpleResutl res = new CollectionRunSimpleResutl();
        //todo:初始化res

        return res;
    }

    @Async
    @Override
    public void addCollectionRunRecord(CollectionRunSimpleResutl record) {
        caseCollectionMapper.addCollectionRunRecord(record);
    }

    @Override
    public List<CaseDataBean> getCasesDeatil(RunCollectionBean bean) {
        ConcurrentHashMap<Integer, String> certificates = new ConcurrentHashMap<>();
        List<CaseDataBean> cases = caseCollectionMapper.getCasesDeatil(bean);
        CountDownLatch countDownLatch = new CountDownLatch(cases.size());
        cases.forEach(x -> {
            new Thread(() -> {
                try {
                    caseService.handleParams(x, certificates);
                } finally {
                    countDownLatch.countDown();
                }
            }).start();
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            CommonException.fail("初始化用例信息失败!");
        }
        return cases;
    }


}
