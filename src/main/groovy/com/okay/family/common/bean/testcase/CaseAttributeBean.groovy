package com.okay.family.common.bean.testcase

import com.okay.family.fun.base.bean.AbstractBean
import org.hibernate.validator.constraints.Range

import javax.validation.constraints.Min
import javax.validation.constraints.Pattern

class CaseAttributeBean extends AbstractBean {

    private static final long serialVersionUID = -629015126822729332L;

    int id

    /**
     * 创建人的id
     */
    @Min(value = 1L, message = "uid错误")
    int uid

    @Range(min = 1L, max = 5L, message = "环境设置错误")
    int envId

    /**
     * 关联项目id
     */
    //TODO:看数据结构做判断
    @Pattern(regexp = "(\\d*,)*\\d*", message = "关联项目ID格式错误")
    String project

    /**
     * 关联服务id
     */
    int serverid

    String servername

    /**
     * 关联服务模块id
     */
    @Min(value = 1L)
    int moduleId

    String name

    List<Integer> projectList

    /**
     * 关联接口id
     */
    int apiId

}
