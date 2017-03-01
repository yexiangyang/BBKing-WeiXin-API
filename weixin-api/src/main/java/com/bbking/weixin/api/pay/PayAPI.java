package com.bbking.weixin.api.pay;

import com.alibaba.fastjson.JSONObject;
import com.bbking.weixin.api.model.*;
import com.bbking.weixin.common.HttpUtil;
import com.bbking.weixin.common.XMLUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;

/**
 * <p>微信支付API：包括公众号支付、扫码支付、刷卡支付、APP支付，兼容境内商户版和境内服务商版</p>
 *
 * @author yexiangyang
 * @create 2016-11-30
 */
public class PayAPI {
    private Logger logger = Logger.getLogger(PayAPI.class);
    /**
     * 统一下单URL
     */
    private String unifiedOrderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    /**
     * 查询订单URL
     */
    private String orderQueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
    /**
     * 关闭订单URL
     */
    private String closeOrderUrl = "https://api.mch.weixin.qq.com/pay/closeorder";
    /**
     * 申请退款URL
     */
    private String refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
    /**
     * 查询退款URL
     */
    private String refundQueryUrl = "https://api.mch.weixin.qq.com/pay/refundquery";
    /**
     * 下载对账单URL
     */
    private String downloadBillUrl = "https://api.mch.weixin.qq.com/pay/downloadbill";
    /**
     * 交易保障URL
     */
    private String reportUrl = "https://api.mch.weixin.qq.com/payitil/report";

    /**
     * <p>默认构造器</p>
     */
    public PayAPI() {
    }

    /**
     * <p>沙箱模式构造器</p>
     *
     * @param sandBox 是否启用沙箱模式
     */
    public PayAPI(boolean sandBox) {
        if (sandBox) {
            this.unifiedOrderUrl = "https://api.mch.weixin.qq.com/sandboxnew/pay/unifiedorder";
            this.orderQueryUrl = "https://api.mch.weixin.qq.com/sandboxnew/pay/orderquery";
            this.closeOrderUrl = "https://api.mch.weixin.qq.com/sandboxnew/pay/closeorder";
            this.refundUrl = "https://api.mch.weixin.qq.com/sandboxnew/secapi/pay/refund";
            this.refundQueryUrl = "https://api.mch.weixin.qq.com/sandboxnew/pay/refundquery";
            this.downloadBillUrl = "https://api.mch.weixin.qq.com/sandboxnew/pay/downloadbill";
            this.reportUrl = "https://api.mch.weixin.qq.com/sandboxnew/payitil/report";
        }
    }


    /**
     * <p>统一下单接口</p>
     * <li>除被扫支付场景以外，商户系统先调用该接口在微信支付服务后台生成预支付交易单，返回正确的预支付交易回话标识后再按扫码、JSAPI、APP等不同场景生成交易串调起支付</li>
     *
     * @param unifiedOrderPO 统一下单接口请求实体类
     * @return 统一下单接口返回实体类
     */
    public UnifiedOrderRO unifiedOrder(UnifiedOrderPO unifiedOrderPO) {
        this.logger.info("PayAPI.unifiedOrder_parameters_unifiedOrderPO_" + JSONObject.toJSONString(unifiedOrderPO));
        UnifiedOrderRO unifiedOrderRO = null;
        if (unifiedOrderPO != null) {
            try {
                String xml = XMLUtil.bean2Xml(unifiedOrderPO);
                String result = HttpUtil.post(this.unifiedOrderUrl, xml);
                if (StringUtils.isNotBlank(result)) {
                    unifiedOrderRO = XMLUtil.xml2Bean(UnifiedOrderRO.class, result);
                }
            } catch (Exception e) {
                this.logger.info("PayAPI.unifiedOrder_error" + e);
            }
        }
        this.logger.info("PayAPI.unifiedOrder_return_" + JSONObject.toJSONString(unifiedOrderRO));
        return unifiedOrderRO;
    }

    /**
     * <p>查询订单接口</p>
     * <p>该接口提供所有微信支付订单的查询，商户可以通过该接口主动查询订单状态，完成下一步的业务逻辑。</p>
     * <p>需要调用查询接口的情况：</p>
     * <li>当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；</li>
     * <li>调用支付接口后，返回系统错误或未知交易状态情况；</li>
     * <li>调用被扫支付API，返回USERPAYING的状态；</li>
     * <li>调用关单或撤销接口API之前，需确认支付状态；</li>
     *
     * @param orderQueryPO 查询订单接口请求实体类
     * @return 查询订单接口返回实体类
     */
    public OrderQueryRO orderQuery(OrderQueryPO orderQueryPO) {
        this.logger.info("PayAPI.orderQuery_parameters_orderQueryPO_" + JSONObject.toJSONString(orderQueryPO));
        OrderQueryRO orderQueryRO = null;
        if (orderQueryPO != null) {
            try {
                String xml = XMLUtil.bean2Xml(orderQueryPO);
                String result = HttpUtil.post(this.orderQueryUrl, xml);
                if (StringUtils.isNotBlank(result)) {
                    orderQueryRO = XMLUtil.xml2Bean(OrderQueryRO.class, result);
                }
            } catch (Exception e) {
                this.logger.info("PayAPI.orderQuery_error" + e);
            }
        }
        this.logger.info("PayAPI.orderQuery_return_" + JSONObject.toJSONString(orderQueryRO));
        return orderQueryRO;
    }

    /**
     * <p>关闭订单接口</p>
     * <p>以下情况需要调用关单接口：</p>
     * <li>商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；</li>
     * <li>系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。</li>
     *
     * @param closeOrderPO 关闭订单接口请求实体类
     * @return 关闭订单接口返回实体类
     */
    public CloseOrderRO closeOrder(CloseOrderPO closeOrderPO) {
        this.logger.info("PayAPI.closeOrder_parameters_closeOrderPO_" + JSONObject.toJSONString(closeOrderPO));
        CloseOrderRO closeOrderRO = null;
        if (closeOrderPO != null) {
            try {
                String xml = XMLUtil.bean2Xml(closeOrderPO);
                String result = HttpUtil.post(this.closeOrderUrl, xml);
                if (StringUtils.isNotBlank(result)) {
                    closeOrderRO = XMLUtil.xml2Bean(CloseOrderRO.class, result);
                }
            } catch (Exception e) {
                this.logger.info("PayAPI.closeOrder_error" + e);
            }
        }
        this.logger.info("PayAPI.closeOrder_return_" + JSONObject.toJSONString(closeOrderRO));
        return closeOrderRO;
    }

    /**
     * <p>申请退款订单接口</p>
     * <p>当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。</p>
     * <p>注意：</p>
     * <li>交易时间超过一年的订单无法提交退款；</li>
     * <li>微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。一笔退款失败后重新提交，要采用原来的退款单号。总退款金额不能超过用户实际支付金额。</li>
     * <p>服务商模式下，退款接口需要单独申请权限，指引链接：http://kf.qq.com/faq/120911VrYVrA150929imAfuU.html</p>
     * <p>TODO 不包含代金券信息,持续维护中...</p>
     *
     * @param refundPO 申请退款接口请求实体类
     * @return 申请退款接口返回实体类
     */
    public RefundRO refund(RefundPO refundPO) {
        this.logger.info("PayAPI.refund_parameters_refundPO_" + JSONObject.toJSONString(refundPO));
        RefundRO refundRO = null;
        if (refundPO != null) {
            InputStream instream = null;
            CloseableHttpClient httpclient = null;
            try {
                //加载退款数字证书
                KeyStore keyStore = KeyStore.getInstance("PKCS12");
                instream = refundPO.getInstream();
                String mch_id = refundPO.getMch_id();
                if (instream != null && StringUtils.isNotBlank(mch_id)) {
                    keyStore.load(instream, mch_id.toCharArray());
                    SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
                    SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null, SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
                    httpclient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
                    HttpPost post = new HttpPost(this.refundUrl);
                    refundPO.setInstream(null);
                    String xml = XMLUtil.bean2Xml(refundPO);
                    if (StringUtils.isNotBlank(xml)) {
                        post.setEntity(new StringEntity(xml, "UTF-8"));
                        HttpEntity entity = httpclient.execute(post).getEntity();
                        refundRO = XMLUtil.xml2Bean(RefundRO.class, EntityUtils.toString(entity, "UTF-8"));
                    }
                }
            } catch (Exception e) {
                this.logger.info("PayAPI.refund_error" + e);
            } finally {
                try {
                    if (instream != null) {
                        instream.close();
                    }
                    if (httpclient != null) {
                        httpclient.close();
                    }
                } catch (Exception e) {
                    this.logger.info("PayAPI.refund_链接关闭异常" + e);
                }
            }
        }
        this.logger.info("PayAPI.refund_return_" + JSONObject.toJSONString(refundRO));
        return refundRO;
    }

    /**
     * <p>查询退款接口</p>
     * <p>提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。</p>
     * <p>TODO 仅支持一次退款申请的查询,不包含代金券信息,持续维护中...</p>
     *
     * @param refundQueryPO 查询退款接口请求实体类
     * @return 查询退款接口返回实体类
     */
    public RefundQueryRO refundQuery(RefundQueryPO refundQueryPO) {
        this.logger.info("PayAPI.refundQuery_parameters_refundQueryRequestVO_" + JSONObject.toJSONString(refundQueryPO));
        RefundQueryRO refundQueryRO = null;
        if (refundQueryPO != null) {
            try {
                String xml = XMLUtil.bean2Xml(refundQueryPO);
                String result = HttpUtil.post(this.refundQueryUrl, xml);
                if (StringUtils.isNotBlank(result)) {
                    refundQueryRO = XMLUtil.xml2Bean(RefundQueryRO.class, result);
                }
            } catch (Exception e) {
                this.logger.info("PayAPI.refundQuery_error" + e);
            }
        }
        this.logger.info("PayAPI.refundQuery_return_" + JSONObject.toJSONString(refundQueryRO));
        return refundQueryRO;
    }

    /**
     * <p>下载对账单接口</p>
     * <p>商户可以通过该接口下载历史交易清单。比如掉单、系统错误等导致商户侧和微信侧数据不一致，通过对账单核对后可校正支付状态。</p>
     * <p>注意：</p>
     * <li>微信侧未成功下单的交易不会出现在对账单中。支付成功后撤销的交易会出现在对账单中，跟原支付单订单号一致，bill_type为REVOKED；</li>
     * <li>微信在次日9点启动生成前一天的对账单，建议商户10点后再获取；</li>
     * <li>对账单中涉及金额的字段单位为“元”。</li>
     *
     * @param downloadBillPO 下载对账单接口请求实体类
     * @return 下载对账单接口返回实体类
     */
    public DownloadBillRO downloadBill(DownloadBillPO downloadBillPO) {
        this.logger.info("PayAPI.downloadBill_parameters_downloadBillPO_" + JSONObject.toJSONString(downloadBillPO));
        DownloadBillRO downloadBillRO = null;
        if (downloadBillPO != null) {
            try {
                String xml = XMLUtil.bean2Xml(downloadBillPO);
                String result = HttpUtil.post(this.downloadBillUrl, xml);
                if (StringUtils.isNotBlank(result)) {
                    if (result.contains("return_code")) {
                        downloadBillRO = XMLUtil.xml2Bean(DownloadBillRO.class, result);
                    } else {
                        downloadBillRO = new DownloadBillRO();
                        downloadBillRO.setText(result);
                    }
                }
            } catch (Exception e) {
                this.logger.info("PayAPI.downloadBill_error" + e);
            }
        }
        this.logger.info("PayAPI.downloadBill_return_" + JSONObject.toJSONString(downloadBillRO));
        return downloadBillRO;
    }

    /**
     * <p>交易保障接口</p>
     * <p>商户在调用微信支付提供的相关接口时，会得到微信支付返回的相关信息以及获得整个接口的响应时间。为提高整体的服务水平，协助商户一起提高服务质量，微信支付提供了相关接口调用耗时和返回信息的主动上报接口，微信支付可以根据商户侧上报的数据进一步优化网络部署，完善服务监控，和商户更好的协作为用户提供更好的业务体验。</p>
     *
     * @param reportPO 交易保障接口请求实体类
     * @return 交易保障接口返回实体类
     */
    public ReportRO report(ReportPO reportPO) {
        this.logger.info("PayAPI.report_parameters_reportPO_" + JSONObject.toJSONString(reportPO));
        ReportRO reportRO = null;
        if (reportPO != null) {
            try {
                String xml = XMLUtil.bean2Xml(reportPO);
                String result = HttpUtil.post(this.reportUrl, xml);
                if (StringUtils.isNotBlank(result)) {
                    reportRO = XMLUtil.xml2Bean(ReportRO.class, result);
                }
            } catch (Exception e) {
                this.logger.info("PayAPI.report_error" + e);
            }
        }
        this.logger.info("PayAPI.report_return_" + JSONObject.toJSONString(reportRO));
        return reportRO;
    }
}
