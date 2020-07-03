package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray
import com.okay.family.fun.frame.SourceCode

enum CollectionEditType {

    CREATE(1, "重建用例集"),
    EDIT(2, "编辑测试用例"),
    DEL_CASE(3, "删除测试用例")

    int code

    String desc

    CollectionEditType(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONArray getAll() {
        JSONArray result = new JSONArray()
        CollectionEditType.values().each {x -> result << SourceCode.getJson("id=" + x.getCode(), "name=" + x.getDesc())}
        result
    }

}