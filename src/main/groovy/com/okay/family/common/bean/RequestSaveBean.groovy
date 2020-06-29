package com.okay.family.common.bean

import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.base.bean.RequestInfo

class RequestSaveBean extends AbstractBean {

    private static final long serialVersionUID = -15959429531153477L;

    /**
     * 接口地址
     */
    String api

    int mark

    /**
     * 方法，get/post
     */
    String method

    /**
     * 域名
     */
    String host

    /**
     * 响应大小
     */
    int size

    /**
     * 响应耗时
     */
    long cost

    /**
     * 响应状态码
     */
    int status

    /**
     * 响应业务code
     */
    int code

    String requestid

    public RequestSaveBean(RequestInfo requestInfo, int size, long cost, int code, int status, String requestid,int mark) {
        this.api = requestInfo.getApiName()
        this.method = requestInfo.getMethod().getName()
        this.host = requestInfo.getHost()
        this.size = size
        this.cost = cost
        this.status = status
        this.code = code
        this.requestid = requestid
        this.mark = mark
    }

}
