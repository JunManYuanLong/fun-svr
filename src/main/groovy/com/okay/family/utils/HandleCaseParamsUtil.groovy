package com.okay.family.utils

import com.okay.family.common.bean.testcase.request.CaseDataBean
import com.okay.family.common.enums.RunResult
import com.okay.family.common.exception.UserStatusException
import com.okay.family.fun.frame.SourceCode
import com.okay.family.service.ITestCaseService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct
import java.util.concurrent.ConcurrentHashMap

@Component
class HandleCaseParamsUtil extends SourceCode {

    private static Logger logger = LoggerFactory.getLogger(HandleCaseParamsUtil.class)

    static ITestCaseService service;

    ITestCaseService caseService

    HandleCaseParamsUtil(ITestCaseService caseService) {
        this.caseService = caseService
    }

    @PostConstruct
    public void init() {
        this.service = caseService
    }


    static def handleParams(CaseDataBean bean,ConcurrentHashMap certificates) {
        try {
            service.handleParams(bean, certificates);
        } catch (UserStatusException e) {
            logger.error("用户异常!", e);
            bean.setAvailable(RunResult.USER_ERROR.getCode());
        } catch (Exception e) {
            logger.error("处理用例参数发生错误!", e);
            bean.setAvailable(RunResult.UNRUN.getCode());
        }
    }

}
