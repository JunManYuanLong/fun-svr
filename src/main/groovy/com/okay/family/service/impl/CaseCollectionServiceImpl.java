package com.okay.family.service.impl;

import com.okay.family.common.bean.casecollect.CaseCollectionBean;
import com.okay.family.common.bean.casecollect.CaseCollectionRecord;
import com.okay.family.common.bean.casecollect.CaseCollectionRelationBean;
import com.okay.family.common.bean.testcase.response.TestCaseAttributeBean;
import com.okay.family.common.exception.CaseCollecionException;
import com.okay.family.mapper.CaseCollectionMapper;
import com.okay.family.mapper.TestCaseMapper;
import com.okay.family.service.ICaseCollectionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CaseCollectionServiceImpl implements ICaseCollectionService {

    private static Logger logger = LoggerFactory.getLogger(CaseCollectionServiceImpl.class);

    TestCaseMapper testCaseMapper;

    CaseCollectionMapper caseCollectionMapper;

    @Autowired
    public CaseCollectionServiceImpl(TestCaseMapper testCaseMapper, CaseCollectionMapper caseCollectionMapper) {
        this.testCaseMapper = testCaseMapper;
        this.caseCollectionMapper = caseCollectionMapper;
    }

    @Override
    public int addCollection(CaseCollectionBean bean) {
        int i = caseCollectionMapper.addCollection(bean);
        if (i != 1 || bean.getId() < 1000) CaseCollecionException.fail("添加用例集错误!");
        Set<Integer> caseids = bean.getCaseids();
        List<CaseCollectionRelationBean> list = new ArrayList<>();
        caseids.forEach(x -> {
            CaseCollectionRelationBean e = new CaseCollectionRelationBean();
            e.setCaseid(x);
            e.setCollectionid(bean.getId());
            list.add(e);
        });
        int i1 = caseCollectionMapper.addRelation(list);
        return i1;
    }

    @Override
    public int addCollectionRelation(List<CaseCollectionRelationBean> beans) {
        int i = caseCollectionMapper.addRelation(beans);
        return i;
    }

    @Override
    public int update(CaseCollectionBean bean) {
        return 0;
    }

    @Override
    public int addEditReord(CaseCollectionRecord record) {
        caseCollectionMapper.addEditReord(record);
        return 0;
    }

    @Override
    public List<CaseCollectionBean> getCollections(int uid) {
        return null;
    }

    @Override
    public CaseCollectionBean getCollectionInfo(int collectionid) {
        return null;
    }

    @Override
    public List<TestCaseAttributeBean> getCases(int id) {
        return null;
    }


}
