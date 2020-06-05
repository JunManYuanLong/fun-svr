package com.okay.family.utils

import com.okay.family.common.bean.testuser.TestUserBean
import com.okay.family.fun.frame.SourceCode

class UserUtil extends SourceCode {

/**
 * 更新用户凭据
 * @param bean
 */
    static void updateUserStatus(TestUserBean bean) {
        int identity = bean.getIdentity()
        switch (identity) {
            case 1:

                bean.setStatus(2)
                break
            case 2:

                bean.setStatus(2)
                break
            case 3:

                bean.setStatus(2)
                break
            case 4:

                bean.setStatus(2)
                break
            default:
                bean.setStatus(4)
                break
        }

    }

/**
 * 检查用户登录状态,处理用户凭据是否可用
 * @param bean
 * @return
 */
    static boolean checkUserLoginStatus(TestUserBean bean) {
        int identity = bean.getIdentity()
        switch (identity) {
            case 1:

                bean.setStatus(2)
                return true
            case 2:

                bean.setStatus(2)
                return true
            case 3:

                bean.setStatus(2)
                return true
            case 4:

                bean.setStatus(2)
                break
            default:
                bean.setStatus(4)
                return false
        }
    }

/**
 * 验证用户信息是否可用,处理从接口获取到的用户参数
 * @param bean
 * @return
 */
    static boolean checkUserAvailable(TestUserBean bean) {

        def environment = bean.getEnvironment()

        def identity = bean.getIdentity()

        def uname = bean.getUname()

        def pwd = bean.getPwd()



    }
}
