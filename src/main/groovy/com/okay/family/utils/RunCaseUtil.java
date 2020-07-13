package com.okay.family.utils;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.bean.testcase.CaseRunRecord;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.enums.RequestType;
import com.okay.family.common.enums.RunResult;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.fun.frame.httpclient.FunRequest;
import com.okay.family.service.ICommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class RunCaseUtil extends SourceCode {

    static Logger logger = LoggerFactory.getLogger(RunCaseUtil.class);

    static ICommonService service;

    ICommonService commonService;

    public RunCaseUtil(ICommonService service) {
        this.commonService = service;
    }

    @PostConstruct
    public void init() {
        service = this.commonService;
    }


    public static void run(CaseDataBean bean, CaseRunRecord record) {
        if (!bean.canRun()) {
            record.setResponseResult(new JSONObject());
            record.setCode(TEST_ERROR_CODE);
            record.setResult(RunResult.UNRUN.getCode());
            record.setCheckResult(bean.getTestWish());
            return;
        }
        int envId = bean.getEnvId();
        int serviceId = bean.getServiceId();
        String host = service.getHost(envId, serviceId);
        FunRequest request = null;
        String httpType = bean.getHttpType();
        if (httpType.equalsIgnoreCase(RequestType.GET.getDesc())) {
            request = FunRequest.isGet().setHost(host).setApiName(bean.getUrl()).addHeaders(bean.getHeaders()).addArgs(bean.getParams());
        } else if (httpType.equalsIgnoreCase(RequestType.POST_JSON.getDesc())) {
            request = FunRequest.isPost().setHost(host).setApiName(bean.getUrl()).addHeaders(bean.getHeaders()).addJson(bean.getParams());
        } else if (httpType.equalsIgnoreCase(RequestType.POST_FORM.getDesc())) {
            request = FunRequest.isPost().setHost(host).setApiName(bean.getUrl()).addHeaders(bean.getHeaders()).addParams(bean.getParams());
        } else {
            logger.warn("请求方式不支持:{}", bean.toString());
            record.setResult(RunResult.UNRUN.getCode());
            return;
        }
        JSONObject response = request.getResponse();
        record.setResponseResult(response);
        if (response.isEmpty()) {
            logger.warn("用例响应为空:{}", bean.toString());
            record.setResult(RunResult.UNRUN.getCode());
            return;
        }
        record.setCode(VerifyResponseUtil.getCode(response));
        boolean verify = VerifyResponseUtil.verify(response, bean.getTestWish());
        record.setResult(verify ? RunResult.SUCCESS.getCode() : RunResult.FAIL.getCode());
        record.setCheckResult(bean.getTestWish());
    }


}
