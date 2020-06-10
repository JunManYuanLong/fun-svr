package com.okay.family.mapper;

import com.okay.family.common.bean.casecollect.CaseCollectionBean;
import com.okay.family.common.bean.casecollect.CaseCollectionRecord;
import com.okay.family.common.bean.testcase.CaseRunRecord;

import java.util.List;

public interface CaseCollectionMapper {

    int add(CaseCollectionBean records);

    int share(CaseCollectionBean bean);

    //todo:待完成
    int update(CaseCollectionBean bean);

    //todo:待完成
    int addEditReord(CaseCollectionRecord bean);

    //todo:待完成
    void addRunReord(CaseRunRecord record);

    //todo:待完成
    List<CaseCollectionBean> findCollections(int environment, int uid);


}
