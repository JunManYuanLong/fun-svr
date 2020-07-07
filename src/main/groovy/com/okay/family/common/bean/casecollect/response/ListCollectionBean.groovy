package com.okay.family.common.bean.casecollect.response

import com.okay.family.fun.base.bean.AbstractBean

/**
 * 用例集列表展示
 */
class ListCollectionBean extends AbstractBean {

    private static final long serialVersionUID = -14894856161;


    Integer id

    /**
     * 0 非本人 1本人
     */
    Integer isMyself
    /**
     * 0 未共享 1已共享
     */
    Integer isShare

    String result

    String name

    String envName

    Integer envId

    String createTime


}
