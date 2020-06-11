package com.okay.family.common.enums;

/**
 * 接口请求方式,get postform, postjson,枚举类
 */
enum RequestMethod {

    GET(1),
    POST_JSON(2),
    POST_FORM(3)

    int code;

    private RequestMethod(int code) {
        this.code = code;
    }


}
