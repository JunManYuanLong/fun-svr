package com.okay.family.common.bean.testcase.response

import com.okay.family.fun.base.bean.AbstractBean

/**
 * 测试用例编辑历史
 */
class CaseEditRecord extends AbstractBean {

    private static final long serialVersionUID = -6290432268998729332L;


    int id

    int caseId

    int editor

    String create_time

    String type

    CaseEditRecord(int caseId, int editor, String type) {
        this.caseId = caseId
        this.editor = editor
        this.type = type
    }
}
