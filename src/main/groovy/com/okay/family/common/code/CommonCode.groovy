package com.okay.family.common.code;

import com.okay.family.fun.base.interfaces.ReturnCode
import com.okay.family.fun.config.Constant;

enum CommonCode implements ReturnCode {


    FAIL(Constant.TEST_ERROR_CODE, "失败"),
    SUCCESS(0, "成功"),
    PARAMS_ERROR(100, "常用参数错误!"),
    BIND_ERROR(101, "参数验证错误!"),
    UNKNOW_ERROR(102, "未知错误")

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
