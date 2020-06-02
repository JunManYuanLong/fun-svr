package com.okay.family.constants.bean

import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.base.bean.RequestInfo
import com.okay.family.fun.config.RequestType

class RequestSaveBean extends AbstractBean {
    /**
     * 接口地址
     */
    String apiName

    /**
     * 请求的url
     */
    String url

    /**
     * 请求的uri
     */
    String uri

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
     * 参数
     */
    String params
    /**
     * 响应大小
     */
    int data_size
    /**
     * 响应耗时
     */
    long elapsed_time
    /**
     * 响应状态码
     */
    int status
    /**
     * 响应业务code
     */
    int code

    int request_uid

    int request_id

    public RequestSaveBean(RequestInfo requestInfo, int data_size, long elapsed_time, int code, int status, int request_uid, int request_id) {
        this.apiName = requestInfo.getApiName()
        this.url = requestInfo.getUrl()
        this.uri = requestInfo.getUri()
        this.method = requestInfo.getMethod()
        this.host = requestInfo.getHost()
        this.type = requestInfo.getType()
        this.params = requestInfo.getParams()
        this.data_size = data_size
        this.elapsed_time = elapsed_time
        this.status = status
        this.code = code
        this.code = code
        this.request_uid = request_uid
        this.request_id = request_id
    }

}
