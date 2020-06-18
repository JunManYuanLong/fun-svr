package com.okay.family.common.enums

enum CaseEditType {

    CREATE("创建用例"),
    EDIT_ATTRIBUTE("修改用例属性"),
    EDIT_DATA("修改用例数据")

    String desc

    CaseEditType(String desc) {
        this.desc = desc
    }

}