package com.charles.guli.edu.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Dict;
import com.charles.common.domain.R;
import com.charles.guli.edu.domain.vo.LoginParam;
import com.charles.guli.edu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "后台登录管理模块")
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class LoginController {

    private final UserService userService;

    @ApiOperation("登录接口")
    @PostMapping("/login")
    public R<Dict> login(@RequestBody LoginParam loginParam) {
        String token = userService.login(loginParam);
        return R.ok(Dict.create().set("token", token));
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/info")
    public R<Dict> getUserInfo() {
        return R.ok(Dict.create()
                .set("roles", "['admin']")
                .set("name", "admin")
                .set("avatar", "https://guli-edu-test-bucket.oss-cn-beijing.aliyuncs.com/2022/01/18/015250f63bae48e6b44fc351790442be.jpg"));
    }

    @ApiOperation("注销接口")
    @PostMapping("/logout")
    public R<Void> logout() {
        StpUtil.logout();
        return R.ok();
    }
}
