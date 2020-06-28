package com.okay.family.common.bean.testcase.response

import com.okay.family.common.bean.common.SimpleBean
import com.okay.family.fun.base.bean.AbstractBean

class TestCaseAttributeBean extends AbstractBean {

    private static final long serialVersionUID = -1595563071153477L;

    int id

    int apiId

    String apiName

    int envId

    String envName

    int moduleId

    String moduleName

    String name

    int serviceId

    String serviceName

    List<SimpleBean> versionList

/**
 * 用于异常查询结果赋值
 * @param bean
 */
    def copyFrom(TestCaseAttributeBean bean) {
        this.id = bean.getId()
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
