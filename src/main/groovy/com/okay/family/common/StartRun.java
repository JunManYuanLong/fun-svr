package com.okay.family.common;

import com.okay.family.service.IDemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartRun implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(StartRun.class);

    @Autowired
    IDemoService demoService;

    @Override
    public void run(String... args) {
//        demoService.findALL(3);
        logger.info("程序初始化运行方法执行完毕……");
    }


}
