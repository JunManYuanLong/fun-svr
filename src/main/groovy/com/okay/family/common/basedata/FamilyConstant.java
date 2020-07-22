package com.okay.family.common.basedata;

import com.okay.family.fun.config.PropertyUtils;

/**
 * 从配置文件读取的常量
 */
public class FamilyConstant {

    static PropertyUtils.Property property = PropertyUtils.getProperties("family");


    /**
     * 是否区分登录选项,针对stress环境临时设置
     */
    public static boolean TEA_WEB_LOGIN = property.getPropertyBoolean("teaweblogin");

}
