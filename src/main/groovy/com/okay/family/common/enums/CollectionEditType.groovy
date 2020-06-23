package com.okay.family.common.enums

enum CollectionEditType {

    CREATE(1,"重建用例集"),
    EDIT(2,"编辑测试用例"),
    DEL_CASE(3,"删除测试用例")

    int code

    String desc

    CollectionEditType(int code, String desc) {
        this.code = code
        this.desc = desc
    }
}