package com.bbking.weixin.api.model;

import java.io.Serializable;

/**
 * <p>交易保障接口返回实体类</p>
 *
 * @author yexiangyang
 * @create 2016-12-20
 */
public class ReportRO implements Serializable {
    /**
     * 返回状态码
     */
    private String return_code;
    /**
     * 返回信息
     */
    private String return_msg;
    /**
     * 业务结果
     */
    private String result_code;

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

    public String getResult_code() {
        return result_code;
    }

    public void setResult_code(String result_code) {
        this.result_code = result_code;
    }
}
