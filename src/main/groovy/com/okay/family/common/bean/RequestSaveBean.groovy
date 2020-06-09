package com.okay.family.common.bean

import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.base.bean.RequestInfo
import com.okay.family.fun.config.RequestType

class RequestSaveBean extends AbstractBean {

    private static final long serialVersionUID = -15959429531153477L;

    /**
     * 接口地址
     */
    String apiName

    /**
     * 请求的url
     */
    String url

    /**
     * 方法，get/post
     */
    RequestType method

    /**
     * 域名
     */
    String host

    /**
     * 协议类型
     */
    String type

    /**
     * 响应大小
     */
    int datasize
    /**
     * 响应耗时
     */
    long elapsedtime
    /**
     * 响应状态码
     */
    int status
    /**
     * 响应业务code
     */
    int code

    int requestid

    int uid

    public RequestSaveBean(RequestInfo requestInfo, int datasize, long elapsed_time, int code, int status, int requestid, int uid) {
        this.apiName = requestInfo.getApiName()
        this.url = requestInfo.getUrl()
        this.uri = requestInfo.getUri()
        this.method = requestInfo.getMethod()
        this.host = requestInfo.getHost()
        this.type = requestInfo.getType()
        this.datasize = datasize
        this.elapsedtime = elapsedtime
        this.status = status
        this.code = code
        this.code = code
        this.requestid = requestid
        this.uid = uid
    }

}
