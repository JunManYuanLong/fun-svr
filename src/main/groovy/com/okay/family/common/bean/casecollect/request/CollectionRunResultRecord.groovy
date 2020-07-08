package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.config.Constant

/**
 * 用例集运行结果
 */
class CollectionRunResultRecord extends AbstractBean{

    private static final long serialVersionUID = 48438627439874932;

    Integer collectionId

    Integer uid

    Integer envId

    Integer runId

    String name = Constant.EMPTY

    Integer caseNum

    Integer result

    Integer success

    Integer fail

    Integer unrun

    Integer userError

    String start

    String end

}
