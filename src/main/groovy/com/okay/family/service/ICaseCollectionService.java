package com.okay.family.service;

import com.okay.family.common.bean.casecollect.request.*;
import com.okay.family.common.bean.casecollect.response.CollectionRunSimpleResutl;
import com.okay.family.common.bean.casecollect.response.ListCaseBean;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.testcase.request.CaseDataBean;

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

    CollectionRunSimpleResutl runCollection(RunCollectionBean bean);

    void addCollectionRunRecord(CollectionRunSimpleResutl record);

    List<CaseDataBean> getCasesDeatil(RunCollectionBean bean);

}
