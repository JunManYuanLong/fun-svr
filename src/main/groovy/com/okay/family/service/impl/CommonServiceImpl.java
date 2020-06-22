package com.okay.family.service.impl;

import com.okay.family.common.bean.RequestSaveBean;
import com.okay.family.mapper.CommonMapper;
import com.okay.family.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CommonServiceImpl implements ICommonService {

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
        }), (x -> "https://" + x.get("domain"))));
        return collect;
    }

    @Async
    @Override
    public void saveRequest(RequestSaveBean bean) {
        commonMapper.saveRequest(bean);
    }


}
