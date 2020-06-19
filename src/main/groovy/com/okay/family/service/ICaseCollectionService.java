package com.okay.family.service;

import com.okay.family.common.bean.casecollect.CaseCollectionBean;
import com.okay.family.common.bean.casecollect.CaseCollectionRecord;
import com.okay.family.common.bean.casecollect.CaseCollectionRelationBean;
import com.okay.family.common.bean.testcase.response.TestCaseAttributeBean;

import java.util.List;

public interface ICaseCollectionService {

    int addCollection(CaseCollectionBean beans);

    int addCollectionRelation(List<CaseCollectionRelationBean> bean);

    /**
     * 这里需要先删除用例集和用例的依赖关系,然后重建,需要记录record
     *
     * @param bean
     * @return
     */
    //todo:待完成
    int update(CaseCollectionBean bean);

    int addEditReord(CaseCollectionRecord record);

    //todo:待完成
    List<CaseCollectionBean> getCollections(int uid);

    //todo:待完成
    CaseCollectionBean getCollectionInfo(int collectionid);

    List<TestCaseAttributeBean> getCases(int id);


}
