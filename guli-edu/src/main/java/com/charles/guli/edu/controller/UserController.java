package com.charles.guli.edu.controller;

import com.charles.common.domain.R;
import com.charles.guli.edu.domain.vo.UserVo;
import com.charles.guli.edu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    @ApiOperation("添加用户")
    public R addUser(@RequestBody UserVo user){
        userService.addUser(user);
        return R.ok();
    }
}
