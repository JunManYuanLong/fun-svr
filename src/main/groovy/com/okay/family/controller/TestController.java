package com.okay.family.controller;


import com.alibaba.fastjson.JSONObject;
import com.okay.family.Bean.DemoBean;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.fun.config.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    public static Logger logger = LoggerFactory.getLogger(TestController.class);

    @GetMapping(value = "/t")
    public Result test() {
        logger.info("3242432");
        return Result.success(Constant.DEFAULT_STRING);
    }

    @PostMapping(value = "/d")
    public Result demo(@RequestBody DemoBean bean) {
        JSONObject jsonObject = bean.toJson();
        return Result.success(jsonObject);
    }

    @PostMapping(value = "/dd")
    public Result demo2(DemoBean bean) {
        JSONObject jsonObject = bean.toJson();
        logger.info(bean.getName() + "55555555");
        logger.info(bean.getAge() + "333333");
        return Result.success(jsonObject);
    }


}
