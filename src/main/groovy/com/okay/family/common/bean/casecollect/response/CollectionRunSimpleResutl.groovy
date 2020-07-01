package com.okay.family.common.bean.casecollect.response

import com.okay.family.fun.base.bean.AbstractBean

/**
 * 用例集运行的简单结果
 */
class CollectionRunSimpleResutl extends AbstractBean {

    private static final long serialVersionUID = 43439867210;

    Integer runId

    Integer caseNum

    String start

    String end

    String result

    Integer success

    Integer fail

    Integer unrun

    Integer userError

}
