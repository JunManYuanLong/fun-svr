package com.okay.family.service.impl;

import com.okay.family.common.bean.RequestSaveBean;
import com.okay.family.common.exception.CommonException;
import com.okay.family.mapper.CommonMapper;
import com.okay.family.service.ICommonService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommonServiceImpl implements ICommonService {

    public static Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    CommonMapper commonMapper;

    @Autowired
    public CommonServiceImpl(CommonMapper commonMapper) {
        this.commonMapper = commonMapper;
    }

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

    @Async
    @Override
    public void saveRequest(RequestSaveBean bean) {
        commonMapper.saveRequest(bean);
    }

    @Override
    public String getHost(int envId, int service_id) {
        String host = commonMapper.getHost(envId, service_id);
        if (StringUtils.isBlank(host) || !host.startsWith("http")) CommonException.fail("服务ID:{},环境ID:{}域名配置错误");
        return host;
    }


}
