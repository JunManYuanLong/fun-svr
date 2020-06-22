package com.okay.family.common.enums

enum RunResult {

    SUCCESS(1, "成功"),
    FAIL(2, "失败"),
    UNRUN(3, "无法运行"),
    USER_EROOR(4, "用户错误")

    int code

    String desc

    RunResult(int code, String desc) {
        this.code = code
        this.desc = desc
    }

}
