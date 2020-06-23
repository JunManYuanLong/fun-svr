package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

/**
 * 用例集编辑记录
 */
class CaseCollectionEditRecord extends AbstractBean {

    private static final long serialVersionUID = 7314229831207231048L;


    int collectionId

    int editor

    String create_time

    int type

    CaseCollectionEditRecord(int collectionId, int editor, int type) {
        this.collectionId = collectionId
        this.editor = editor
        this.type = type
    }

}
