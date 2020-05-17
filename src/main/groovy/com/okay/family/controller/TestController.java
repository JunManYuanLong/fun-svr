package com.okay.family.controller;


import com.alibaba.fastjson.JSONObject;
import com.okay.family.fun.base.bean.AbstractBean;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.fun.config.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

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
    public Result demo(@RequestBody Demo bean) {
        JSONObject jsonObject = bean.toJson();
        return Result.success(jsonObject);
    }

    @PostMapping(value = "/dd")
    public Result demo2(Demo bean) {
        JSONObject jsonObject = bean.toJson();
        logger.info(bean.name + "55555555");
        logger.info(bean.age + "333333");
        return Result.success(jsonObject);
    }

    static class Demo extends AbstractBean implements Serializable {

        private static final long serialVersionUID = -5504602047112523781L;

        public int age;

        public String name;

        public Demo(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public Demo() {

        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }


    }


}
