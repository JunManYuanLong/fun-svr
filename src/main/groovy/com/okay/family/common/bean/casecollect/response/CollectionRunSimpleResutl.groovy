package com.okay.family.common.bean.casecollect.response

import com.okay.family.fun.base.bean.AbstractBean

class CollectionRunSimpleResutl extends AbstractBean {

    private static final long serialVersionUID = 43439867210;

    int runId

    int caseNum

    String start

    String end

    String result

    int success

    int fail

    int unrun

    int userError

}
