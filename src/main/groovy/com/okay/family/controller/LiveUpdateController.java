package com.okay.family.controller;


import com.okay.family.service.ILiveUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
