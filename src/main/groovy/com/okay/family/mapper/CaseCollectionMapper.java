package com.okay.family.mapper;

import com.okay.family.common.bean.casecollect.request.*;
import com.okay.family.common.bean.casecollect.response.CollectionCaseInfoBean;
import com.okay.family.common.bean.casecollect.response.CollectionRunResultDetailBean;
import com.okay.family.common.bean.casecollect.response.ListCaseBean;
import com.okay.family.common.bean.casecollect.response.ListCollectionBean;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.common.SimpleBean;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.bean.testcase.request.DelCollectionBean;
import com.okay.family.common.bean.testcase.response.ListCaseRunResultBean;

import java.util.List;

public interface CaseCollectionMapper {

    int addCollection(AddCollectionBean bean);

    void addCollectionCaseRelation(AddCollectionBean beans);

    void addEditRcord(CaseCollectionEditRecord record);

    int delCollection(DelCollectionBean bean);

    void delCollectionCaseRelation(DelCollectionBean bean);

    int editCollection(CollectionEditBean bean);

    int shareCollection(CollectionEditBean bean);

    int delCaseFromCollection(DelCaseCollectionRelationBean bean);

    List<ListCaseBean> getCases(int collectionId, int uid);

    List<Integer> getCaseIds(int collectionId);

    List<CaseDataBean> getCasesDeatil(RunCollectionBean bean);

    CollectionCaseInfoBean getCollectionInfo(CollectionCaseInfoBean bean);

    void addCollectionRunRecord(CollectionRunResultRecord record);

    void updateCollectionStatus(int id, int status);

    List<ListCollectionBean> findCollecions(SearchCollectionBean bean);

    List<SimpleBean> searchCollectionNoPage(SearchCollectionNoPageBean bean);

    List<SimpleBean> getRecords(DelBean bean);

    List<ListCaseRunResultBean> getCaseRunRecord(int runId);

    CollectionRunResultDetailBean getCollectionRunDetail(int runId);

    void delAllCaseCollectionRelation(int id);

}
