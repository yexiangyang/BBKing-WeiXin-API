package com.bbking.weixin.api.model;

import java.io.Serializable;

/**
 * <p>获取验签秘钥接口返回实体类</p>
 *
 * @author yexiangyang
 * @create 2017-03-01
 */
public class GetSignKeyRO implements Serializable {
    /**
     * 返回状态码
     */
    private String return_code;
    /**
     * 返回信息
     */
    private String return_msg;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 沙箱密钥
     */
    private String sandbox_signkey;

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

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSandbox_signkey() {
        return sandbox_signkey;
    }

    public void setSandbox_signkey(String sandbox_signkey) {
        this.sandbox_signkey = sandbox_signkey;
    }
}
