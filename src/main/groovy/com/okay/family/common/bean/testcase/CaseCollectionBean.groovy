package com.okay.family.common.bean.testcase

import org.hibernate.validator.constraints.Range

class CaseCollectionBean {

    int owner

    @Range(min = 1L, max = 5L, message = "环境设置错误")
    int environment

    int server

    String cases

    String projects

    int status


}
