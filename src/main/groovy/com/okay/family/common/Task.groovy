package com.okay.family.common

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.utils.Time
import com.okay.family.service.ICommonService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Task {

    private static Logger logger = LoggerFactory.getLogger(Task.class)

    ICommonService commonService

    @Autowired
    Task(ICommonService commonService) {
        this.commonService = commonService
    }

    @Scheduled(cron = "0 0/10 * * * ?")
    def saveRequestBean() {
        if (OkayConstant.node == 1) commonService.clearLock()
        logger.info("定时任务执行完毕! 时间:{}", Time.getDate())
    }


}
