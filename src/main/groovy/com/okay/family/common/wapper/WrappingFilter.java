package com.okay.family.common.wapper;


import com.okay.family.common.basedata.FamilyConstant;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.fun.config.Constant;
import com.okay.family.fun.frame.Output;
import com.okay.family.fun.utils.DecodeEncode;
import com.okay.family.fun.utils.Time;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;


@Component
@WebFilter(urlPatterns = "/*", filterName = "wrappingFilter")
public class WrappingFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        ResponseWrapper responseWrapper = new ResponseWrapper(resp);
        RequestWrapper requestWrapper = new RequestWrapper(req);
        String url = requestWrapper.getRequestURI();
        String queryArgs = requestWrapper.getQueryString();
        queryArgs = queryArgs == null ? DecodeEncode.unicodeToString(requestWrapper.getBody()) : queryArgs;

        String requestId = req.getHeader(OkayConstant.REQUEST_ID);
//        if(StringUtils.isBlank(requestId)){
//            resp.getWriter().write(Result.fail(CommonCode.REQUESTID_ERROR).toString());
//            resp.flushBuffer();
//            return;
//        }
        MDC.put("id", requestId);
        long start = Time.getTimeStamp();
        chain.doFilter(requestWrapper == null ? request : requestWrapper, responseWrapper);
        long end = Time.getTimeStamp();
        byte[] bytes = responseWrapper.getContent();
        String respContent = new String(bytes, Constant.UTF_8);
        if (!url.startsWith("/ws")) {
            logger.info("请求:{},耗时:{} ms,参数:{},响应:{}", url, end - start, queryArgs, respContent);
            try {
                if (FamilyConstant.OUTPUT) Output.showStr(queryArgs);
                if (FamilyConstant.OUTPUT) Output.showStr(respContent);
            } catch (Exception e) {

            }
            response.getOutputStream().write(respContent.getBytes(Constant.UTF_8));
        }
    }


}
