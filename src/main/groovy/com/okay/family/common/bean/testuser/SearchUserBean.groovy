package com.okay.family.common.bean.testuser

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min

class SearchUserBean extends AbstractBean {

    private static final long serialVersionUID = 894894891651651L;

    int uid

    int envId

    @Min(value = 1L)
    int pageNum
    @Range(min = 5L, max = 20L, message = "每页显示数量设置错误")
    int pageSize

    int roleId

    int status

    String query

    @Range(min = 0l, max = 2L, message = "搜索类型出错!0账号1手机号2用户id")
    int type

}
