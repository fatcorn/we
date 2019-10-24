package com.den.we;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * 阿里对象储存工具类
 * @author fatKarin
 * @date 2019/10/16 14:17
 */
public class AliYunOssUtil {

    public static String updatePicture(InputStream inputStream, String suffix) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-chengdu.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FisGyijRwBTYCpmdZAr";
        String accessKeySecret = "Pr4hmohCYNRvcrKj1Vy3YfvYdHZWYn";

        String bucketName = "same-test";

        try {
            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传网络流。
           // inputStream = new URL("https://www.aliyun.com/").openStream();
            String objectName = DateUtil.dateToYYYYMMDDHHMMSS(new Date()) + "." + suffix;
            ossClient.putObject(bucketName, objectName, inputStream);

            //URL url = String.format("%s%s%s",endpoint, bucketName)
            // 关闭OSSClient。
            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            InputStream in = new FileInputStream("C:/Users/maccura/Desktop/timg.jpg");
            AliYunOssUtil.updatePicture(in, "timg.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
