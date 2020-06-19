package com.okay.family.common.code

import com.okay.family.fun.base.interfaces.ReturnCode

enum TestCaseCode implements ReturnCode {

    ADD_CASE_FAIL(3000, "添加测试用例失败"),
    DEL_CASE_FAIL(3001, "删除测试用例失败"),
    NO_CHANGE_FAIL(3002, "没有匹配记录/与原有内容一致,无法更改"),
    COPY_CASE_FAIL(3003, "复制用例错误")

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
