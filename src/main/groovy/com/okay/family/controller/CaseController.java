package com.okay.family.controller;

import com.okay.family.common.bean.TestCaseBean;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.service.ITestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/case")
public class CaseController {

    @Autowired
    ITestCaseService service;

    @PostMapping(value = "/save")
    public Result saveCase(@RequestBody @Valid TestCaseBean bean) {
        int i = service.saveCase(bean);
        return Result.success(i == 1 ? true : false);
    }

    @GetMapping(value = "/findcase")
    public Result saveCase() {
        List<TestCaseBean> my = service.findMy(2, 2);
        return Result.success(my);
    }


}
