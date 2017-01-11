package com.lujiahao.mapping.pojo;

import java.util.Date;

public class TbOrderShipping {
    private String orderId;

    private String receiverName;

    private String receiverPhone;

    private String receiverMobile;

    private String receiverState;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;

    private Date created;

    private Date updated;

    public TbOrderShipping(String orderId, String receiverName, String receiverPhone, String receiverMobile, String receiverState, String receiverCity, String receiverDistrict, String receiverAddress, String receiverZip, Date created, Date updated) {
        this.orderId = orderId;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.receiverMobile = receiverMobile;
        this.receiverState = receiverState;
        this.receiverCity = receiverCity;
        this.receiverDistrict = receiverDistrict;
        this.receiverAddress = receiverAddress;
        this.receiverZip = receiverZip;
        this.created = created;
        this.updated = updated;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public String getReceiverState() {
        return receiverState;
    }

    public String getReceiverCity() {
        return receiverCity;
    }

    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public String getReceiverZip() {
        return receiverZip;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }
}