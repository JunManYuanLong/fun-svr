package com.okay.family.service;

import com.okay.family.common.bean.casecollect.request.AddCollectionBean;
import com.okay.family.common.bean.casecollect.request.CaseCollectionEditRecord;
import com.okay.family.common.bean.casecollect.request.CollectionEditBean;
import com.okay.family.common.bean.casecollect.request.DelCaseCollectionRelationBean;
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

}
