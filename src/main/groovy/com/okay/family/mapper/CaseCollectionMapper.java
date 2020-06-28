package com.okay.family.mapper;

import com.okay.family.common.bean.casecollect.request.*;
import com.okay.family.common.bean.casecollect.response.CollectionRunSimpleResutl;
import com.okay.family.common.bean.casecollect.response.ListCaseBean;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.testcase.request.CaseDataBean;

import java.util.List;

public interface CaseCollectionMapper {

    int addCollection(AddCollectionBean bean);

    void addCollectionCaseRelation(AddCollectionBean beans);

    void addEditRcord(CaseCollectionEditRecord record);

    int delCollection(DelBean bean);

    void delCollectionCaseRelation(DelBean bean);

    int editCollection(CollectionEditBean bean);

    int shareCollection(CollectionEditBean bean);

    int delCaseFromCollection(DelCaseCollectionRelationBean bean);

    List<ListCaseBean> getCases(int collectionId, int uid);

    //todo:待完成
    List<CaseDataBean> getCasesDeatil(RunCollectionBean bean);

    //todo:待完成
    void addCollectionRunRecord(CollectionRunSimpleResutl record);

}
