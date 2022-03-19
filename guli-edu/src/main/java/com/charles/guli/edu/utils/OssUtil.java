package com.charles.guli.edu.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.OSSObject;
import com.charles.common.utils.DownLoadUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

public class OssUtil {

    public static final String endpoint = ConstantPropertiesUtil.ENDPOINT;
    public static final String accessKeyId = ConstantPropertiesUtil.KEY_ID;
    public static final String accessKeySecret = ConstantPropertiesUtil.KEY_SECRET;
    public static final String bucketName = ConstantPropertiesUtil.BUCKET_NAME;


    public static String uploadToOss(MultipartFile file) throws IOException {

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        InputStream inputStream = file.getInputStream();

        String datePath = DateUtil.format(new Date(), "yyyy/MM/dd");
        String oldName = file.getOriginalFilename();
        String newName = IdUtil.simpleUUID();
        assert oldName != null;
        newName = datePath + "/" + newName + oldName.substring(oldName.lastIndexOf("."));

        ossClient.putObject(bucketName, newName, inputStream);
        ossClient.shutdown();
        return StrUtil.format("https://{}.{}/{}", bucketName, endpoint, newName);
    }

    public static void downloadFromOss(HttpServletRequest request, HttpServletResponse response, String filename) throws IOException {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, filename);

        response.setContentType(ossObject.getObjectMetadata().getContentType());

        String newFilename = DownLoadUtil.getFileName(request.getHeader("user-agent"), filename);
        response.setHeader("Content-Disposition", "attachment;filename=" + newFilename);

        // 读取文件内容。
        InputStream in = new BufferedInputStream(ossObject.getObjectContent());
        OutputStream out = response.getOutputStream();

        IoUtil.copy(in, out, IoUtil.DEFAULT_BUFFER_SIZE);

        // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
        in.close();
        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
