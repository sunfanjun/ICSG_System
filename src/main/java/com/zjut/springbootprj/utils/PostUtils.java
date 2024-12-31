package com.zjut.springbootprj.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;


import java.io.*;

public class PostUtils {
    private static byte[] imgtoio(String imgaddress) {
        //图片转化为二进制
        byte[] imageBytes = null;
        try (FileInputStream fileInputStream = new FileInputStream(imgaddress)) {
            imageBytes = new byte[fileInputStream.available()];
            fileInputStream.read(imageBytes);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
        return imageBytes;
    }
    public static String uploadStream(String targetURL, String imgaddress) {
        byte[] data = imgtoio(imgaddress);
        PostMethod filePost = null;
        try {
            filePost = new PostMethod(targetURL);
            // 增加http 请求输入流
            filePost.setRequestEntity(new ByteArrayRequestEntity(data, "text/plain;charset=utf-8"));
            HttpClient client = new HttpClient();
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK) {
                return filePost.getResponseBodyAsString();
            } else {
                System.out.println("请求出错，状态：" + status + "," + filePost.getStatusText());
                System.out.println("请求出错，响应：" + filePost.getResponseBodyAsString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (filePost != null) {
                filePost.releaseConnection();
            }
        }
        return "";
    }
}







