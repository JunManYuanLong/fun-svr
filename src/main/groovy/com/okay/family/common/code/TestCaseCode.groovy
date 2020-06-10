package com.okay.family.common.code

import com.okay.family.fun.base.interfaces.ReturnCode

enum TestCaseCode implements ReturnCode {

    ADD_CASE_FAIL(3000, "添加测试用例失败")

    int code

    String desc

    TestCaseCode(int code, String desc) {
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
