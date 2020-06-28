package com.okay.family.common.enums

enum CollectionStatus {


    DEFAULT(1, "未测试"),
    SUCCESS(2, "成功"),
    FAIL(3, "失败")

    int code

    String desc

    CollectionStatus(int code, String desc) {
        this.code = code
        this.desc = desc
    }

}