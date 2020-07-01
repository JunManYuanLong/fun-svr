package com.okay.family.utils;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.ServerHost;
import com.okay.family.common.bean.testcase.request.CaseRunRecord;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.enums.RequestType;
import com.okay.family.common.enums.RunResult;
import com.okay.family.fun.frame.httpclient.FunRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunCaseUtil {

    static Logger logger = LoggerFactory.getLogger(RunCaseUtil.class);

    public static void run(CaseDataBean bean, CaseRunRecord record) {
        int envId = bean.getEnvId();
        int serviceId = bean.getServiceId();
        String host = ServerHost.getHost(serviceId, envId);
        FunRequest request = null;
        String httpType = bean.getHttpType();
        if (httpType.equalsIgnoreCase(RequestType.GET.getDesc())) {
            request = FunRequest.isGet().setHost(host).setApiName(bean.getUrl()).addHeaders(bean.getHeaders()).addArgs(bean.getParams());
        } else if (httpType.equalsIgnoreCase(RequestType.POST_JSON.getDesc())) {
            request = FunRequest.isPost().setHost(host).setApiName(bean.getUrl()).addHeaders(bean.getHeaders()).addJson(bean.getParams());
        } else if (httpType.equalsIgnoreCase(RequestType.POST_FORM.getDesc())) {
            request = FunRequest.isPost().setHost(host).setApiName(bean.getUrl()).addHeaders(bean.getHeaders()).addParams(bean.getParams());
        } else {
            logger.warn("用户身份不支持:{}",bean.toString());
            record.setResult(RunResult.UNRUN.getCode());
            return;
        }
        JSONObject response = request.getResponse();
        record.setResponse(response);
        if (response.isEmpty()) {
            logger.warn("用例响应为空:{}",bean.toString());
            record.setResult(RunResult.UNRUN.getCode());
            return;
        }
        record.setCode(VerifyResponseUtil.getCode(response));
        boolean verify = VerifyResponseUtil.verify(response, bean.getTestWish());
        record.setResult(verify ? RunResult.SUCCESS.getCode() : RunResult.FAIL.getCode());
        record.setVerify(bean.getTestWish());
    }


}
