package com.okay.family.service.impl;

import com.okay.family.common.bean.common.DelBean;
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

    /**
     * 获取所有公共数据
     *
     * @param uid
     * @return
     */
    @Override
    public List<PubDataBean> getAllDatas(int uid) {
        List<PubDataBean> allDatas = pubDataMapper.getAllDatas(uid);
        return allDatas;
    }

    /**
     * 获取某一环境下的公共数据,用于编写测试用例时候用
     *
     * @param uid
     * @param envId
     * @return
     */
    @Override
    public List<PubDataBean> getDatasByEnv(int uid, int envId) {
        List<PubDataBean> datasByEnv = pubDataMapper.getDatasByEnv(uid, envId);
        return datasByEnv;
    }

    /**
     * 添加公共数据
     *
     * @param bean
     * @return
     */
    @Override
    public int addData(EditPubBean bean) {
        int add = pubDataMapper.add(bean);
        return bean.getId();
    }

    /**
     * 删除公共数据
     *
     * @param bean
     * @return
     */
    @Override
    public int delData(DelBean bean) {
        int i = pubDataMapper.delData(bean);
        return i;
    }

    /**
     * 更新公共数据属性
     *
     * @param bean
     * @return
     */
    @Override
    public int updateDataAttribute(EditPubBean bean) {
        int i = pubDataMapper.updateDataAttribute(bean);
        return i;
    }

    /**
     * 保存公共数据内容
     *
     * @param bean
     * @return
     */
    @Override
    public int saveData(SavePubDataBean bean) {
        int i = pubDataMapper.saveData(bean);
        return i;
    }


}
