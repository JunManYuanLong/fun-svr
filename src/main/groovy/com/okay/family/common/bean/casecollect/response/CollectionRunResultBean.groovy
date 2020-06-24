package com.okay.family.common.bean.casecollect.response


import com.okay.family.common.bean.testcase.response.ListCaseRunResultBean
import com.okay.family.fun.base.bean.AbstractBean
/**
 * 返回给前端的测试结果
 */
class CollectionRunResultBean extends AbstractBean{

    private static final long serialVersionUID = 32454657765756;

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

    String create_time
    
    List<ListCaseRunResultBean> results


}
