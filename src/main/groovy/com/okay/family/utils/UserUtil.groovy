package com.okay.family.utils


import com.okay.family.common.bean.testuser.TestUserCheckBean
import com.okay.family.common.bean.testuser.request.EditUserBean
import com.okay.family.common.enums.Identity
import com.okay.family.common.enums.UserState
import com.okay.family.fun.frame.SourceCode
import com.okay.family.middle.stupad.StuPadBase
import com.okay.family.middle.teapad.TeaPadBase
import org.apache.commons.lang3.StringUtils

class UserUtil extends SourceCode {

/**
 * 更新用户凭据,设置用户状态
 * @param bean
 */
    static void updateUserStatus(TestUserCheckBean bean) {
        def role = bean.getRoleId()
        def envId = bean.getEnvId()
        def user = bean.getUser()
        def password = bean.getPassword()
        def phone = bean.getPhone()
        switch (role) {
            case Identity.STU_PAD.getCode():
                def base = new StuPadBase(user, password, envId)
                def cer = base.getCertificate()
                if (base.loginResponse == null || base.loginResponse.getIntValue(RESPONSE_CODE) == TEST_ERROR_CODE) {
                    bean.setStatus(UserState.CANNOT.getCode())
                } else if (!base.isRight(base.loginResponse) || StringUtils.isEmpty(cer)) {
                    bean.setStatus(UserState.NO.getCode())
                } else {
                    bean.setStatus(UserState.OK.getCode())
                    bean.setCertificate(cer)
                }
                break
            case Identity.TEA_PAD.getCode():
                def base = new TeaPadBase(user, password, envId)
                def cer = base.getCertificate()
                if (base.getLoginResponse() == null || base.getLoginResponse().getIntValue(RESPONSE_CODE) == TEST_ERROR_CODE) {
                    bean.setStatus(UserState.CANNOT.getCode())
                } else if (!base.isRight(base.getLoginResponse()) || StringUtils.isEmpty(cer)) {
                    bean.setStatus(UserState.NO.getCode())
                } else {
                    bean.setStatus(UserState.OK.getCode())
                    bean.setCertificate(cer)
                }
                break
            case Identity.STU_WEB.getCode():

                bean.setStatus(UserState.NO.getCode())
                break
            case Identity.TEA_WEB.getCode():

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
        def role = bean.getRoleId()
        switch (role) {
            case 1:
                def base = new StuPadBase(bean.getCertificate(), bean.getEnvId())
                def status = base.checkLoginStatus()
                bean.setStatus(status ? UserState.OK.getCode() : UserState.NO.getCode())
                return status
            case 2:
                def base = new TeaPadBase(bean.getCertificate(), bean.getEnvId())
                def status = base.checkLoginStatus()
                bean.setStatus(status ? UserState.OK.getCode() : UserState.NO.getCode())
                return status
            case 3:

                return true
            case 4:

                break
            default:
                return false
        }
    }

    static boolean checkUserLoginStatus(EditUserBean bean) {
        def checkBean = new TestUserCheckBean()
        checkBean.copyFrom(bean)
        checkUserLoginStatus(checkBean)
    }

}
