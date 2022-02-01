package com.charles.common.utils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;


public class DownLoadUtils {

    public static String getFileName(String agent, String filename) {

        if (agent.contains("MSIE")) {
            // IE浏览器
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
            filename = filename.replace("+", " ");
        } else if (agent.contains("Firefox")) {
            // 火狐浏览器
            filename = "=?utf-8?B?" + Base64.getEncoder().encodeToString(filename.getBytes(StandardCharsets.UTF_8)) + "?=";
        } else {
            // 其它浏览器
            filename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        }
        return filename;
    }
}
