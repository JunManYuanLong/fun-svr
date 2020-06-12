package com.okay.family.mapper;

import com.okay.family.common.bean.pubdata.EditPubBean;
import com.okay.family.common.bean.pubdata.PubDataBean;

import java.util.List;

public interface PubDataMapper {

    List<PubDataBean> getEnvDatas(int uid, int environment);

    List<PubDataBean> getAllDatas(int uid);

    int add(EditPubBean bean);

    int updateData(PubDataBean bean);

    int delData(EditPubBean bean);


}
