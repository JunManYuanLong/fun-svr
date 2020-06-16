package com.okay.family.utils


import com.okay.family.common.bean.testuser.TestUserCheckBean
import com.okay.family.common.enums.UserState
import com.okay.family.fun.frame.SourceCode

class UserUtil extends SourceCode {

/**
 * 更新用户凭据
 * @param bean
 */
    static void updateUserStatus(TestUserCheckBean bean) {
        int identity = bean.getIdentity()
        switch (identity) {
            case 1:

                bean.setStatus(UserState.NO.getCode())
                break
            case 2:

                bean.setStatus(UserState.NO.getCode())
                break
            case 3:

                bean.setStatus(UserState.NO.getCode())
                break
            case 4:

                bean.setStatus(UserState.NO.getCode())
                break
            default:
                bean.setStatus(UserState.CANNOT.getCode())
                break
        }

    }

/**
 * 检查用户登录状态,处理用户凭据是否可用
 * @param bean
 * @return
 */
    static boolean checkUserLoginStatus(TestUserCheckBean bean) {
        int identity = bean.getRoleId()
        switch (identity) {
            case 1:

                return true
            case 2:

                return true
            case 3:

                return true
            case 4:

                break
            default:
                return false
        }
    }

/**
 * 验证用户信息是否可用,处理从接口获取到的用户参数
 * @param bean
 * @return
 */
    private boolean checkUserAvailable(TestUserCheckBean bean) {

        def environment = bean.getEnvironment()

        def identity = bean.getIdentity()

        def uname = bean.getUname()

        def pwd = bean.getPwd()



    }
}
