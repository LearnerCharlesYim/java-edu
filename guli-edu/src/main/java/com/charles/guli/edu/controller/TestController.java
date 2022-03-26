package com.charles.guli.edu.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.Dict;
import com.charles.common.annotation.AccessLimit;
import com.charles.common.annotation.LoginUser;
import com.charles.common.annotation.NoResponseResult;
import com.charles.common.annotation.ResponseResult;
import com.charles.common.domain.R;
import com.charles.common.utils.enums.EnumUtil;
import com.charles.guli.edu.domain.pojo.Role;
import com.charles.guli.edu.domain.pojo.User;
import com.charles.guli.edu.domain.vo.Pet;
import com.charles.guli.edu.domain.vo.TestVo;
import com.charles.guli.edu.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ResponseResult
@RestController
@Api(tags = "测试接口")
public class TestController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/test")
    @AccessLimit
    @ApiOperation("测试1")
    public R<Void> test(@RequestParam Integer a, String b, HttpServletRequest request) {
        request.getParameterMap();
        request.getParameterValues("a");
        return R.ok();
    }

    @GetMapping("/online")
    @ApiOperation("测试saToken sessions")
    public R<List<SaSession>> online() {
        List<String> list = StpUtil.searchSessionId("", -1, 0);
        List<SaSession> sessions = list.stream()
                .map(StpUtil::getSessionBySessionId)
                .collect(Collectors.toList());
        return R.ok(sessions);
    }

    @ResponseResult
    @ApiOperation("测试ResponseResult")
    @GetMapping("test/role/{hah}")
    public List<Role> queryRole(@PathVariable Integer hah) {
        return roleService.queryRoles();
    }

    @ResponseResult
    @ApiOperation("测试ResponseResult Void")
    @GetMapping("test/void")
    public void testVoid() {
    }

    @ResponseResult
    @GetMapping("test/string")
    @ApiOperation("测试ResponseResult String")
    public String testString() {
        return "hello Springboot!";
    }

    @ResponseResult
    @GetMapping("test/error")
    @ApiOperation("测试ResponseResult Exception")
    public void testError() {
        int i = 10 / 0;
    }

    @ResponseResult
    @PostMapping("test/validate")
    @ApiOperation("测试ResponseResult validate ")
    public Dict testValidate(@Validated TestVo testVo) {
        System.out.println(testVo);
        return Dict.create()
                .set("name", testVo.getName())
                .set("gender", EnumUtil.getNameByValue(TestVo.Gender.class, testVo.getGender()));
    }

    @ResponseResult
    @PostMapping("/test/validate2")
    @ApiOperation("测试ResponseResult validate 2")
    public Dict testValidate2(@Validated @RequestBody TestVo testVo) {
        System.out.println(testVo);
        return Dict.create()
                .set("name", testVo.getName())
                .set("gender", EnumUtil.getNameByValue(TestVo.Gender.class, testVo.getGender()));
    }

    @ResponseResult
    @PostMapping("/test/multi")
    @ApiOperation("测试ResponseResult MultipartFile")
    public void testMultipart(@RequestParam MultipartFile file) {
        System.out.println(file);
    }

    @GetMapping(value = "/test/testpro", produces = "text/html")
    @ApiOperation("测试ResponseResult produces = 'text/html'")
    public String testPro() {
        return "<h1>hello spring!</h1>";
    }

    @ResponseResult
    @GetMapping("/test/xss")
    @ApiOperation("测试ResponseResult xss")
    public void testXss(HttpServletRequest request, TestVo testVo) {

//        System.out.println(testVo);
        Map<String, String[]> map = request.getParameterMap();
        map.forEach((k, v) -> System.out.printf("%s==>%s%n", k, Arrays.toString(v)));
    }

    @NoResponseResult
    @AccessLimit(seconds = 60, maxCount = 10)
    @ApiOperation("测试NoResponseResult")
    @GetMapping("/test/no")
    public String testNo() {
        return "Hello";
    }

    @ResponseResult
    @GetMapping("/test/testConvert")
    public Pet testConvert(Date date, Pet pet) {
        System.out.println(date.toString());
        System.out.println(new Date());
        System.out.println(pet);
        return pet;
    }

    @ResponseResult
    @GetMapping("/test/testArgs")
    public User testArgs(@LoginUser User user) {
        return user;
    }
}
