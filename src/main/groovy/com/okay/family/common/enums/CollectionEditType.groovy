package com.okay.family.common.enums

import com.alibaba.fastjson.JSONArray
import com.okay.family.common.bean.common.SimpleBean

enum CollectionEditType {

    CREATE(1, "创建用例集"),
    ADD_CASE(2, "添加测试用例"),
    DEL_CASE(3, "删除测试用例"),
    EDIT_NAME(4, "编辑用例集名称"),
    SHARE(5, "共享测试用例集")

    int code

    String desc

    CollectionEditType(int code, String desc) {
        this.code = code
        this.desc = desc
    }

    static JSONArray getAll() {
        JSONArray result = new JSONArray()
        CollectionEditType.values().each {x ->
            result << SimpleBean.getBean(x.getCode(), x.getDesc())
        }
        result
    }

}