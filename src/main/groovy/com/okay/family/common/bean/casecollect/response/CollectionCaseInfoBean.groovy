package com.okay.family.common.bean.casecollect.response

import com.okay.family.fun.base.bean.AbstractBean

/**
 * 用例集信息
 */
class CollectionCaseInfoBean extends AbstractBean {

    private static final long serialVersionUID = 69384703278;

    Integer id

    Integer uid

    Integer envId

    String groupName

    /**
     * 0非本人 1 本人
     */
    Integer isMyself

    List<ListCaseBean> list

    def copyFrom(CollectionCaseInfoBean bean) {
        this.envId = bean.getEnvId()
        this.groupName = bean.getGroupName()
        this.isMyself = bean.getIsMyself()
        this.uid = null
    }

}
