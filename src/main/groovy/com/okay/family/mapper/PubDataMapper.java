package com.okay.family.mapper;

import com.okay.family.common.bean.pubdata.EditPubBean;
import com.okay.family.common.bean.pubdata.PubDataBean;
import com.okay.family.common.bean.pubdata.SavePubDataBean;

import java.util.List;

public interface PubDataMapper {

    List<PubDataBean> getDatasByEnv(int uid, int envId);

    List<PubDataBean> getAllDatas(int uid);

    int add(EditPubBean bean);

    int updateDataAttribute(EditPubBean bean);

    int delData(EditPubBean bean);

    int saveData(SavePubDataBean bean);

}
