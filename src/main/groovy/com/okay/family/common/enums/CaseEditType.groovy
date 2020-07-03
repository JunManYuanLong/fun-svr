package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray
import com.okay.family.fun.frame.SourceCode

enum CaseEditType {

    CREATE(1, "创建用例"),
    EDIT_ATTRIBUTE(2, "修改用例属性"),
    EDIT_DATA(3, "修改用例数据")

    int code

    String desc

    CaseEditType(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONArray getAll() {
        JSONArray result = new JSONArray()
        CaseEditType.values().each {x -> result << SourceCode.getJson("id=" + x.getCode(), "name=" + x.getDesc())}
        result
    }

}