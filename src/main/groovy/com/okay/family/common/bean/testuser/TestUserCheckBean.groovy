package com.okay.family.common.bean.testuser

import com.okay.family.common.bean.testuser.request.EditUserBean
import com.okay.family.fun.base.bean.AbstractBean
import com.okay.family.fun.config.Constant

/**
 * 用于验证测试用户
 */
class TestUserCheckBean extends AbstractBean {

    private static final long serialVersionUID = -98489156165;

    Integer id

    Integer uid

    Integer envId

    String user

    Integer roleId

    String password

    String phone

    String des

    Integer status

    String certificate = Constant.EMPTY

    String create_time

    def copyFrom(EditUserBean user) {
        this.setId(user.getId());
        this.setEnvId(user.getEnvId());
        this.setRoleId(user.getRoleId());
        this.setUser(user.getUser());
        this.setPassword(user.getPassword());
        this.setPhone(user.getPhone());
        this.setUid(user.getUid());
        this.setDes(user.getDes());
    }

    def copyFrom(TestUserCheckBean user) {
        this.setId(user.getId());
        this.setEnvId(user.getEnvId());
        this.setRoleId(user.getRoleId());
        this.setUser(user.getUser());
        this.setPassword(user.getPassword());
        this.setPhone(user.getPhone());
        this.setUid(user.getUid());
        this.setDes(user.getDes());
        this.status = user.getStatus()
        this.create_time = user.getCreate_time()
        this.certificate = user.getCertificate()
    }

/**
 * 是否是同一个用户,检查用户关键信息是否更改,用于判断是否要重新登录用户获取最近的凭据
 * @param bean
 * @return
 */
    boolean same(TestUserCheckBean bean) {
        this.user == bean.user && this.password == bean.password && this.roleId == bean.roleId;
    }

}
