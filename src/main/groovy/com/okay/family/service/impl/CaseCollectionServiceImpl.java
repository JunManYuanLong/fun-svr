package com.okay.family.service.impl;

import com.okay.family.common.bean.casecollect.CaseCollectionBean;
import com.okay.family.mapper.TestCaseMapper;
import com.okay.family.service.ICaseCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseCollectionServiceImpl implements ICaseCollectionService {

    TestCaseMapper testCaseMapper;

    @Autowired
    public CaseCollectionServiceImpl(TestCaseMapper testCaseMapper) {
        this.testCaseMapper = testCaseMapper;
    }

    @Override
    public int add(CaseCollectionBean bean) {
        return 0;
    }

    @Override
    public int update(CaseCollectionBean bean) {
        return 0;
    }


}
