package com.okay.family.common.wapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
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

//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;

//        ResponseWrapper responseWrapper = new ResponseWrapper(resp);

//        String requestId = req.getHeader("requestid");
//        String apiGzip = req.getHeader("Api-Gzip");
//        String ae = req.getHeader("Accept-Encoding");
//        String uid = req.getHeader("uid");
//        String from = req.getHeader("Request-From");
//        String requestURL = req.getRequestURL().toString();
//        String queryArgs = req.getQueryString();

//        TraceKeyHolder.setTraceKey(requestId);
//        MDC.put("id", requestId);

//        logger.info("header paramters RequestURL:{}", requestURL + "?" + queryArgs);

//        RequestPathHolder.setPath(req.getRequestURI());

        //增加requestid验证
//        if (StringUtils.isBlank(requestId)) {
//            MDC.put("id", SourceCode.getMark() + Constant.EMPTY);

//            resp.setHeader("Content-type", "application/json;charset=UTF-8");
//            resp.getWriter().write(Result.fail("校验失败").toString());
//            resp.flushBuffer();
//            return;
//        }
        RequestWrapper requestWrapper = new RequestWrapper((HttpServletRequest) request);
//        RequestWrapper requestWrapper = new RequestWrapper(request);
        String url = requestWrapper.getRequestURL().toString();
        String queryArgs = requestWrapper.getQueryString();
        queryArgs = queryArgs == null ? requestWrapper.getBody() : queryArgs;
        logger.info("请求url:{},参数:{}", url, queryArgs);
//        chain.doFilter(request, response);
        chain.doFilter(requestWrapper == null ? request : requestWrapper, response);
//        byte[] bytes = responseWrapper.getContent();
//        String respContent = new String(bytes);
//        if (respContent != null) {
//            /*http转https*/
//            respContent = respContent.replace("http://", "https://");
//        }
//        //将数据重新写入返回数据流中
//        response.getOutputStream().write(respContent.getBytes());
    }


}
