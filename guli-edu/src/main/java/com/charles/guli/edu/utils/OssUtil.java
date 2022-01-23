package com.charles.guli.edu.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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
        newName = datePath + "/" + newName + oldName.substring(oldName.lastIndexOf("."));

        ossClient.putObject(bucketName, newName, inputStream);
        ossClient.shutdown();
        return "https://" + bucketName + "." + endpoint + "/" + newName;
    }
}
