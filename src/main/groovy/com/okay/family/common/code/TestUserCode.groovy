package com.okay.family.common.code

import com.okay.family.fun.base.interfaces.ReturnCode

enum TestUserCode implements ReturnCode {

    NO_USER(1000, "用户不存在"),
    CHECK_FAIL(1001, "用户校验失败"),
    UPDATE_USER_FAIL(1002, "没有匹配记录/与原有内容一致,无法更改"),
    DEL_USER_FAIL(1003, "删除用户失败"),
    ADD_USER_FAIL(1004, "添加用户失败")

    int code

    String desc

    TestUserCode(int code, String desc) {
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