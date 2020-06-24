package com.okay.family.common.bean.casecollect

import com.okay.family.fun.base.bean.AbstractBean

class CollectionRunResultRecord extends AbstractBean{

    private static final long serialVersionUID = 48438627439874932;

    int id

    String name

    int collectionId

    int envId

    int runId

    int caseNum

    int result

    int success

    int fail

    int unrun

    int user_error

    String start_time

    String end_time

}
