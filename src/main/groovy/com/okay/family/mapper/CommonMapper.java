package com.okay.family.mapper;

import com.okay.family.common.bean.RequestSaveBean;

import java.util.HashMap;
import java.util.List;

public interface CommonMapper {

    List<HashMap<String, String>> findAllHost();

    //TODO:完成SQL语句
    int saveRequest(RequestSaveBean bean);


}
