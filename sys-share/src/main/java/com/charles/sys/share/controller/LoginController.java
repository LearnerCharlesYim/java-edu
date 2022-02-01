package com.charles.sys.share.controller;

import com.charles.common.domain.R;
import com.charles.sys.share.domain.dto.LoginParam;
import com.charles.sys.share.domain.dto.LoginResult;
import com.charles.sys.share.service.SysUserService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final SysUserService sysUserService;

    @PostMapping("/login")
    public R<LoginResult> login(@RequestBody LoginParam loginParam, HttpServletRequest request) {
        LoginResult result = sysUserService.login(loginParam, request);
        return R.ok(result);
    }
}
