package com.okay.family.common;


import com.okay.family.fun.base.bean.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.invoke.MethodHandles;


@Component
@WebFilter(urlPatterns = "/*", filterName = "wrappingFilter")
@Order(value = 1)
public class WrappingFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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

        String requestId = req.getHeader("requestid");
        String apiGzip = req.getHeader("Api-Gzip");
        String ae = req.getHeader("Accept-Encoding");
        String uid = req.getHeader("uid");
        String from = req.getHeader("Request-From");
        String requestURL = req.getRequestURL().toString();
        String queryArgs = req.getQueryString();

//        TraceKeyHolder.setTraceKey(requestId);
        MDC.put("id", requestId);

        LOGGER.info("header paramters Accept-Encoding:{} Api-Gzip:{} UID:{} RequestURL:{},FROM:{}", ae, apiGzip, uid, requestURL + "?" + queryArgs, from);

//        RequestPathHolder.setPath(req.getRequestURI());

        //增加requestid验证
        if (StringUtils.isBlank(requestId)) {
            resp.setHeader("Content-type", "application/json;charset=UTF-8");
            resp.getWriter().write(Result.fail("校验失败").toString());
            resp.flushBuffer();
            return;
        }
        chain.doFilter(request, responseWrapper);

        byte[] bytes = responseWrapper.getContent();
        String respContent = new String(bytes);
        if (respContent != null) {
            /*http转https*/
            respContent = respContent.replace("http://", "https://");
        }
        //将数据重新写入返回数据流中
        response.getOutputStream().write(respContent.getBytes());
    }


}
