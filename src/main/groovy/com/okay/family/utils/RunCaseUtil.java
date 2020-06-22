package com.okay.family.utils;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.basedata.ServerHost;
import com.okay.family.common.bean.testcase.request.CaseDataBean;
import com.okay.family.common.bean.testcase.request.CaseRunRecord;
import com.okay.family.common.enums.RequestType;
import com.okay.family.common.enums.RunResult;
import com.okay.family.fun.frame.httpclient.FunRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunCaseUtil {

    static Logger logger = LoggerFactory.getLogger(RunCaseUtil.class);

    public static CaseRunRecord run(CaseDataBean bean) {
        int envId = bean.getEnvId();
        int serviceId = bean.getServiceId();
        String host = ServerHost.getHost(serviceId, envId);

        CaseRunRecord historyBean = new CaseRunRecord();
        int andIncrement = OkayConstant.RUN_MARK.getAndIncrement();
        historyBean.setMark(andIncrement);
        historyBean.setUid(bean.getUid());
        historyBean.setParams(bean.getParams());
        JSONObject headers = bean.getHeaders();
        headers.put(OkayConstant.MARK_HEADER, andIncrement);
        historyBean.setHeaders(headers);
        FunRequest request = null;
        String httpType = bean.getHttpType();
        if (httpType.equalsIgnoreCase(RequestType.GET.getDesc())) {
            request = FunRequest.isGet().setHost(host).setApiName(bean.getUrl()).addHeaders(bean.getHeaders()).addArgs(bean.getParams());
        } else if (httpType.equalsIgnoreCase(RequestType.POST_JSON.getDesc())) {
            request = FunRequest.isPost().setHost(host).setApiName(bean.getUrl()).addHeaders(bean.getHeaders()).addJson(bean.getParams());
        } else if (httpType.equalsIgnoreCase(RequestType.POST_FORM.getDesc())) {
            request = FunRequest.isPost().setHost(host).setApiName(bean.getUrl()).addHeaders(bean.getHeaders()).addParams(bean.getParams());
        } else {
            historyBean.setResult(RunResult.UNRUN.getCode());
            return historyBean;
        }
        JSONObject response = request.getResponse();
        historyBean.setResponse(response);

        boolean verify = VerifyResponseUtil.verify(response, bean.getTestWish());
        historyBean.setResult(verify? RunResult.SUCCESS.getCode():RunResult.FAIL.getCode());
        historyBean.setVerify(bean.getTestWish());

        return historyBean;

    }


}
