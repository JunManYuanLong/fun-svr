package com.okay.family.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okay.family.common.basedata.NodeLock;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.basedata.UserLock;
import com.okay.family.common.bean.common.DelBean;
import com.okay.family.common.bean.testuser.TestUserCheckBean;
import com.okay.family.common.bean.testuser.request.EditUserBean;
import com.okay.family.common.bean.testuser.request.SearchUserBean;
import com.okay.family.common.bean.testuser.response.TestUserBean;
import com.okay.family.common.code.TestUserCode;
import com.okay.family.common.enums.UserState;
import com.okay.family.common.exception.UserStatusException;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.fun.utils.Time;
import com.okay.family.mapper.TestUserMapper;
import com.okay.family.service.ICommonService;
import com.okay.family.service.ITestUserService;
import com.okay.family.utils.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class TestUserServiceImpl implements ITestUserService {

    private static Logger logger = LoggerFactory.getLogger(TestUserServiceImpl.class);

    TestUserMapper testUserMapper;

    ICommonService commonService;

    public TestUserServiceImpl(TestUserMapper testUserMapper, ICommonService commonService) {
        this.testUserMapper = testUserMapper;
        this.commonService = commonService;
    }

    /**
     * 搜索测试用户
     *
     * @param bean
     * @return
     */
    @Override
    public PageInfo<TestUserBean> findUsers(SearchUserBean bean) {
        PageHelper.startPage(bean.getPageNum(), bean.getPageSize());
        List<TestUserBean> users = testUserMapper.findUsers(bean);
        PageInfo<TestUserBean> result = new PageInfo(users);
        return result;
    }

    /**
     * 添加测试用户,如果登录失败,回滚
     *
     * @param user
     * @return
     */
    @Override
    public int addUser(EditUserBean user) {
        int add = testUserMapper.addUser(user);
        if (add == 1) {
            TestUserCheckBean userCheckBean = new TestUserCheckBean();
            userCheckBean.copyFrom(user);
            int i = updateUserStatus(userCheckBean);
            if (i != 1 || StringUtils.isEmpty(userCheckBean.getCertificate())) {
                UserStatusException.fail(TestUserCode.CHECK_FAIL.getDesc());
            }
        } else {
            UserStatusException.fail(TestUserCode.ADD_USER_FAIL.getDesc());
        }
        return user.getId();
    }

    /**
     * 获取某个测试用户的详情
     *
     * @param id
     * @return
     */
    @Override
    public TestUserCheckBean findUser(int id) {
        return testUserMapper.findUser(id);
    }

    /**
     * 更新用户信息,同样会校验登录状态
     *
     * @param bean
     * @return
     */
    @Override
    public int updateUser(EditUserBean bean) {
        TestUserCheckBean checkBean = new TestUserCheckBean();
        checkBean.copyFrom(bean);
        updateUserStatus(checkBean);
        int i = testUserMapper.updateUser(bean);
        return i;
    }

    /**
     * 更新用户登录状态,全局锁+分布式锁
     *
     * @param bean
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public int updateUserStatus(TestUserCheckBean bean) {
        int userLock = NodeLock.getUserLock(bean.getId());
        int lock = commonService.lock(userLock);
        if (lock == 0) {
            logger.info("分布式锁竞争失败,ID:{}", bean.getId());
            int i = 0;
            while (true) {
                SourceCode.sleep(OkayConstant.WAIT_INTERVAL);
                TestUserCheckBean user = testUserMapper.findUser(bean.getId());
                String create_time = user.getCreate_time();
                long create = Time.getTimestamp(create_time);
                long now = Time.getTimeStamp();
                if (now - create < OkayConstant.CERTIFICATE_TIMEOUT && user.getStatus() == UserState.OK.getCode()) {
                    bean.copyFrom(user);
                    return testUserMapper.updateUserStatus(bean);
                }
                if (i++ > OkayConstant.WAIT_MAX_TIME) {
                    UserStatusException.fail("获取分布式锁超时,无法更新用户凭据:id:" + bean.getId());
                }
            }
        } else {
            logger.info("分布式锁竞争成功,ID:{}", bean.getId());
            try {
                TestUserCheckBean user = testUserMapper.findUser(bean.getId());
                boolean b = StringUtils.isBlank(user.getCertificate()) ? false : UserUtil.checkUserLoginStatus(user);
                if (b) {
                    bean.copyFrom(user);
                } else {
                    UserUtil.updateUserStatus(bean);
                }
                return testUserMapper.updateUserStatus(bean);
            } catch (Exception e) {
                UserStatusException.fail("用户验证失败!ID:" + bean.getId());
                return 0;
            } finally {
                commonService.unlock(userLock);
            }
        }
    }


    /**
     * 校验用户登录凭据状态
     *
     * @param bean
     * @return
     */
    @Override
    public boolean checkUser(TestUserCheckBean bean) {
        return UserUtil.checkUserLoginStatus(bean);
    }

    /**
     * 校验用户状态,暂时无用,本来用来定期更新用户状态
     *
     * @param id
     * @return
     */
    @Override
    public boolean checkUser(int id) {
        TestUserCheckBean user = testUserMapper.findUser(id);
        return checkUser(user);
    }


    /**
     * 获取用户登录凭据
     *
     * @param id
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public String getCertificate(int id) {
        Object o = UserLock.get(id);
        synchronized (o) {
            TestUserCheckBean user = testUserMapper.findUser(id);
            if (user == null) UserStatusException.fail("用户不存在,ID:" + id);
            String create_time = user.getCreate_time();
            long create = Time.getTimestamp(create_time);
            long now = Time.getTimeStamp();
            if (now - create < OkayConstant.CERTIFICATE_TIMEOUT && user.getStatus() == UserState.OK.getCode()) {
                testUserMapper.updateUserStatus(user);
                return user.getCertificate();
            }
            boolean b = UserUtil.checkUserLoginStatus(user);
            logger.info("环境:{},用户:{},身份:{},登录状态验证:{}", user.getEnvId(), user.getId(), user.getRoleId(), b);
            if (!b) {
                updateUserStatus(user);
            } else {
                testUserMapper.updateUserStatus(user);
            }
            return user.getCertificate();
        }
    }

    /**
     * 获取用户登录凭据,map缓存
     *
     * @param id
     * @param map
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public String getCertificate(int id, ConcurrentHashMap<Integer, String> map) {
        if (map.containsKey(id)) return map.get(id);
        Object o = UserLock.get(id);
        synchronized (o) {
            if (map.containsKey(id)) return map.get(id);
            logger.warn("非缓存读取用户数据{}", id);
            TestUserCheckBean user = testUserMapper.findUser(id);
            if (user == null) UserStatusException.fail("用户不存在,ID:" + id);
            String create_time = user.getCreate_time();
            long create = Time.getTimestamp(create_time);
            long now = Time.getTimeStamp();
            if (now - create < OkayConstant.CERTIFICATE_TIMEOUT && user.getStatus() == UserState.OK.getCode()) {
                map.put(id, user.getCertificate());
                return user.getCertificate();
            }
            boolean b = UserUtil.checkUserLoginStatus(user);
            logger.info("环境:{},用户:{},身份:{},登录状态验证:{}", user.getEnvId(), user.getId(), user.getRoleId(), b);
            if (!b) {
                updateUserStatus(user);
                if (user.getStatus() != UserState.OK.getCode()) UserStatusException.fail("用户不可用,ID:" + id);
            } else {
                testUserMapper.updateUserStatus(user);
            }
            map.put(id, user.getCertificate());
            return user.getCertificate();
        }
    }

    @Override
    public int delUesr(DelBean bean) {
        int i = testUserMapper.delUser(bean);
        return i;
    }


}
