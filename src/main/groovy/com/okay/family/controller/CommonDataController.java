package com.okay.family.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.enums.Identity;
import com.okay.family.common.enums.UserState;
import com.okay.family.fun.base.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.okay.family.fun.frame.SourceCode.getJson;

@RestController
@RequestMapping(value = "/common")
public class CommonDataController {



    @GetMapping(value = "/roles")
    public Result getIdentity() {
        JSONArray all = Identity.getAll();
        return Result.success(all);
    }


    @GetMapping(value = "/rule")
    public Result getRule() {
        JSONObject json = getJson("text=" + OkayConstant.RULE);
        return Result.success(json);
    }

    @GetMapping(value = "/userstate")
    public Result getUserState() {
        JSONArray all = UserState.getAll();
        return Result.success(all);
    }

}
