package com.charles.sys.share.controller;

import com.charles.common.domain.R;
import com.charles.sys.share.domain.dto.LoginParam;
import com.charles.sys.share.domain.dto.LoginResult;
import com.charles.sys.share.domain.dto.UserParam;
import com.charles.sys.share.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class SysUserController {

    private final SysUserService sysUserService;

    @PostMapping("/add")
    public R<Void> add(@RequestBody UserParam userParam){
        sysUserService.add(userParam);
        return R.ok();
    }
}
