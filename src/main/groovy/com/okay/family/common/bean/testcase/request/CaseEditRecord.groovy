package com.okay.family.common.bean.testcase.request

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

    int type

    CaseEditRecord(int caseId, int editor, int type) {
        this.caseId = caseId
        this.editor = editor
        this.type = type
    }
}
