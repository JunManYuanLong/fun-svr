package com.okay.family.service;

import com.okay.family.common.bean.pubdata.EditPubBean;
import com.okay.family.common.bean.pubdata.PubDataBean;

import java.util.List;

public interface IPubDataService {

    List<PubDataBean> getEnvDatas(int uid, int environment);

    List<PubDataBean> getAllDatas(int uid);

    List<PubDataBean> getDatasByEnv(int envId, int uid);

    int addData(EditPubBean bean);

    int delData(EditPubBean bean);

    int updateDataAttribute(EditPubBean bean);

    int updateData(PubDataBean bean);


}
