package com.okay.family.controller;

import com.okay.family.constants.bean.TestCaseBean;
import com.okay.family.fun.base.bean.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/case")
public class CaseController {


    @RequestMapping(value = "/save")
    public Result saveCase(@RequestBody TestCaseBean bean) {
        return Result.success(true);
    }


}
