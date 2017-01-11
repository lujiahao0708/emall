package com.lujiahao.mapping.pojo;

import java.util.Date;

public class TbItem {
    private Long id;

    private String title;

    private String sellPoint;

    private Long price;

    private Integer num;

    private String barcode;

    private String image;

    private Long cid;

    private Byte status;

    private Date created;

    private Date updated;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public Long getPrice() {
        return price;
    }

    public Integer getNum() {
        return num;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getImage() {
        return image;
    }

    public Long getCid() {
        return cid;
    }

    public Byte getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }
}