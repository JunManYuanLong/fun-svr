package com.okay.family.service;

import com.okay.family.common.bean.RequestSaveBean;

import java.util.Map;

public interface ICommonService {

    Map<Integer, String> findAllHost();

    void saveRequest(RequestSaveBean bean);


}
