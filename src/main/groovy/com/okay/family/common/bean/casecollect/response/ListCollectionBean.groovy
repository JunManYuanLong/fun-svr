package com.okay.family.common.bean.casecollect.response

import com.okay.family.fun.base.bean.AbstractBean

class ListCollectionBean extends AbstractBean {

    private static final long serialVersionUID = -14894856161;


    int id

    /**
     * 0 非本人 1本人
     */
    int isMyself
    /**
     * 0 未共享 1已共享
     */
    int isShare

    String result

    String name

    String envName

    String createTime


}
