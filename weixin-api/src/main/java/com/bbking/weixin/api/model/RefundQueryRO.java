package com.bbking.weixin.api.model;

import java.io.Serializable;

/**
 * <p>查询退款接口返回实体类</p>
 *
 * @author yexiangyang
 * @create 2016-11-30
 */
public class RefundQueryRO implements Serializable {
    /**
     * 返回状态码
     */
    private String return_code;
    /**
     * 返回信
     */
    private String return_msg;
    /**
     * 业务结果
     */
    private String result_code;
    /**
     * 错误代码
     */
    private String err_code;
    /**
     * 错误代码描述
     */
    private String err_code_des;
    /**
     * 公众账号ID
     */
    private String appid;
    /**
     * 商户号
     */
    private String mch_id;
    /**
     * 子商户公众账号ID
     */
    private String sub_appid;
    /**
     * 子商户号
     */
    private String sub_mch_id;
    /**
     * 设备号
     */
    private String device_info;
    /**
     * 随机字符串
     */
    private String nonce_str;
    /**
     * 签名
     */
    private String sign;
    /**
     * 微信订单号
     */
    private String transaction_id;
    /**
     * 商户订单号
     */
    private String out_trade_no;
    /**
     * 订单总金额
     */
    private Integer total_fee;
    /**
     * 订单金额货币种类
     */
    private String fee_type;
    /**
     * 现金支付金额
     */
    private Integer cash_fee;
    /**
     * 货币种类
     */
    private String cash_fee_type;
    /**
     * 退款金额
     */
    private Integer refund_fee;
    /**
     * 代金券或立减优惠退款金额
     */
    private Integer coupon_refund_fee;
    /**
     * 退款笔数
     */
    private Integer refund_count;
    /**
     * 商户退款单号
     */
    private String out_refund_no_0;
    /**
     * 微信退款单号
     */
    private String refund_id_0;
    /**
     * 退款渠道
     */
    private String refund_channel_0;
    /**
     * 退款资金来源
     */
    private String refund_account;
    /**
     * 退款金额
     */
    private Integer refund_fee_0;
    /**
     * 货币种类
     */
    private String fee_type_0;
    /**
     * 退款状态
     */
    private String refund_status_0;

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

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
    }

    public String getErr_code_des() {
        return err_code_des;
    }

    public void setErr_code_des(String err_code_des) {
        this.err_code_des = err_code_des;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSub_appid() {
        return sub_appid;
    }

    public void setSub_appid(String sub_appid) {
        this.sub_appid = sub_appid;
    }

    public String getSub_mch_id() {
        return sub_mch_id;
    }

    public void setSub_mch_id(String sub_mch_id) {
        this.sub_mch_id = sub_mch_id;
    }

    public String getDevice_info() {
        return device_info;
    }

    public void setDevice_info(String device_info) {
        this.device_info = device_info;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public Integer getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(Integer total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public Integer getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(Integer cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public Integer getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(Integer refund_fee) {
        this.refund_fee = refund_fee;
    }

    public Integer getCoupon_refund_fee() {
        return coupon_refund_fee;
    }

    public void setCoupon_refund_fee(Integer coupon_refund_fee) {
        this.coupon_refund_fee = coupon_refund_fee;
    }

    public Integer getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(Integer refund_count) {
        this.refund_count = refund_count;
    }

    public String getOut_refund_no_0() {
        return out_refund_no_0;
    }

    public void setOut_refund_no_0(String out_refund_no_0) {
        this.out_refund_no_0 = out_refund_no_0;
    }

    public String getRefund_id_0() {
        return refund_id_0;
    }

    public void setRefund_id_0(String refund_id_0) {
        this.refund_id_0 = refund_id_0;
    }

    public String getRefund_channel_0() {
        return refund_channel_0;
    }

    public void setRefund_channel_0(String refund_channel_0) {
        this.refund_channel_0 = refund_channel_0;
    }

    public String getRefund_account() {
        return refund_account;
    }

    public void setRefund_account(String refund_account) {
        this.refund_account = refund_account;
    }

    public Integer getRefund_fee_0() {
        return refund_fee_0;
    }

    public void setRefund_fee_0(Integer refund_fee_0) {
        this.refund_fee_0 = refund_fee_0;
    }

    public String getFee_type_0() {
        return fee_type_0;
    }

    public void setFee_type_0(String fee_type_0) {
        this.fee_type_0 = fee_type_0;
    }

    public String getRefund_status_0() {
        return refund_status_0;
    }

    public void setRefund_status_0(String refund_status_0) {
        this.refund_status_0 = refund_status_0;
    }
}
