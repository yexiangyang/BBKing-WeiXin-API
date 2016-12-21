package com.bbking.weixin.api.model;

import java.io.Serializable;

/**
 * <p>下载对账单接口返回实体类</p>
 *
 * @author yexiangyang
 * @create 2016-11-30
 */
public class DownloadBillRO implements Serializable {
    /**
     * 返回状态码
     */
    private String return_code;
    /**
     * 返回信息
     */
    private String return_msg;
    /**
     * 对账单文本
     */
    private String text;

    public String getReturn_code() {
        return return_code;
    }

    public void setReturn_code(String return_code) {
        this.return_code = return_code;
    }

    public String getReturn_msg() {
        return return_msg;
    }

    public void setReturn_msg(String return_msg) {
        this.return_msg = return_msg;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
