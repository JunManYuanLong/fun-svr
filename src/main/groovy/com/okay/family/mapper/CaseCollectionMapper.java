package com.okay.family.mapper;

import com.okay.family.common.bean.casecollect.CaseCollectionRecord;

import java.util.List;

public interface CaseCollectionMapper {

    int add(List<CaseCollectionRecord> records);

}
