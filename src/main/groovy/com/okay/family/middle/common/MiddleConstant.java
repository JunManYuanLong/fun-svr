package com.okay.family.middle.common;

import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.fun.config.PropertyUtils;

import java.util.HashMap;
import java.util.Map;

public class MiddleConstant {

    static PropertyUtils.Property property = PropertyUtils.getProperties("middle");

    /*学生pad host*/
    public static final String STUPAD_HOTFIX_HOST = property.getProperty("stupad_hotfix_host");

    public static final String STUPAD_DEV_HOST = property.getProperty("stupad_dev_host");

    public static final String STUPAD_STRESS_HOST = property.getProperty("stupad_stress_host");

    public static final String STUPAD_ONLINE_HOST = property.getProperty("stupad_online_host");

    /*教师pad host*/

    public static final String TEAPAD_HOTFIX_HOST = property.getProperty("teapad_hotfix_host");

    public static final String TEAPAD_DEV_HOST = property.getProperty("teapad_dev_host");

    public static final String TEAPAD_STRESS_HOST = property.getProperty("teapad_stress_host");

    public static final String TEAPAD_ONLINE_HOST = property.getProperty("teapad_online_host");


    /*学生空间  host*/
    public static final String STUWEB_HOTFIX_HOST = property.getProperty("stuweb_hotfix_host");

    public static final String STUWEB_DEV_HOST = property.getProperty("stuweb_dev_host");

    public static final String STUWEB_STRESS_HOST = property.getProperty("stuweb_stress_host");

    public static final String STUWEB_ONLINE_HOST = property.getProperty("stuweb_online_host");

    /*教师空间  host*/
    public static final String TEAWEB_HOTFIX_HOST = property.getProperty("teaweb_hotfix_host");

    public static final String TEAWEB_DEV_HOST = property.getProperty("teaweb_dev_host");

    public static final String TEAWEB_STRESS_HOST = property.getProperty("teaweb_stress_host");

    public static final String TEAWEB_ONLINE_HOST = property.getProperty("teaweb_online_host");


    /*公立校  host*/
    public static final String PUBWEB_HOTFIX_HOST = property.getProperty("pubweb_hotfix_host");

    public static final String PUBWEB_DEV_HOST = property.getProperty("pubweb_dev_host");

    public static final String PUBWEB_STRESS_HOST = property.getProperty("pubweb_stress_host");

    public static final String PUBWEB_ONLINE_HOST = property.getProperty("pubweb_online_host");

    /*学生pad服务所用  host*/
    public static final String STUAPP_HOTFIX = property.getProperty("stuapp_hotfix_host");

    public static final String STUAPP_DEV = property.getProperty("stuapp_dev_host");

    public static final String STUAPP_STRESS = property.getProperty("stuapp_stress_host");

    public static final String STUAPP_ONLINE = property.getProperty("stuapp_online_host");

    /*cas服务所用  host*/
    public static final String CAS_HOTFIX = property.getProperty("cas_hotfix");

    public static final String CAS_DEV = property.getProperty("cas_dev");

    public static final String CAS_STRESS = property.getProperty("cas_stress");

    public static final String CAS_ONLINE = property.getProperty("cas_online");


    /*加密公钥*/

    public static final String PAD_HOTFIXT_PUBKEY = property.getProperty("pad_hotfix_pubkey");

    public static final String PAD_DEV_PUBKEY = property.getProperty("pad_dev_pubkey");

    public static final String PAD_STRESS_PUBKEY = property.getProperty("pad_stress_pubkey");

    public static final String PAD_ONLINE_PUBKEY = property.getProperty("pad_online_pubkey");

    /*加密盐*/

    public static final String SALT_HOTFIX = property.getProperty("salt_hotfix");

    public static final String SALT_DEV = property.getProperty("salt_dev");

    public static final String SALT_STRESS = property.getProperty("salt_stress");

    public static final String SALT_ONLINE = property.getProperty("salt_online");

    /**
     * 通用验证码
     */
    public static final String CAPTCHA = property.getProperty("captcha");

    /**
     * 密码加密过程中实例类型
     */
    public final static String DES = property.getProperty("des");

    public static Map<Integer, String> salts = new HashMap<Integer, String>((int) OkayConstant.ENV) {
        private static final long serialVersionUID = 62254732323261844L;

        {
            put(OkayConstant.HOTFIX, MiddleConstant.SALT_HOTFIX);
            put(OkayConstant.DEV, MiddleConstant.SALT_DEV);
            put(OkayConstant.STRESS, MiddleConstant.SALT_STRESS);
            put(OkayConstant.ONLINE, MiddleConstant.SALT_ONLINE);
        }
    };


}
