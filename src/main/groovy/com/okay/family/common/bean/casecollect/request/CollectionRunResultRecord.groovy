package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean

class CollectionRunResultRecord extends AbstractBean{

    private static final long serialVersionUID = 48438627439874932;

    int collectionId

    int uid

    int envId

    int runId

    String name

    int caseNum

    int result

    int success

    int fail

    int unrun

    int userError

    String start

    String end

}
