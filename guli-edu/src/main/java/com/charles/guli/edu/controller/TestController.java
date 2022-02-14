package com.charles.guli.edu.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.charles.common.domain.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@RestController
public class TestController {

    @GetMapping("/test")
    public R<Void> test(String a, String b, HttpServletRequest request) {
        request.getParameterMap();
        request.getParameterValues("a");
        return R.ok();
    }

    @GetMapping("/online")
    public R<List<String>> online(){
        List<String> list = StpUtil.searchSessionId("", -1, 0);
        return R.ok(list);
    }
}
