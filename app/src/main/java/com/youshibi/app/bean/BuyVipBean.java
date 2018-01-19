package com.youshibi.app.bean;

/**
 * Created by Suzukaze on 2017-12-21.
 * ====
 * BuyVipBean.
 */

public class BuyVipBean {


    /**
     * biz_content : {"productCode":"QUICK_WAP_PAY","body":"4f1a5458595799106d4b8bd501ff1a63cf8ff0","subject":"4f1a5458595799106d4b8bd501ff1a68079898","total_amount":0.01,"out_trade_no":"15138170958987","passback_params":"{\"pay_type\":\"alipay_wap\"}"}
     * app_id : 2017080408039233
     * version : 1.0
     * format : json
     * sign_type : RSA2
     * method : alipay.trade.app.pay
     * timestamp : 2017-12-21 08:44:55
     * alipay_sdk : alipay-sdk-php-20161101
     * terminal_type : null
     * terminal_info : null
     * prod_code : null
     * notify_url : http://xs.cnei.cc/wmcms/notify/pay.php
     * return_url : http://xs.cnei.cc/module/user/charge_success.php
     * charset : UTF-8
     * sign : Y0uCuEcY+p+JFv9KLPBpSbdFlpZo2ckaUsdG2w1phkmKb8+H2bAL4k8RdfwVLCGjPU1j/EIh8ZSPswYlpphqtemNUQZe2hcNHQLl43DyTDABDOoTHOhSz2MqO8I5OaR8YCLBJ1MbSMUAE9I68HYDUA+P6bBEWYO/kXUxm1m4rJJMsBrzPVPpkpwmyeok/tgrHHxs008sdLOLudDipf+jQjNl3aym7aTertaHRdNrRP3Mnbs3y9C4pQVBQ9b4erfaHLdikilVxIC4TCbzbR+zF/ZC4IjVLVeMXF+WEm/x/2YxcUu6ZVMgvfAjYVrCH6w3f78PJK83QAbtj0Lf+98tsg==
     */

    private String biz_content;
    private String app_id;
    private String version;
    private String format;
    private String sign_type;
    private String method;
    private String timestamp;
    private String alipay_sdk;
    private Object terminal_type;
    private Object terminal_info;
    private Object prod_code;
    private String notify_url;
    private String return_url;
    private String charset;
    private String sign;

    public String getBiz_content() {
        return biz_content;
    }

    public void setBiz_content(String biz_content) {
        this.biz_content = biz_content;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getAlipay_sdk() {
        return alipay_sdk;
    }

    public void setAlipay_sdk(String alipay_sdk) {
        this.alipay_sdk = alipay_sdk;
    }

    public Object getTerminal_type() {
        return terminal_type;
    }

    public void setTerminal_type(Object terminal_type) {
        this.terminal_type = terminal_type;
    }

    public Object getTerminal_info() {
        return terminal_info;
    }

    public void setTerminal_info(Object terminal_info) {
        this.terminal_info = terminal_info;
    }

    public Object getProd_code() {
        return prod_code;
    }

    public void setProd_code(Object prod_code) {
        this.prod_code = prod_code;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getReturn_url() {
        return return_url;
    }

    public void setReturn_url(String return_url) {
        this.return_url = return_url;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
