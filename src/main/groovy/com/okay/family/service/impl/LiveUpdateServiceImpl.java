package com.okay.family.service.impl;

import com.okay.family.common.basedata.ServerHost;
import com.okay.family.fun.frame.Output;
import com.okay.family.service.ICommonService;
import com.okay.family.service.ILiveUpdateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LiveUpdateServiceImpl implements ILiveUpdateService {

    private static Logger logger = LoggerFactory.getLogger(LiveUpdateServiceImpl.class);

    ICommonService commonService;

    @Autowired
    public LiveUpdateServiceImpl(ICommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    public void initServiceHost() {
        Map<Integer, String> allHost = commonService.findAllHost();
        ServerHost.init(allHost);
        logger.info("在线更新hosts地址成功!");
        Output.show(allHost);
    }


}
