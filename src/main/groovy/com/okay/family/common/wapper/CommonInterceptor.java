package com.okay.family.common.wapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component("commonInterceptor")
public class CommonInterceptor extends HandlerInterceptorAdapter {

    private static Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o) throws Exception {
        try {
            RequestWrapper requestWrapper = new RequestWrapper(request);
            String url = requestWrapper.getRequestURL().toString();
            String queryArgs = requestWrapper.getQueryString();
            queryArgs = queryArgs == null ? requestWrapper.getBody() : queryArgs;
            logger.info("请求url:{},参数:{}", url, queryArgs);

            ResponseWrapper responseWrapper = new ResponseWrapper(httpServletResponse);
            byte[] content = responseWrapper.getContent();
            String s = new String(content);
            logger.error(s);

            return true;
        } catch (Exception e) {
            logger.error("", e);
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }


}
