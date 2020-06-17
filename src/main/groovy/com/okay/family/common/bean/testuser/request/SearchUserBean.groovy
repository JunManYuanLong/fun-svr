package com.okay.family.common.bean.testuser.request

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class SearchUserBean extends AbstractBean {

    private static final long serialVersionUID = 894894891651651L;

    int uid

    @Range(min = 0L, max = OkayConstant.ENV, message = "环境参数错误")
    int envId

    @Min(value = 1L)
    int pageNum

    @Range(min = 5L, max = 20L, message = "每页显示数量设置错误")
    int pageSize

    @Range(min = 0L, max = 10L, message = "用户身份参数错误")
    int roleId

    @Range(min = 0L, max = OkayConstant.USER_STATUS, message = "用户状态参数错误")
    int status

    @NotNull
    String query

    @Range(min = 0l, max = 2L, message = "搜索类型出错!0账号1手机号2用户id")
    int type

}
