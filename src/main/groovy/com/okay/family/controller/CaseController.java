package com.okay.family.controller;

import com.okay.family.common.bean.testcase.TestCaseBean;
import com.okay.family.common.bean.testcase.request.CaseAttributeBean;
import com.okay.family.common.code.CommonCode;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.service.ITestCaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/case")
public class CaseController {

    private static Logger logger = LoggerFactory.getLogger(CaseController.class);

    ITestCaseService service;

    @Autowired
    public CaseController(ITestCaseService service) {
        this.service = service;
    }

    @PostMapping(value = "/edit")
    public Result edit(@RequestBody @Valid CaseAttributeBean bean) {

        if (bean.getType().equalsIgnoreCase("add")) {
            int i = service.addCase(bean);
            return Result.success(SourceCode.getJson("id=" + i));
        }
        return Result.fail(CommonCode.PARAMS_ERROR);

    }

    @PostMapping(value = "/del")
    public Result delCase(@RequestBody @Valid TestCaseBean bean) {

        return Result.success();
    }

    @GetMapping(value = "/getcases")
    public Result findCaseByid(@PathVariable(value = "apiid", required = true) int apiid,
                               @PathVariable(value = "environment", required = true) int environment) {
        return Result.success();

    }

    @GetMapping(value = "/relation")
    public Result getRelation(@PathVariable(value = "apiid", required = true) int apiid,
                              @PathVariable(value = "environment", required = true) int environment) {
        return Result.success();

    }

    @GetMapping(value = "/records")
    public Result findMyCaseByid(@PathVariable(value = "uid", required = true) int uid) {
        return Result.success();

    }

    @GetMapping(value = "/attribute")
    public Result getAttribute(@PathVariable(value = "uid", required = true) int uid) {
        return Result.success();

    }

    @GetMapping(value = "/data")
    public Result getData(@PathVariable(value = "uid", required = true) int uid) {
        return Result.success();

    }

    @GetMapping(value = "/run")
    public Result runCase(@PathVariable(value = "id", required = true) int id) {
        return Result.success();

    }


}
