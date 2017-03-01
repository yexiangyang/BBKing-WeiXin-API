package com.bbking.weixin.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * <p>支付签名工具类</p>
 *
 * @author yexiangyang
 * @create 2017-01-06
 */
public class PaySignUtil {
    private static Logger logger = Logger.getLogger(PaySignUtil.class);

    /**
     * 支付签名
     *
     * @param object 签名对象
     * @param key    支付KEY
     */
    public static String paySign(Object object, String key) {
        logger.info("PaySignUtils.paySign_parameters_object_" + JSONObject.toJSONString(object) + "_key_" + key);
        String sign = "";
        if (object != null && StringUtils.isNotBlank(key)) {
            try {
                Field[] fields = object.getClass().getDeclaredFields();
                if (fields != null && fields.length > 0) {
                    SortedMap<String, Object> sortedMap = new TreeMap<String, Object>();
                    for (Field field : fields) {
                        try {
                            if (field != null) {
                                String name = field.getName();
                                String getMethodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
                                Method getMethod = object.getClass().getDeclaredMethod(getMethodName, null);
                                Object value = getMethod.invoke(object, null);
                                if (value != null && !"sign".equals(name)) {
                                    if ("pack".equals(name)) {
                                        sortedMap.put("package", value);
                                    } else {
                                        sortedMap.put(name, value);
                                    }
                                }
                            }
                        } catch (Exception e) {
                            logger.info("属性值获取出错_" + e);
                        }
                    }
                    if (sortedMap.size() > 0) {
                        StringBuffer stringA = new StringBuffer();
                        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
                            String entryKey = entry.getKey();
                            Object entryValue = entry.getValue();
                            if (entryValue != null) {
                                stringA.append(entryKey).append("=").append(entryValue).append("&");
                            }
                        }
                        stringA.append("key=").append(key);
                        logger.info("签名字符串为_" + stringA);
                        sign = MD5Util.MD5Encode(stringA.toString(), "UTF-8").toUpperCase();
                    }
                }
            } catch (Exception e) {
                logger.info("PaySignUtils.paySign_异常" + e);
            }
        }
        logger.info("PaySignUtils.paySign_return_" + sign);
        return sign;
    }
}
