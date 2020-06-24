package com.okay.family.common.enums

enum CollectionRunStatus {


    DEFAULT(1, "未测试"),
    SUCCESS(2, "成功"),
    FAIL(3, "失败")

    int code

    String desc

    CollectionRunStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }

}