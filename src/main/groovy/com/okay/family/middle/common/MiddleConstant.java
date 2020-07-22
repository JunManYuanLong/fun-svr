package com.okay.family.middle.common;

import com.okay.family.common.basedata.OkayConstant;

import java.util.HashMap;
import java.util.Map;

public class MiddleConstant {


    /*学生pad host*/
    public static final String STUPAD_HOTFIX_HOST = "https://stupad-hotfix.xk12.cn";

    public static final String STUPAD_DEV_HOST = "https://stupad-dev.xk12.cn";

    public static final String STUPAD_STRESS_HOST = STUPAD_HOTFIX_HOST;

    public static final String STUPAD_ONLINE_HOST = "https://stupad.okjiaoyu.cn";

    /*教师pad host*/

    public static final String TEAPAD_HOTFIX_HOST = "https://teacherpad-hotfix.xk12.cn";

    public static final String TEAPAD_DEV_HOST = "https://teacherpad-dev.xk12.cn";

    public static final String TEAPAD_STRESS_HOST = TEAPAD_HOTFIX_HOST;

    public static final String TEAPAD_ONLINE_HOST = "https://teacherpad.okjiaoyu.cn";


    /*学生空间  host*/
    public static final String STUWEB_HOTFIX_HOST = "https://student-hotfix.xk12.cn";

    public static final String STUWEB_DEV_HOST = "https://student-dev.xk12.cn";

    public static final String STUWEB_STRESS_HOST = STUWEB_HOTFIX_HOST;

    public static final String STUWEB_ONLINE_HOST = "https://student.okjiaoyu.cn";


    /*教师空间  host*/
    public static final String TEAWEB_HOTFIX_HOST = "https://student-hotfix.xk12.cn";

    public static final String TEAWEB_DEV_HOST = "https://jiaoshi-dev.xk12.cn";

    public static final String TEAWEB_STRESS_HOST = TEAWEB_HOTFIX_HOST;

    public static final String TEAWEB_ONLINE_HOST = "https://jiaoshi.okjiaoyu.cn";


    /*公立校  host*/
    public static final String PUBWEB_HOTFIX_HOST = "https://edu-hotfix.xk12.cn";

    public static final String PUBWEB_DEV_HOST = "https://edu-dev.xk12.cn";

    public static final String PUBWEB_STRESS_HOST = PUBWEB_HOTFIX_HOST;

    public static final String PUBWEB_ONLINE_HOST = "https://edu.okjiaoyu.cn";

    /*cas服务所用  host*/
    public static final String STUAPP_HOTFIX = "https://stupad-hotfix.xk12.cn";

    public static final String STUAPP_DEV = "https://xsapp-dev.xk12.cn";

    public static final String STUAPP_STRESS = STUAPP_HOTFIX;

    public static final String STUAPP_ONLINE = "https://stupad.okjiaoyu.cn";

    /*cas服务所用  host*/
    public static final String CAS_HOTFIX = "https://sso-hotfix.xk12.cn/login?service=";

    public static final String CAS_DEV = "https://sso-dev.xk12.cn/login?service=";

    public static final String CAS_STRESS = CAS_HOTFIX;

    public static final String CAS_ONLINE = "https://sso.okjiaoyu.cn/login?service=";


    /*加密公钥*/

    public static final String PAD_HOTFIXT_PUBKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCK5FAPQWGcX4uD9/tVzuZmwucXFqyxhS/ANjB6qFwbqoORhjRBDEBXKNE7iP4pVtiaRs6f8Bwj+QpoaquHgc8RKNLEUtKYPz0cRrTQYh0MqxK4m7wx7TkaJMmAzC5Br5t/VMxB5mexTuawJlZ3+MzEXgI1R2S/N4LDXzeHXCxQwwIDAQAB";

    public static final String PAD_DEV_PUBKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBvhQ1m/kDGjExBL3kV8a/fQL0Df6bPEUlQz+7GBCQgSZ5ZcJKZwzv/nwG+S2m5MH1efgr+98dY2b1jToVStiT9ltfPEtepwbIh0FbV78/lsizP7Ysqc0lrwxWXhpBVEHuG5qU3EiOQD5Xw1LIAoQJKjuqLs7j2GF6NQoJlUJnnQIDAQAB";

    public static final String PAD_STRESS_PUBKEY = PAD_HOTFIXT_PUBKEY;

    public static final String PAD_ONLINE_PUBKEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDoIRaohpg6ys0gA/tIvUL/2TtTxk3aRJpIByJe263lbCNmHA+Es+k9eyc/RT+M7lkqWvAPCeqDOQIOfb7GJ78rFOhyuUf/a/ZWXGgUrqf2hLQrzaeIngkfFEkM9AtOBUT9MDjYtZsaQikOtag68UfFPv7GoULUV+y7woqtjZhaHwIDAQAB";

    /*加密盐*/

    public static final String SALT_HOTFIX = "17802ec2980353bdc3f082b0668bd1e4";

    public static final String SALT_DEV = "17802ec2980353bdc3f082b0668bd1e4";

    public static final String SALT_STRESS = SALT_HOTFIX;

    public static final String SALT_ONLINE = "17802ec2980353bdc3f082b0668bd1e4";
    /**
     * 通用验证码
     */
    public static final String CAPTCHA = "performance";

    /**
     * 密码加密过程中实例类型
     */
    public final static String DES = "DES";

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
