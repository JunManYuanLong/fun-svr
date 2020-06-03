package com.okay.family.common.code

import com.okay.family.fun.base.interfaces.ReturnCode

enum TestUserCode implements ReturnCode {

    NO_USER(1000, "用户不存在!"),
    CHECK_FAIL(1001, "用户校验失败!"),
    UPDATE_USER_FAIL(1002, "更新用户凭证失败!")

    int code

    String name

    TestUserCode(int coe, String desc) {
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