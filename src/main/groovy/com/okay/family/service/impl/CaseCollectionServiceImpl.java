package com.okay.family.service.impl;

import com.okay.family.common.CaseRunThread;
import com.okay.family.common.OkayThreadPool;
import com.okay.family.common.bean.casecollect.CollectionRunResultRecord;
import com.okay.family.common.bean.casecollect.request.*;
import com.okay.family.common.bean.casecollect.response.ListCaseBean;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.enums.CollectionEditType;
import com.okay.family.mapper.CaseCollectionMapper;
import com.okay.family.mapper.TestCaseMapper;
import com.okay.family.service.ICaseCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class CaseCollectionServiceImpl implements ICaseCollectionService {

    private static Logger logger = LoggerFactory.getLogger(CaseCollectionServiceImpl.class);

    CaseCollectionMapper caseCollectionMapper;

    TestCaseMapper testCaseMapper;

    @Autowired
    public CaseCollectionServiceImpl(CaseCollectionMapper caseCollectionMapper, TestCaseMapper testCaseMapper) {
        this.caseCollectionMapper = caseCollectionMapper;
        this.testCaseMapper = testCaseMapper;
    }

    @Override
    public int addCollection(AddCollectionBean bean) {
        int i = caseCollectionMapper.addCollection(bean);
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
    public void runCollection(RunCollectionBean bean) {
        List<CaseDataBean> cases = caseCollectionMapper.getCasesDeatil(bean);
        List<CaseRunThread> results = new ArrayList<>();
        cases.forEach(x -> {
            CaseRunThread caseRunThread = new CaseRunThread(x);
            OkayThreadPool.addSyncWord(caseRunThread);
            results.add(caseRunThread);
        });
        //todo:处理结果
    }

    @Async
    @Override
    public void addCollectionRunRecord(CollectionRunResultRecord record) {
        caseCollectionMapper.addCollectionRunRecord(record);
    }


}
