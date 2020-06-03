package com.okay.family.service.impl;

import com.okay.family.common.bean.RequestSaveBean;
import com.okay.family.mapper.SaveMapper;
import com.okay.family.service.ISaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveServiceimpl implements ISaveService {

    @Autowired
    SaveMapper saveMapper;

    @Override
    public void saveRequest(RequestSaveBean info) {
        saveMapper.saveRequest(info);
    }


}
