package com.charles.sys.share.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.core.util.IdUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Api(tags = "验证码")
@Controller
@RequiredArgsConstructor
@Slf4j
public class CaptchaController {

    private final RedisTemplate<String,String> redisTemplate;

    @ApiOperation(value = "获取验证码",produces = "image/jpeg")
    @GetMapping("/captcha")
    public void getCaptcha(HttpServletResponse response, @RequestParam String uuid) throws IOException {
        //定义图形验证码的长、宽、验证码字符数、干扰线宽度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(100, 40, 4, 3);
        //图形验证码写出，可以写出到文件，也可以写出到流
        String code = captcha.getCode();

        redisTemplate.boundValueOps("captcha:"+uuid).set(code,10, TimeUnit.MINUTES);
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "No-cache");

        ServletOutputStream outputStream = response.getOutputStream();
        captcha.write(outputStream);
        outputStream.close();
        log.info("生成的验证码:{}", code);
    }
}
