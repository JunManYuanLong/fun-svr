package com.okay.family.controller;

import com.okay.family.common.bean.testuser.DelUserBean;
import com.okay.family.common.bean.testuser.TestUserBean;
import com.okay.family.common.code.TestUserCode;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.fun.base.interfaces.ReturnCode;
import com.okay.family.service.ITestUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class TestUserController {

    public static Logger logger = LoggerFactory.getLogger(TestUserController.class);

    @Autowired
    ITestUserService testUserService;

    @GetMapping(value = "/find/{owner}")
    public Result findUsers(@PathVariable(value = "owner", required = true) int owner) {

        Map<Integer, List<TestUserBean>> users = testUserService.findUsers(owner);
        return Result.success(users);
    }

    @PostMapping(value = "/add")
    public Result findUsers(@RequestBody @Valid TestUserBean bean) {
        int add = testUserService.add(bean);
        return add == 0 ? Result.fail(TestUserCode.ADD_USER_FAIL) : Result.success();
    }

    @GetMapping(value = "/check/{id}")
    public Result check(@PathVariable(value = "id", required = true) int id) {
        TestUserBean user = testUserService.findUser(id);
        if (user == null)
            return Result.fail(TestUserCode.NO_USER);
        ReturnCode returnCode = testUserService.checkUser(user);
        return Result.build(returnCode);
    }

    @PostMapping(value = "/del")
    public Result del(@RequestBody @Valid DelUserBean bean) {
        ReturnCode returnCode = testUserService.delUsr(bean);
        return Result.build(returnCode);
    }


}
