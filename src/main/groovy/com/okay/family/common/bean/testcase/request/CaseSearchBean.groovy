package com.okay.family.common.bean.testcase.request

import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.frame.SourceCode
import org.apache.commons.lang3.StringUtils
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min

/**
 * 用例搜索
 */
class CaseSearchBean extends AbstractBean {

    private static final long serialVersionUID = -869483426556;

    @Min(value = 1L)
    Integer id

    @Min(value = 1L)
    Integer uid

    Integer envId

    @Min(value = 1L)
    Integer apiId

    @Range(min = 0L, max = 2L, message = "是否本人参数错误0全部，1本人，2非本人")
    Integer isMyself

    @Range(min = 0L, max = 2L, message = "是否可用参数错误\t0全部， 1可用，2不可用")
    Integer isUsed

    Integer serviceId

    Integer moduleId

    @Min(value = 1L)
    Integer pageNum

    @Range(min = 5L, max = 10L, message = "单页数值设置错误!")
    Integer pageSize

    Integer projectId

    String testQuery

    /**
     * 处理搜索类型,1:用例名模糊搜索,2:用例id精确搜索
     */
    Integer type


    def init() {
        if (StringUtils.isEmpty(testQuery)) return
        if (SourceCode.isNumber(testQuery)) type = 2
        else type = 1
    }


}
