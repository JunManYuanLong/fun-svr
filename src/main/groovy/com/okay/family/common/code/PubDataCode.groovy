package com.okay.family.common.code

import com.okay.family.fun.base.interfaces.ReturnCode

enum PubDataCode implements ReturnCode {

    ADD_FAIL(2000, "公共数据添加失败"),
    NO_MATCH_FAIL(2001, "没有匹配记录,删除失败"),
    NO_CHANGE_FAIL(2002, "没有匹配记录/与原有内容一致,无法更改")

    int code

    String desc

    PubDataCode(int code, String desc) {
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
