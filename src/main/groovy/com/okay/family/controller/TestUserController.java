package com.okay.family.controller;

import com.github.pagehelper.PageInfo;
import com.okay.family.common.bean.DelBean;
import com.okay.family.common.bean.testuser.EditUserBean;
import com.okay.family.common.bean.testuser.SearchUserBean;
import com.okay.family.common.bean.testuser.TestUserBean;
import com.okay.family.common.bean.testuser.TestUserCheckBean;
import com.okay.family.common.code.CommonCode;
import com.okay.family.common.code.TestUserCode;
import com.okay.family.fun.base.bean.Result;
import com.okay.family.fun.frame.SourceCode;
import com.okay.family.service.ITestUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/user")
public class TestUserController {

    public static Logger logger = LoggerFactory.getLogger(TestUserController.class);

    ITestUserService testUserService;

    @Autowired
    public TestUserController(ITestUserService testUserService) {
        this.testUserService = testUserService;
    }

    @GetMapping(value = "/get")
    public Result findUsers(@Valid SearchUserBean bean) {

        PageInfo<TestUserBean> users = testUserService.findUsers(bean);
        return Result.success(users);
    }

    @PostMapping(value = "/edit")
    public Result editUser(@RequestBody @Valid EditUserBean bean) {
        if (bean.getType().equalsIgnoreCase("add")) {
            int add = testUserService.addUser(bean);
            return add > 0 ? Result.success(SourceCode.getJson("id=" + add)) : Result.fail(TestUserCode.ADD_USER_FAIL);
        } else if (bean.getType().equalsIgnoreCase("update")) {
            int i = testUserService.updateUser(bean);
            return i == 0 ? Result.fail(TestUserCode.UPDATE_USER_FAIL) : Result.success();
        }
        return Result.fail(CommonCode.PARAMS_ERROR);
    }

    @PostMapping(value = "/del")
    public Result delUser(@RequestBody @Valid DelBean bean) {
        int i = testUserService.delUsr(bean);
        return i == 0 ? Result.fail(TestUserCode.DEL_USER_FAIL) : Result.success();
    }

    @PostMapping(value = "/test")
    public Result check(@RequestBody @Valid TestUserCheckBean bean) {
        testUserService.updateUserStatus(bean);
        return Result.build(0, "");
    }


}
