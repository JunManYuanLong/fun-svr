package com.okay.family.common.code

import com.okay.family.fun.base.interfaces.ReturnCode

enum CollectionCode implements ReturnCode {

    ADD_COLLECTION_FAIL(4001, "添加测试用例集失败"),
    NO_CHANGE_FAIL(4002, "没有匹配记录/与原有内容一致,无法更改"),
    DEL_COLLECTION_FAIL(4003, "没有匹配记录,无法删除"),
    DEL_CASE_FAIL(4004, "没有匹配记录,无法删除")


    int code

    String desc

    @Override
    int getCode() {
        return code
    }

    @Override
    String getDesc() {
        return desc
    }

    CollectionCode(int code, String desc) {
        this.code = code
        this.desc = desc
    }
}
