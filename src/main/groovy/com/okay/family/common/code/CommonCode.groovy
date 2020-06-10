package com.okay.family.common.code;

import com.okay.family.fun.base.interfaces.ReturnCode;

enum CommonCode implements ReturnCode {


    SUCCESS(0, "成功"),
    PARAMS_ERROR(100, "参数校验失败")

    int code

    String desc

    CommonCode(int code, String desc) {
        this.code = code
        this.desc = desc
    }


    @Override
    int getCode() {
        return code
    }

    @Override
    String getDesc() {
        return desc
    }
}
