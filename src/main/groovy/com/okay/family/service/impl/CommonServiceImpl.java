package com.okay.family.service.impl;

import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.basedata.ServerHost;
import com.okay.family.common.bean.RequestSaveBean;
import com.okay.family.common.exception.CommonException;
import com.okay.family.fun.config.Constant;
import com.okay.family.fun.utils.Time;
import com.okay.family.mapper.CommonMapper;
import com.okay.family.service.ICommonService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class CommonServiceImpl implements ICommonService {

    public static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    CommonMapper commonMapper;

    @Autowired
    public CommonServiceImpl(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

    /**
     * 缓存所有host信息,暂时弃用
     *
     * @return
     */
    @Override
    public Map<Integer, String> findAllHost() {
        List<HashMap<String, String>> hosts = commonMapper.findAllHost();
        Map<Integer, String> collect = hosts.stream().collect(Collectors.toMap((x -> {
            Object id = x.get("id");
            return Integer.parseInt(id.toString());
        }), (x -> {
            String domain = x.get("domain");
            return domain.endsWith("/") ? domain.substring(0, domain.length() - 1) : domain;
        })));
        return collect;
    }

    /**
     * 记录请求
     *
     * @param bean
     */
    @Async
    @Override
    public void saveRequest(RequestSaveBean bean) {
        commonMapper.saveRequest(bean);
    }

    /**
     * 获取host,缓存
     *
     * @param envId
     * @param service_id
     * @return
     */
    @Override
    public String getHost(int envId, int service_id) {
        String host = ServerHost.getHost(envId, service_id);
        if (StringUtils.isBlank(host)) {
            host = commonMapper.getHost(envId, service_id);
            if (StringUtils.isBlank(host)) CommonException.fail("域名配置错误!环境ID:" + envId + "服务ID:" + service_id);
            if (host.endsWith(Constant.OR)) host = host.substring(0, host.length() - 1);
            if (StringUtils.isBlank(host) || !host.startsWith("http")) CommonException.fail("服务ID:{},环境ID:{}域名配置错误");
            ServerHost.putHost(envId, service_id, host);
        }
        return host;
    }

    /**
     * 加锁
     *
     * @param lock
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public int lock(long lock) {
        try {
            return commonMapper.lock(lock);
        } catch (DuplicateKeyException e) {
            return 0;
        }
    }

    /**
     * 释放锁
     *
     * @param lock
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRES_NEW)
    public int unlock(long lock) {
        int unlock = commonMapper.unlock(lock);
        return unlock;
    }

    /**
     * 删除过期锁
     */
    @Override
    public void clearLock() {
        commonMapper.clearLock(Time.getTimeByTimestamp(Time.getTimeStamp() - OkayConstant.LOCK_TIMEOUT));
    }


}
