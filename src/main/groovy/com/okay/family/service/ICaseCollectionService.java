package com.okay.family.service;

import com.okay.family.common.bean.casecollect.request.*;
import com.okay.family.common.bean.casecollect.CollectionRunResultRecord;
import com.okay.family.common.bean.casecollect.response.ListCaseBean;
import com.okay.family.common.bean.common.DelBean;

import java.util.List;

public interface ICaseCollectionService {

    int addCollection(AddCollectionBean beans);

    void addCollectionCaseRelation(AddCollectionBean bean);

    void addCollectionEditRecord(CaseCollectionEditRecord record);

    int editCollection(CollectionEditBean bean);

    int shareCollection(CollectionEditBean bean);

    int delCollection(DelBean bean);

    void delCollectionCaseRelation(DelBean bean);

    int delCaseFromCollection(DelCaseCollectionRelationBean bean);

    List<ListCaseBean> getCases(int collectionId,int uid);

    void runCollection(RunCollectionBean bean);

    void addCollectionRunRecord(CollectionRunResultRecord record);

}
