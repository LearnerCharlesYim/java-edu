package com.charles.common.utils;

import cn.hutool.json.JSONUtil;
import com.charles.common.execption.BizAssert;

import javax.servlet.http.HttpServletResponse;

public class WebUtil {
    public static void writeJson(HttpServletResponse response, Object obj) {
        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("utf-8");
        try {
            response.getWriter().write(JSONUtil.toJsonStr(obj));
            response.flushBuffer();
        } catch (Exception e) {
            BizAssert.fail(String.format("响应json异常: %s", e));
        }
    }
}
