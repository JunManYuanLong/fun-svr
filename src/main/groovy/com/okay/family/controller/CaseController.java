package com.okay.family.controller;

import com.okay.family.constants.bean.TestCaseBean;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.service.ITestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/case")
public class CaseController {

    @Autowired
    ITestCaseService service;

    @RequestMapping(value = "/save")
    public Result saveCase(@RequestBody @Valid TestCaseBean bean) {
        int i = service.saveCase(bean);
        return Result.success(i == 1 ? true : false);
    }


}
