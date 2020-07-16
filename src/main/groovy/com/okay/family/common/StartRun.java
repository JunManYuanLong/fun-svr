package com.okay.family.common;

import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.service.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

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
        commonService.clearLock();
        int lock = commonService.lock(OkayConstant.NODE_LOCK);
        if (lock == 1) {
            OkayConstant.RUN_MARK = new AtomicInteger(SourceCode.getMark() % 100_000_000);
            OkayConstant.node = 1;
            logger.info("我是节点 {} ----------", OkayConstant.node);
        } else {
            OkayConstant.node = 2;
            logger.info("我是节点 {} ----------", OkayConstant.node);
            OkayConstant.RUN_MARK = new AtomicInteger(SourceCode.getMark() % 100_000_000 - 10_000_000);
        }
        OkayConstant.COLLECTION_MARK = new AtomicInteger(OkayConstant.RUN_MARK.get() / 100);
        logger.info("程序初始化运行方法执行完毕……");
    }


}
