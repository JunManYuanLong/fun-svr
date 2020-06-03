package com.okay.family.mapper;

import com.okay.family.common.bean.DemoBean;
import com.okay.family.common.bean.RequestSaveBean;

public interface SaveMapper {

    DemoBean saveRequest(RequestSaveBean bean);


}
