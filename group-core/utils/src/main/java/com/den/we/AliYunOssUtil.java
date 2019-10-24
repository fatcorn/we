package com.den.we;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * ������󴢴湤����
 * @author fatKarin
 * @date 2019/10/16 14:17
 */
public class AliYunOssUtil {

    public static String updatePicture(InputStream inputStream, String suffix) {
        // Endpoint�Ժ���Ϊ��������Region�밴ʵ�������д��
        String endpoint = "oss-cn-chengdu.aliyuncs.com";
// ���������˺�AccessKeyӵ������API�ķ���Ȩ�ޣ����պܸߡ�ǿ�ҽ�����������ʹ��RAM�˺Ž���API���ʻ��ճ���ά�����¼ https://ram.console.aliyun.com ����RAM�˺š�
        String accessKeyId = "LTAI4FisGyijRwBTYCpmdZAr";
        String accessKeySecret = "Pr4hmohCYNRvcrKj1Vy3YfvYdHZWYn";

        String bucketName = "same-test";

        try {
            // ����OSSClientʵ����
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // �ϴ���������
           // inputStream = new URL("https://www.aliyun.com/").openStream();
            String objectName = DateUtil.dateToYYYYMMDDHHMMSS(new Date()) + "." + suffix;
            ossClient.putObject(bucketName, objectName, inputStream);

            //URL url = String.format("%s%s%s",endpoint, bucketName)
            // �ر�OSSClient��
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
