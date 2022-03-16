package com.charles.guli.edu.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Dict;
import com.charles.common.annotation.ResponseResult;
import com.charles.common.domain.R;
import com.charles.common.utils.enums.EnumUtils;
import com.charles.guli.edu.domain.pojo.Role;
import com.charles.guli.edu.domain.vo.TestVo;
import com.charles.guli.edu.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

//@ResponseResult
@RestController
public class TestController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/test")
    public R<Void> test(String a, String b, HttpServletRequest request) {
        request.getParameterMap();
        request.getParameterValues("a");
        return R.ok();
    }

    @GetMapping("/online")
    public R<List<SaSession>> online() {
        List<String> list = StpUtil.searchSessionId("", -1, 0);
        List<SaSession> sessions = list.stream()
                .map(StpUtil::getSessionBySessionId)
                .collect(Collectors.toList());
        return R.ok(sessions);
    }

    @ResponseResult
    @GetMapping("test/role")
    public List<Role> queryRole() {
        return roleService.queryRoles();
    }

    @ResponseResult
    @GetMapping("test/void")
    public void testVoid() {
    }

    @ResponseResult
    @GetMapping("test/string")
    public String testString() {
        return "hello Springboot!";
    }

    @ResponseResult
    @GetMapping("test/error")
    public void testError() {
        int i = 10 / 0;
    }

    @ResponseResult
    @GetMapping("test/validate")
    public Dict testValidate(@Validated TestVo testVo) {
        System.out.println(testVo);
        return Dict.create()
                .set("name", testVo.getName())
                .set("gender", EnumUtils.getNameByValue(TestVo.Gender.class, testVo.getGender()));
    }

    @ResponseResult
    @PostMapping("/test/validate2")
    public Dict testValidate2(@Validated @RequestBody TestVo testVo) {
        System.out.println(testVo);
        return Dict.create()
                .set("name", testVo.getName())
                .set("gender", EnumUtils.getNameByValue(TestVo.Gender.class, testVo.getGender()));
    }
}
