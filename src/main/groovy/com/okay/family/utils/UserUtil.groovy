package com.okay.family.utils

import com.okay.family.common.bean.testuser.TestUserCheckBean
import com.okay.family.common.enums.Identity
import com.okay.family.common.enums.UserState
import com.okay.family.fun.base.exception.LoginException
import com.okay.family.fun.frame.SourceCode
import com.okay.family.middle.pubweb.PubWebBase
import com.okay.family.middle.stuapp.StuAppBase
import com.okay.family.middle.stupad.StuPadBase
import com.okay.family.middle.stuweb.StuWebBase
import com.okay.family.middle.teapad.TeaPadBase
import com.okay.family.middle.teaweb.TeaWebBase
import org.apache.commons.lang3.StringUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class UserUtil extends SourceCode {

    private static Logger logger = LoggerFactory.getLogger(UserUtil.class)

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
                def base = new StuWebBase(user, password, envId)
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
            case Identity.TEA_WEB.getCode():
                try {
                    def base = new TeaWebBase(user, password, envId)
                    def cer = base.getCertificate()
                    if (StringUtils.isEmpty(cer)) {
                        bean.setStatus(UserState.NO.getCode())
                    } else {
                        bean.setStatus(UserState.OK.getCode())
                        bean.setCertificate(cer)
                    }
                } catch (LoginException e1) {
                    logger.warn("教师空间用户{}登录验证失败!", user, e1)
                    bean.setStatus(UserState.NO.getCode())
                } catch (Exception e) {
                    logger.warn("教师空间用户{}无法验证!", user, e)
                    bean.setStatus(UserState.CANNOT.getCode())
                }
                logger.info("登录状态校验结果:{}",bean.toString())
                break
            case Identity.PUB_WEB.getCode():
                try {
                    def base = new PubWebBase(user, password, envId)
                    def cer = base.getCertificate()
                    if (StringUtils.isEmpty(cer)) {
                        bean.setStatus(UserState.NO.getCode())
                    } else {
                        bean.setStatus(UserState.OK.getCode())
                        bean.setCertificate(cer)
                    }
                } catch (LoginException e1) {
                    logger.warn("公立校用户{}登录验证失败!", user, e1)
                    bean.setStatus(UserState.NO.getCode())
                } catch (Exception e) {
                    logger.warn("公立校用户{}无法验证!", user, e)
                    bean.setStatus(UserState.CANNOT.getCode())
                }
                logger.info("登录状态校验结果:{}",bean.toString())
                break
            case Identity.STU_APP.getCode():
                try {
                    def base = new StuAppBase(user, password, envId)
                    def cer = base.getCertificate()
                    if (StringUtils.isEmpty(cer)) {
                        bean.setStatus(UserState.NO.getCode())
                    } else {
                        bean.setStatus(UserState.OK.getCode())
                        bean.setCertificate(cer)
                    }
                } catch (LoginException e1) {
                    logger.warn("公立校用户{}登录验证失败!", user, e1)
                    bean.setStatus(UserState.NO.getCode())
                } catch (Exception e) {
                    logger.warn("公立校用户{}无法验证!", user, e)
                    bean.setStatus(UserState.CANNOT.getCode())
                }
                logger.info("登录状态校验结果:{}",bean.toString())
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
            case Identity.STU_PAD.getCode():
                def base = new StuPadBase(bean.getCertificate(), bean.getEnvId())
                def status = base.checkLoginStatus()
                bean.setStatus(status ? UserState.OK.getCode() : UserState.NO.getCode())
                return status
            case Identity.TEA_PAD.getCode():
                def base = new TeaPadBase(bean.getCertificate(), bean.getEnvId())
                def status = base.checkLoginStatus()
                bean.setStatus(status ? UserState.OK.getCode() : UserState.NO.getCode())
                return status
            case Identity.STU_WEB.getCode():
                def base = new StuWebBase(bean.getCertificate(), bean.getEnvId())
                def status = base.checkLoginStatus()
                bean.setStatus(status ? UserState.OK.getCode() : UserState.NO.getCode())
                return status
            case Identity.TEA_WEB.getCode():
                def base = new TeaWebBase(bean.getCertificate(), bean.getEnvId())
                def status = base.checkLoginStatus()
                bean.setStatus(status ? UserState.OK.getCode() : UserState.NO.getCode())
                return status
            case Identity.PUB_WEB.getCode():
                def base = new PubWebBase(bean.getCertificate(), bean.getEnvId())
                def status = base.checkLoginStatus()
                bean.setStatus(status ? UserState.OK.getCode() : UserState.NO.getCode())
                return status
            case Identity.STU_APP.getCode():
                def base = new StuAppBase(bean.getCertificate(), bean.getEnvId())
                def status = base.checkLoginStatus()
                bean.setStatus(status ? UserState.OK.getCode() : UserState.NO.getCode())
                return status
            default:
                return false
        }
    }


}
