package com.okay.family.mapper;

import com.okay.family.common.bean.casecollect.request.AddCollectionBean;
import com.okay.family.common.bean.casecollect.request.CaseCollectionEditRecord;
import com.okay.family.common.bean.casecollect.request.CollectionEditBean;
import com.okay.family.common.bean.casecollect.request.DelCaseCollectionRelationBean;
import com.okay.family.common.bean.casecollect.response.ListCaseBean;
import com.okay.family.common.bean.common.DelBean;

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

    //todo:完成
    List<ListCaseBean> getCases(int collectionId, int uid);


}
