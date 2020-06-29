package com.okay.family.common.enums

import com.alibaba.fastjson.JSONObject

import java.util.stream.Collectors

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

    static JSONObject getAll() {
        CaseEditType.values().stream().collect(Collectors.toMap({x -> x.getCode() as String}, {x -> x.getDesc()})) as JSONObject
    }

}