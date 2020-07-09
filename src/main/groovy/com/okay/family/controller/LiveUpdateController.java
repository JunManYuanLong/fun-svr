package com.okay.family.controller;


import com.okay.family.fun.base.bean.Result;
import com.okay.family.service.ILiveUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/live")
public class LiveUpdateController {

    ILiveUpdateService service;

    @Autowired
    public LiveUpdateController(ILiveUpdateService service) {
        this.service = service;
    }

    @GetMapping(value = "/hosts")
    public Result updateHosts() {
        service.initServiceHost();
        return Result.success();
    }


}
