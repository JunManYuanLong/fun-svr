package com.okay.family.mapper;

import com.okay.family.common.bean.RequestSaveBean;
import org.springframework.scheduling.annotation.Async;

public interface SaveMapper {

    @Async
    int saveRequest(RequestSaveBean bean);


}
