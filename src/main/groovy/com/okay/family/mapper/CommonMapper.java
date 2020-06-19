package com.okay.family.mapper;

import com.okay.family.common.bean.RequestSaveBean;

import java.util.HashMap;
import java.util.List;

public interface CommonMapper {

    List<HashMap<String, String>> findAllHost();

    int saveRequest(RequestSaveBean bean);


}
