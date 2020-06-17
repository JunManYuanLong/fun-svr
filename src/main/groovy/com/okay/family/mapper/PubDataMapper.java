package com.okay.family.mapper;

import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.pubdata.request.EditPubBean;
import com.okay.family.common.bean.pubdata.response.PubDataBean;
import com.okay.family.common.bean.pubdata.request.SavePubDataBean;

import java.util.List;

public interface PubDataMapper {

    List<PubDataBean> getDatasByEnv(int uid, int envId);

    List<PubDataBean> getAllDatas(int uid);

    int add(EditPubBean bean);

    int updateDataAttribute(EditPubBean bean);

    int delData(DelBean bean);

    int saveData(SavePubDataBean bean);

}
