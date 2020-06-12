package com.okay.family.common;

import com.okay.family.utils.FamilyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StartRun implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(StartRun.class);

    @Override
    public void run(String... args) {
        FamilyUtil.initHosts();

        logger.info("程序初始化运行方法执行完毕……");
    }


}
