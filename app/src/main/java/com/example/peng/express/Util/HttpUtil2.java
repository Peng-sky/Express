package com.example.peng.express.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil2 {


    public static String sendGet(String request_url, String params) {

        try {
            URL url = new URL(request_url + params);

            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();

            connection.setConnectTimeout(3000);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoInput(true);
            if (connection.getResponseCode() == 200) {
                return inputStream2String(connection.getInputStream());
            }
        }  catch (IOException e) {
            e.printStackTrace();
        }
        return "未接收到数据";
    }

    private static String inputStream2String(InputStream inputStream) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        int len = 0;
        byte[] bs = new byte[1024];
        try {
            while ((len = inputStream.read(bs)) != -1) {
                outputStream.write(bs, 0, len);
            }
            return new String(outputStream.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
