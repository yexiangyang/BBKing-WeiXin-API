package com.bbking.weixin.common;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * HTTP工具类
 *
 * @version 2016-07-07
 * @auther yexiangyang
 */
public class HttpUtil {
    private static Logger logger = Logger.getLogger(HttpUtil.class);

    /**
     * POST请求
     *
     * @param url  请求url
     * @param json 请求数据
     * @return 微信返回数据
     */
    public static String post(String url, String json) {
        logger.info("POST请求url:" + url + ",发送数据:" + json);
        String result = null;
        if (StringUtils.isNotBlank(url) && StringUtils.isNotBlank(json)) {
            try {
                URL url1 = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(json.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();
                int length = 0;
                byte[] data = new byte[1024];
                while ((length = inputStream.read(data)) > -1) {
                    stringBuffer.append(new String(data, 0, length));
                }
                inputStream.close();
                result = stringBuffer.toString();
            } catch (Exception e) {
                logger.info("HttpUtil.post出现异常:" + e);
            }
        } else {
            logger.info("HttpUtil.post入参存在空");
        }
        logger.info("POST请求返回结果:" + result);
        return result;
    }

    /**
     * GET请求
     *
     * @param url 请求地址
     * @return 微信返回的数据
     */
    public static String get(String url) {
        logger.info("GET请求url:" + url);
        String result = null;
        if (StringUtils.isNotBlank(url)) {
            try {
                URL url1 = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(30000);
                httpURLConnection.setDoOutput(false);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
                InputStream inputStream = httpURLConnection.getInputStream();
                StringBuffer stringBuffer = new StringBuffer();
                byte[] data = new byte[1024];
                int length = 0;
                while ((length = inputStream.read(data)) > -1) {
                    stringBuffer.append(new String(data, 0, length));
                }
                inputStream.close();
                result = stringBuffer.toString();
            } catch (Exception e) {
                logger.info("HttpUtil.get出现异常:" + e);
            }
        } else {
            logger.info("HttpUtil.get入参存在空");
        }
        logger.info("GET请求返回结果:" + result);
        return result;
    }
}
