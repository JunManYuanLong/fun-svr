package com.okay.family.controller;


import com.okay.family.common.basedata.ServerHost;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.service.ILiveUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 热更新接口
 */
@RestController
@RequestMapping(value = "/live")
public class LiveUpdateController {

    ILiveUpdateService service;

    @Autowired
    public LiveUpdateController(ILiveUpdateService service) {
        this.service = service;
    }

    @GetMapping(value = "/hosts")
    public Result getHosts() {
        Map<Integer, String> hosts = ServerHost.getHosts();
        return Result.success(hosts);
    }

    @GetMapping(value = "/hoststime")
    public Result getHostsTime() {
        Map<Integer, Integer> timeout = ServerHost.getTimeout();
        return Result.success(timeout);
    }


}
