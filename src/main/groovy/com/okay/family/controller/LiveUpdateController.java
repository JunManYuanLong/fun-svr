package com.okay.family.controller;


import com.okay.family.common.basedata.FamilyConstant;
import com.okay.family.common.basedata.ServerHost;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.service.ILiveUpdateService;
import com.okay.family.service.ITestCaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    ITestCaseService caseService;

    public LiveUpdateController(ILiveUpdateService service, ITestCaseService caseService) {
        this.service = service;
        this.caseService = caseService;
    }

    @GetMapping(value = "/hosts")
    public Result getHosts() {
        Map<Integer, String> hosts = ServerHost.getHosts();
        return Result.success(hosts);
    }

    @GetMapping(value = "/rmhost}")
    public Result rmHosts() {
        ServerHost.getHosts().clear();
        return Result.success();
    }

    @GetMapping(value = "/hoststime")
    public Result getHostsTime() {
        Map<Integer, Integer> timeout = ServerHost.getTimeout();
        return Result.success(timeout);
    }

    @GetMapping(value = "/case/{caseId}")
    public Result updateCaseApiInfo(@PathVariable(value = "caseId", required = true) int caseId) {
        caseService.syncApi(caseId);
        return Result.success();
    }

    @GetMapping(value = "/output")
    public Result editOutput() {
        FamilyConstant.OUTPUT = !FamilyConstant.OUTPUT;
        return Result.success();
    }


}
