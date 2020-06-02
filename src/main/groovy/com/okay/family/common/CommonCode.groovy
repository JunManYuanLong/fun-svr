package com.okay.family.common

import com.okay.family.fun.base.interfaces.ErrorCode

enum CommonCode implements ErrorCode {

    PARAMS_ERROR(100, "参数校验失败"),
    ADD_USER_ERROR(101,"添加用户失败!")

    int code

    String name

    CommonCode(int coe, String desc) {
        this.code = code
        this.desc = desc
    }


    @Override
    int getcode() {
        return code
    }

    @Override
    String getDesc() {
        return desc
    }
}