package com.okay.family.service;

import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.pubdata.EditPubBean;
import com.okay.family.common.bean.pubdata.PubDataBean;
import com.okay.family.common.bean.pubdata.SavePubDataBean;

import java.util.List;

public interface IPubDataService {

    List<PubDataBean> getAllDatas(int uid);

    List<PubDataBean> getDatasByEnv(int uid, int envId);

    int addData(EditPubBean bean);

    int delData(DelBean bean);

    int updateDataAttribute(EditPubBean bean);

    int saveData(SavePubDataBean bean);


}
