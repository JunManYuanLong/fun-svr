package com.okay.family.common.bean.casecollect.request

import com.okay.family.common.basedata.OkayConstant
import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.frame.SourceCode
import org.apache.commons.lang3.StringUtils
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

/**
 * 搜索用例集
 */
class SearchCollectionBean extends AbstractBean {

    private static final long serialVersionUID = -564894151549848961;

    @NotNull(message = "uid不能为空")
    @Min(value = 1L, message = "uid不能为空")
    Integer uid

    @NotNull(message = "envId不能为空")
    @Range(min = 0L, max = OkayConstant.ENV, message = "环境变量参数错误")
    Integer envId

    @NotNull(message = "groupQuery不能为空")
    String groupQuery

    @NotNull(message = "pageNum不能为空")
    @Min(value = 1L)
    Integer pageNum

    @NotNull(message = "pageSize不能为空")
    @Range(min = 5L, max = 20L, message = "每页显示数量设置错误")
    Integer pageSize

    @NotNull(message = "result不能为空")
    @Range(min = 0L, max = 3L, message = "运行结果参数错误")
    Integer result

    /**
     * 区分用例集名称和ID,1:用例集名称,2:id
     */
    Integer type

    def init() {
        if (StringUtils.isEmpty(groupQuery)) return
        if (SourceCode.isNumber(groupQuery)) type = 2
        else type = 1
    }

}
