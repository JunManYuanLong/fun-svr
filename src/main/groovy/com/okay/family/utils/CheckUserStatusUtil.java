package com.okay.family.utils;

import com.okay.family.common.bean.testuser.TestUserBean;
import com.okay.family.fun.frame.SourceCode;

public class CheckUserStatusUtil extends SourceCode {

    public static boolean checkUser(TestUserBean bean) {
        int environment = bean.getEnvironment();
        String uname = bean.getUname();
        String pwd = bean.getPwd();
        switch (environment) {
            case 1:

                return false;
            default:
                return false;
        }

    }


}
