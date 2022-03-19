package com.charles.guli.edu.controller;

import com.charles.common.domain.R;
import com.charles.guli.edu.domain.pojo.User;
import com.charles.guli.edu.domain.vo.UserVo;
import com.charles.guli.edu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ApiOperation("添加用户")
    public R<Void> addUser(@RequestBody @Validated UserVo user) {
        userService.addUser(user);
        return R.ok();
    }

    @GetMapping
    @ApiOperation("查询用户列表")
    public R<List<User>> list() {
        List<User> users = userService.findAll();
        return R.ok(users);
    }
}
