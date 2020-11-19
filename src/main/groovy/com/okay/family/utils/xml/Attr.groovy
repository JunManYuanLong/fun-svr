package com.okay.family.utils.xml

import com.okay.family.fun.base.bean.AbstractBean


/**
 * 节点属性信息
 */
class Attr extends AbstractBean {

    private static final long serialVersionUID = -35484487563215649L

    String name

    String value

    public Attr(String name, String value) {
        this.name = name
        this.value = value
    }


}