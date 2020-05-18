package com.okay.family.Bean

import com.okay.family.fun.base.bean.AbstractBean
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


}
