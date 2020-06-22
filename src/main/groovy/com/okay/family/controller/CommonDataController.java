package com.okay.family.controller;

import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.enums.*;
import com.okay.family.fun.base.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.okay.family.fun.frame.SourceCode.getJson;

@RestController
@RequestMapping(value = "/common")
public class CommonDataController {



    @GetMapping(value = "/roles")
    public Result getIdentity() {
        JSONObject all = Identity.getAll();
        return Result.success(all);
    }


    @GetMapping(value = "/rule")
    public Result getRule() {
        JSONObject json = getJson("text=" + OkayConstant.RULE);
        return Result.success(json);
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
        List<RequestType> environments = Arrays.asList(RequestType.values());
        Map<Integer, RequestType> collect = environments.stream().collect(Collectors.toMap(RequestType::getCode, x -> x));
        return Result.success(collect);
    }


}
