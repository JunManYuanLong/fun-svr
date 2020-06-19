package com.okay.family.utils;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.basedata.ServerHost;
import com.okay.family.common.bean.testcase.CaseRunRecord;
import com.okay.family.common.bean.testcase.response.TestCaseAttributeBean;
import com.okay.family.fun.config.Constant;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.fun.frame.httpclient.FanLibrary;
import com.okay.family.fun.frame.httpclient.FunRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunCaseUtil {

    static Logger logger = LoggerFactory.getLogger(RunCaseUtil.class);

    public static CaseRunRecord run(TestCaseAttributeBean bean) {
        int environment = bean.getEnvironment();
        int serverid = bean.getServerid();
        /*
        存疑,server是id还是name
         */
        bean.setHost(ServerHost.getHost(serverid));

        CaseRunRecord historyBean = new CaseRunRecord();
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
                bean.setLastresult(3);
                historyBean.setStatus(3);
                return historyBean;
        }
        int mark = SourceCode.getMark() + historyBean.hashCode() % 10000;
        historyBean.setMark(mark);
        request.addHeader(FanLibrary.getHeader(OkayConstant.MARK_HEADER, mark + Constant.EMPTY));
        JSONObject response = request.getResponse();
        historyBean.setResponse(response.toString());

        VerifyResponseUtil.verify(response, bean.getVerify());

        historyBean.setStatus(1);

        return historyBean;

    }


}
