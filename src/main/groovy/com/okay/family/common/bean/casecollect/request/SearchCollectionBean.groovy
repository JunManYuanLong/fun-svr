package com.okay.family.common.bean.casecollect.request

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min

/**
 * 搜索用例集
 */
class SearchCollectionBean extends AbstractBean {

    private static final long serialVersionUID = -564894151549848961;

    @Min(value = 1L)
    Integer uid

    Integer envId

    String groupQuery

    @Min(value = 1L)
    Integer pageNum

    @Range(min = 5L, max = 20L, message = "每页显示数量设置错误")
    Integer pageSize

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
