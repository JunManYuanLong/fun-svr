package com.okay.family.service;

import com.okay.family.common.bean.pubdata.DelDataBean;
import com.okay.family.common.bean.pubdata.PubDataBean;

import java.util.List;

public interface IPubDataService {

    List<PubDataBean> getEnvDatas(int uid, int environment);

    List<PubDataBean> getAllDatas(int uid);

    int addData(PubDataBean bean);

    int delData(DelDataBean bean);

    int updateData(PubDataBean bean);


}
