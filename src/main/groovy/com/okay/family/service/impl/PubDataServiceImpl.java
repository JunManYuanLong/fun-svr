package com.okay.family.service.impl;

import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.pubdata.request.EditPubBean;
import com.okay.family.common.bean.pubdata.response.PubDataBean;
import com.okay.family.common.bean.pubdata.request.SavePubDataBean;
import com.okay.family.mapper.PubDataMapper;
import com.okay.family.service.IPubDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PubDataServiceImpl implements IPubDataService {


    PubDataMapper pubDataMapper;

    @Autowired
    public PubDataServiceImpl(PubDataMapper pubDataMapper) {
        this.pubDataMapper = pubDataMapper;
    }

    @Override
    public List<PubDataBean> getAllDatas(int uid) {
        List<PubDataBean> allDatas = pubDataMapper.getAllDatas(uid);
        return allDatas;
    }

    @Override
    public List<PubDataBean> getDatasByEnv(int uid, int envId) {
        List<PubDataBean> datasByEnv = pubDataMapper.getDatasByEnv(uid, envId);
        return datasByEnv;
    }

    @Override
    public int addData(EditPubBean bean) {
        int add = pubDataMapper.add(bean);
        return bean.getId();
    }

    @Override
    public int delData(DelBean bean) {
        int i = pubDataMapper.delData(bean);
        return i;
    }

    @Override
    public int updateDataAttribute(EditPubBean bean) {
        int i = pubDataMapper.updateDataAttribute(bean);
        return i;
    }

    @Override
    public int saveData(SavePubDataBean bean) {
        int i = pubDataMapper.saveData(bean);
        return i;
    }


}
