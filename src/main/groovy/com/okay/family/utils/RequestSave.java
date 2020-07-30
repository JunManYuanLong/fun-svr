package com.okay.family.utils;

import com.okay.family.common.bean.RequestSaveBean;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.service.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RequestSave extends SourceCode {

    public static Logger logger = LoggerFactory.getLogger(RequestSave.class);

    private static ICommonService service;

    ICommonService commonService;

    public RequestSave(ICommonService commonService) {
        this.commonService = commonService;
    }


    @PostConstruct
    public void init() {
        service = this.commonService;
    }

    public static void addWork(RequestSaveBean requestSaveBean) {
        service.saveRequest(requestSaveBean);
    }


}
