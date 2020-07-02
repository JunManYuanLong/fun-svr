package com.okay.family.common;

import com.okay.family.common.bean.RequestSaveBean;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.service.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class RequestSave extends SourceCode {

    public static Logger logger = LoggerFactory.getLogger(RequestSave.class);

    private static LinkedBlockingQueue<RequestSaveBean> beans = new LinkedBlockingQueue<>();

    private static ICommonService service;

    ICommonService commonService;

    @Autowired
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
