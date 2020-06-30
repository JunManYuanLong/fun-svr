package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

class CollectionRunResultRecord extends AbstractBean{

    private static final long serialVersionUID = 48438627439874932;

    Integer collectionId

    Integer uid

    Integer envId

    Integer runId

    String name

    Integer caseNum

    Integer result

    Integer success

    Integer fail

    Integer unrun

    Integer userError

    String start

    String end

}
