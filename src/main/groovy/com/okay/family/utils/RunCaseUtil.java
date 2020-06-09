package com.okay.family.utils;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.ServiceHost;
import com.okay.family.common.bean.testcase.RunCaseRecordBean;
import com.okay.family.common.bean.testcase.TestCaseBean;
import com.okay.family.fun.frame.httpclient.FunRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunCaseUtil {

    static Logger logger = LoggerFactory.getLogger(RunCaseUtil.class);

    public static RunCaseRecordBean run(TestCaseBean bean) {
        int environment = bean.getEnvironment();
        int server = bean.getServer();
        /*
        存疑,server是id还是name
         */
        bean.setHost(ServiceHost.getHost(environment, server));

        RunCaseRecordBean historyBean = new RunCaseRecordBean();
        historyBean.setCaseid(bean.getId());
        historyBean.setHeaders(bean.getHeaders().toString());
        historyBean.setParams(bean.getParams().toJSONString());


        FunRequest request = null;
        switch (bean.getMethod()) {
            case 1:
                request = FunRequest.isGet().addHeaders(bean.getHeaders()).addArgs(bean.getParams());
                break;
            case 2:
                request = FunRequest.isPost().addHeaders(bean.getHeaders()).addJson(bean.getParams());
                break;
            case 3:
                request = FunRequest.isPost().addHeaders(bean.getHeaders()).addParams(bean.getParams());
                break;
            default:
                bean.setLast(3);
                historyBean.setStatus(3);
                return historyBean;
        }

        JSONObject response = request.getResponse();
        historyBean.setResponse(response.toString());

        VerifyResponseUtil.verify(response, bean.getVerify());

        historyBean.setStatus(1);

        return historyBean;

    }


}
