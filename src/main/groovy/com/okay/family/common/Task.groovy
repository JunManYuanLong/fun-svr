package com.okay.family.common

import com.okay.family.common.bean.RequestSaveBean
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

    @Scheduled(cron = "30 * * * * ?")
    def saveRequestBean() {
        while (true) {
            if (RequestSave.getWorkNum() == 0) break
            RequestSaveBean work = RequestSave.getWork()
            if (work == null) break
            commonService.saveRequest(work)
        }
        logger.info("定时任务执行完毕! 时间:{}", Time.getDate())
    }


}
