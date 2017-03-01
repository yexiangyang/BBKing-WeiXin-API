package com.bbking.weixin.api.pay;

import com.alibaba.fastjson.JSONObject;
import com.bbking.weixin.api.model.*;
import com.bbking.weixin.common.PaySignUtil;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

public class PayAPITest {
    private PayAPI payAPI = null;

    @Before
    public void setUp() throws Exception {
        payAPI = new PayAPI();
    }

    @Test
    public void testUnifiedOrder() throws Exception {
        UnifiedOrderPO unifiedOrderPO = new UnifiedOrderPO();
        unifiedOrderPO.setAppid("wxda8f17411ad516ef");
        unifiedOrderPO.setMch_id("1329257601");
        unifiedOrderPO.setSub_appid("wxf88c7482c9649af2");
        unifiedOrderPO.setSub_mch_id("1398480802");
        unifiedOrderPO.setDevice_info("WEB");
        unifiedOrderPO.setNonce_str("1234567890");
        unifiedOrderPO.setBody("在BBKing消费1元");
        unifiedOrderPO.setOut_trade_no("bbking001");
        unifiedOrderPO.setTotal_fee(100);
        unifiedOrderPO.setSpbill_create_ip("192.168.232.15");
        unifiedOrderPO.setNotify_url("www.bbking.com");
        unifiedOrderPO.setTrade_type("JSAPI");
        unifiedOrderPO.setSub_openid("oCd87w5PiQemnsFJdecllzaA6BUQ");
        unifiedOrderPO.setSign("5C3DD782037E1ED197FD21828A174BF2");
        UnifiedOrderRO unifiedOrderRO = this.payAPI.unifiedOrder(unifiedOrderPO);
        System.err.println(JSONObject.toJSONString(unifiedOrderRO));
    }

    @Test
    public void testOrderQuery() throws Exception {
        OrderQueryPO orderQueryPO = new OrderQueryPO();
        orderQueryPO.setAppid("wxda8f17411ad516ef");
        orderQueryPO.setMch_id("1329257601");
        orderQueryPO.setSub_appid("wxf88c7482c9649af2");
        orderQueryPO.setSub_mch_id("1398480802");
        orderQueryPO.setOut_trade_no("20161103135722264667");
        orderQueryPO.setNonce_str("1234567890");
        orderQueryPO.setSign("16EA94C6B6865C04582A0674D016F4F3");
        OrderQueryRO orderQueryRO = this.payAPI.orderQuery(orderQueryPO);
        System.err.println(JSONObject.toJSONString(orderQueryRO));
    }

    @Test
    public void testCloseOrder() throws Exception {
        CloseOrderPO closeOrderPO = new CloseOrderPO();
        closeOrderPO.setAppid("wxda8f17411ad516ef");
        closeOrderPO.setMch_id("1329257601");
        closeOrderPO.setSub_appid("wxf88c7482c9649af2");
        closeOrderPO.setSub_mch_id("1398480802");
        closeOrderPO.setOut_trade_no("bbking001");
        closeOrderPO.setNonce_str("1234567890");
        closeOrderPO.setSign("E50039F7DAAEF329C2DAD2B88202FCBC");
        CloseOrderRO closeOrderRO = this.payAPI.closeOrder(closeOrderPO);
        System.err.println(JSONObject.toJSONString(closeOrderRO));
    }

    @Test
    public void testRefund() throws Exception {
        RefundPO refundPO = new RefundPO();
        refundPO.setAppid("wxda8f17411ad516ef");
        refundPO.setMch_id("1329257601");
        refundPO.setSub_appid("wxf88c7482c9649af2");
        refundPO.setSub_mch_id("1398480802");
        refundPO.setOut_trade_no("20161103103015350825");
        refundPO.setNonce_str("1234567890");
        refundPO.setOut_refund_no("1234567890");
        refundPO.setTotal_fee(1);
        refundPO.setRefund_fee(1);
        refundPO.setOp_user_id("1329257601");
        FileInputStream fileInputStream = new FileInputStream(new File("C:\\Users\\yexiangyang\\Desktop\\apiclient_cert.p12"));
        refundPO.setInstream(fileInputStream);
        refundPO.setRefund_account("REFUND_SOURCE_RECHARGE_FUNDS");
        refundPO.setSign("A343780361C7D0E577F90EFE7FA101CA");
        RefundRO refundRO = this.payAPI.refund(refundPO);
        System.err.println(JSONObject.toJSONString(refundRO));
    }

    @Test
    public void testRefundQuery() throws Exception {
        RefundQueryPO refundQueryPO = new RefundQueryPO();
        refundQueryPO.setAppid("wxda8f17411ad516ef");
        refundQueryPO.setMch_id("1329257601");
        refundQueryPO.setSub_appid("wxf88c7482c9649af2");
        refundQueryPO.setSub_mch_id("1398480802");
        refundQueryPO.setOut_trade_no("20161102170357721581");
        refundQueryPO.setNonce_str("1234567890");
        refundQueryPO.setSign("9E51D9451C10E88D7A14EB2A02B0E8F7");
        RefundQueryRO refundQueryRO = this.payAPI.refundQuery(refundQueryPO);
        System.err.println(JSONObject.toJSONString(refundQueryRO));
    }

    @Test
    public void testDownloadBill() throws Exception {
        DownloadBillPO downloadBillPO = new DownloadBillPO();
        downloadBillPO.setAppid("wxda8f17411ad516ef");
        downloadBillPO.setMch_id("1329257601");
        downloadBillPO.setSub_appid("wxf88c7482c9649af2");
        downloadBillPO.setSub_mch_id("1398480802");
        downloadBillPO.setNonce_str("1234567890");
        downloadBillPO.setBill_date("20161103");
        downloadBillPO.setBill_type("ALL");
        downloadBillPO.setSign("590C7330DC2691395023E44B3F2C599E");
        DownloadBillRO downloadBillRO = this.payAPI.downloadBill(downloadBillPO);
        System.err.println(JSONObject.toJSONString(downloadBillRO));
    }

    @Test
    public void testReport() throws Exception {
        ReportPO reportPO = new ReportPO();
        reportPO.setAppid("wxda8f17411ad516ef");
        reportPO.setMch_id("1329257601");
        reportPO.setSub_appid("wxf88c7482c9649af2");
        reportPO.setSub_mch_id("1398480802");
        reportPO.setNonce_str("1234567890");
        reportPO.setInterface_url("https://api.mch.weixin.qq.com/pay/unifiedorder");
        reportPO.setExecute_time("1000");
        reportPO.setReturn_code("SUCCESS");
        reportPO.setResult_code("SUCCESS");
        reportPO.setUser_ip("192.168.232.15");
        reportPO.setSign("C32CF09E846BB9BDAF52AE7DBA2628C6");
        ReportRO reportRO = this.payAPI.report(reportPO);
        System.err.println(JSONObject.toJSONString(reportRO));
    }

    @Test
    public void testGetSignKey() throws Exception {
        GetSignKeyPO getSignKeyPO = new GetSignKeyPO();
        getSignKeyPO.setMch_id("1329257601");
        getSignKeyPO.setNonce_str("1234567890");
        getSignKeyPO.setSign(PaySignUtil.paySign(getSignKeyPO, "sDGYszkxbbBMai7G8Vap7FL4gjwNXhvz"));
        GetSignKeyRO getSignKeyRO = this.payAPI.getSignKey(getSignKeyPO);
        System.err.println(JSONObject.toJSONString(getSignKeyRO));
    }
}