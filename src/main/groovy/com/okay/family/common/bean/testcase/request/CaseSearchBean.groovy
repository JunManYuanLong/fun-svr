package com.okay.family.common.bean.testcase.request

import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.frame.SourceCode
import org.apache.commons.lang3.StringUtils
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min

class CaseSearchBean extends AbstractBean {

    private static final long serialVersionUID = -869483426556;

    int id

    @Min(value = 1L)
    int uid

    int envId

    int apiId

    @Range(min = 0L, max = 2L, message = "是否本人参数错误")
    int isMyself

    @Range(min = 0L, max = 2L, message = "是否可用参数错误")
    int isUsed

    int serviceId

    int moduleId

    @Min(value = 1L)
    int pageNum

    @Range(min = 5L, max = 10L, message = "单页数值设置错误!")
    int pageSize

    int projectId

    String testQuery

    /**
     * 处理搜索类型,1:用例名模糊搜索,2:用例id精确搜索
     */
    int type


    def init() {
        if (StringUtils.isEmpty(testQuery)) return
        if (SourceCode.isNumber(testQuery)) type = 2
        else type = 1
    }


}
