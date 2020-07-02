package com.okay.family.common

import com.okay.family.fun.utils.Time
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component

@Component
class Task {

    private static Logger logger = LoggerFactory.getLogger(Task.class)

    @Scheduled(cron = "0 0/10 * * * ?")
    def saveRequestBean() {

        logger.info("定时任务执行完毕! 时间:{},记录数目:{}", Time.getDate())
    }


}
