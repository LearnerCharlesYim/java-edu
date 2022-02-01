package com.charles.guli.edu.controller;

import cn.hutool.core.io.IoUtil;
import com.charles.guli.edu.utils.OssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

@Api(tags = "下载测试")
@Controller
public class DownloadController {

    @ApiOperation("下载单文件")
    @GetMapping("/download")
    public void download(HttpServletRequest request,
                         HttpServletResponse response,
                         @RequestParam String filename) throws IOException {
        OssUtil.downloadFromOss(request, response, filename);
    }

    @ApiOperation("测试下载多级文件夹")
    @GetMapping("/download/test")
    public void test(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
        FileInputStream in = new FileInputStream("E:\\home\\ruoyi\\logs\\sys-info.log");


        response.setContentType("text/plain");
        response.setHeader("Content-Disposition", "attachment;filename=sys-info.log");

        ServletOutputStream out = response.getOutputStream();
        IoUtil.copy(in,out);

        in.close();
    }
}
