package com.okay.family.common.bean.testcase.response

import com.okay.family.common.bean.common.ProjectBean
import com.okay.family.fun.base.bean.AbstractBean
/**
 * 用例属性编辑
 */
class TestCaseAttributeBean extends AbstractBean {

    private static final long serialVersionUID = -1595563071153477L;

    Integer id

    Integer apiId

    String apiName

    Integer envId

    String envName

    Integer moduleId

    String moduleName

    String name

    Integer serviceId

    String serviceName

    List<ProjectBean> versionList

/**
 * 用于异常查询结果赋值
 * @param bean
 */
    def copyFrom(TestCaseAttributeBean bean) {
        this.id = bean.getId()
        this.apiId = bean.getApiId()
        this.apiName = bean.getApiName()
        this.envId = bean.getEnvId()
        this.envName = bean.getEnvName()
        this.moduleId = bean.getModuleId()
        this.moduleName = bean.getModuleName()
        this.name = bean.getName()
        this.serviceId = bean.getServiceId()
        this.serviceName = bean.getServiceName()
    }

}
