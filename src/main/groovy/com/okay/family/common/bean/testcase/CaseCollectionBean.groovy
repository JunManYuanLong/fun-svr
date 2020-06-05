package com.okay.family.common.bean.testcase

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

class CaseCollectionBean extends AbstractBean {

    private static final long serialVersionUID = -1595948467071153477L;

    int owner

    @Range(min = 1L, max = 5L, message = "环境设置错误")
    int environment

    int server

    String cases

    String projects

    int status


}
