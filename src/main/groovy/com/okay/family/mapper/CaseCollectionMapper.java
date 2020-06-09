package com.okay.family.mapper;

import com.okay.family.common.bean.casecollect.CaseCollectionRecord;
import com.okay.family.common.bean.testcase.RunCaseRecordBean;

import java.util.List;

public interface CaseCollectionMapper {

    int add(List<CaseCollectionRecord> records);

    //todo:待完成
    int addEditReord(CaseCollectionRecord bean);

    //todo:待完成
    void addRunReord(RunCaseRecordBean record);


}
