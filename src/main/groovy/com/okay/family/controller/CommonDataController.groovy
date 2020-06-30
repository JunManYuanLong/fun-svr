package com.okay.family.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.okay.family.common.basedata.OkayConstant;
import com.okay.family.common.enums.CollectionStatus;
import com.okay.family.common.enums.Identity;
import com.okay.family.common.enums.RunResult;
import com.okay.family.common.enums.UserState;
import com.okay.family.fun.base.bean.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.okay.family.fun.frame.SourceCode.getJson;

@RestController
@RequestMapping(value = "/common")
public class CommonDataController {

/**
 * 用户身份
 * @return
 */
    @GetMapping(value = "/roles")
    public Result getIdentity() {
        JSONArray all = Identity.getAll();
        Result.success(all);
    }


/**
 * 填写规则
 * @return
 */
    @GetMapping(value = "/rule")
    public Result getRule() {
        JSONObject json = getJson("text=" + OkayConstant.RULE);
        Result.success(json);
    }

/**
 * 用户状态
 * @return
 */
    @GetMapping(value = "/userstate")
    public Result getUserState() {
        JSONArray all = UserState.getAll()
        Result.success(all)
    }


/**
 * 用例集状态
 * @return
 */
    @GetMapping(value = "/collectstate")
    public Result getCollectState() {
        JSONArray all = CollectionStatus.getAll()
        Result.success(all)
    }


/**
 * 用例运行状态
 * @return
 */
    @GetMapping(value = "/runresult")
    public Result getRunResult() {
        JSONArray all = RunResult.getAll()
        Result.success(all)
    }


}
