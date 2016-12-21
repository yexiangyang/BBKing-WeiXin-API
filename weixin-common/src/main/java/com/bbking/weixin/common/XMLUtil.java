package com.bbking.weixin.common;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * XML工具类
 *
 * @version 2016-12-21
 * @auther yexiangyang
 */
public class XMLUtil {
    private static Logger logger = Logger.getLogger(XMLUtil.class);

    /**
     * 类转xml字符串
     *
     * @param object 目标对象
     * @return xml字符串
     */
    public static String bean2Xml(Object object) {
        String xml = null;
        if (object != null) {
            Field[] fields = object.getClass().getDeclaredFields();
            if (fields != null && fields.length > 0) {
                xml = "<xml>";
                for (Field field : fields) {
                    try {
                        if (field != null) {
                            String name = field.getName();
                            String getMethodName = "get" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length());
                            Method getMethod = object.getClass().getDeclaredMethod(getMethodName, null);
                            Object value = getMethod.invoke(object, null);
                            if (value != null) {
                                if ("body".equalsIgnoreCase(name) || "detail".equalsIgnoreCase(name)) {
                                    xml += "<" + name + "><![CDATA[" + value + "]]></" + name + ">";
                                } else {
                                    xml += "<" + name + ">" + value + "</" + name + ">";
                                }
                            }
                        }
                    } catch (Exception e) {
                        logger.info("属性转xml元素出错_" + e);
                    }
                }
                xml += "</xml>";
            }
        }
        return xml;
    }

    /**
     * xml字符串转类
     *
     * @param clazz 对象类型
     * @param xml   目标数据
     * @return 对象类
     */
    public static <T> T xml2Bean(Class<T> clazz, String xml) {
        T t = null;
        if (StringUtils.isNotBlank(xml) && clazz != null) {
            Document document = null;
            Element rootElm = null;
            try {
                t = clazz.newInstance();
                document = DocumentHelper.parseText(xml);
                rootElm = document.getRootElement();
            } catch (Exception e) {
                logger.info("xml转document对象出错_" + e);
            }
            if (rootElm != null && t != null) {
                for (Iterator it = rootElm.elementIterator(); it.hasNext(); ) {
                    Element element = (Element) it.next();
                    String name = element.getName();
                    try {
                        Method method = clazz.getDeclaredMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length()), String.class);
                        method.invoke(t, element.getText());
                    } catch (Exception e) {
                        logger.info("利用反射属性赋值出错_" + e + "_切换参数String类型为Integer类型重试");
                        try {
                            Method method = clazz.getDeclaredMethod("set" + name.substring(0, 1).toUpperCase() + name.substring(1, name.length()), Integer.class);
                            method.invoke(t, Integer.valueOf(element.getText()));
                        } catch (Exception e1) {
                            logger.info("利用反射属性赋值出错_" + e1 + "_更多类型支持请联系移动端开发人员");
                        }
                    }
                }
            }
        }
        return t;
    }
}
