package com.okay.family.common;

import com.okay.family.common.basedata.ServerHost;
import com.okay.family.service.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class StartRun implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(StartRun.class);

    ICommonService commonService;

    @Autowired
    public StartRun(ICommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    public void run(String... args) {
        Map<Integer, String> allHost = commonService.findAllHost();
        ServerHost.init(allHost);
        logger.info("程序初始化运行方法执行完毕……");
    }


}
