package com.okay.family.controller;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.enums.*;
import com.okay.family.fun.base.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/common")
public class CommonDataController {


    @GetMapping(value = "/env")
    public Result getEnv() {
        List<Environment> environments = Arrays.asList(Environment.values());
        Map<Integer, Environment> collect = environments.stream().collect(Collectors.toMap(Environment::getCode, x -> x));
        return Result.success(collect);
    }

    @GetMapping(value = "/identity")
    public Result getIdentity() {
        JSONObject all = Identity.getAll();
        return Result.success(all);
    }

    @GetMapping(value = "/casestatus")
    public Result getCaseStatus() {
        List<CaseStatus> environments = Arrays.asList(CaseStatus.values());
        Map<Integer, CaseStatus> collect = environments.stream().collect(Collectors.toMap(CaseStatus::getCode, x -> x));
        return Result.success(collect);
    }

    @GetMapping(value = "/caselevel")
    public Result getCaseLevel() {
        List<CaseLevel> environments = Arrays.asList(CaseLevel.values());
        Map<Integer, CaseLevel> collect = environments.stream().collect(Collectors.toMap(CaseLevel::getCode, x -> x));
        return Result.success(collect);
    }

    @GetMapping(value = "/requestmethod")
    public Result getRequestMethod() {
        List<RequestMethod> environments = Arrays.asList(RequestMethod.values());
        Map<Integer, RequestMethod> collect = environments.stream().collect(Collectors.toMap(RequestMethod::getCode, x -> x));
        return Result.success(collect);
    }


}
