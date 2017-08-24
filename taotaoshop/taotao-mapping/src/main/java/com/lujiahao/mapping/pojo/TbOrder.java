package com.lujiahao.mapping.pojo;

import java.util.Date;

public class TbOrder {
    private String orderId;

    private String payment;

    private Integer paymentType;

    private String postFee;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Date paymentTime;

    private Date consignTime;

    private Date endTime;

    private Date closeTime;

    private String shippingName;

    private String shippingCode;

    private Long userId;

    private String buyerMessage;

    private String buyerNick;

    private Integer buyerRate;

    public TbOrder(String orderId, String payment, Integer paymentType, String postFee, Integer status, Date createTime, Date updateTime, Date paymentTime, Date consignTime, Date endTime, Date closeTime, String shippingName, String shippingCode, Long userId, String buyerMessage, String buyerNick, Integer buyerRate) {
        this.orderId = orderId;
        this.payment = payment;
        this.paymentType = paymentType;
        this.postFee = postFee;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.paymentTime = paymentTime;
        this.consignTime = consignTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.shippingName = shippingName;
        this.shippingCode = shippingCode;
        this.userId = userId;
        this.buyerMessage = buyerMessage;
        this.buyerNick = buyerNick;
        this.buyerRate = buyerRate;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getPayment() {
        return payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public String getPostFee() {
        return postFee;
    }

    public Integer getStatus() {
        return status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public Date getConsignTime() {
        return consignTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public String getShippingCode() {
        return shippingCode;
    }

    public Long getUserId() {
        return userId;
    }

    public String getBuyerMessage() {
        return buyerMessage;
    }

    public String getBuyerNick() {
        return buyerNick;
    }

    public Integer getBuyerRate() {
        return buyerRate;
    }
}