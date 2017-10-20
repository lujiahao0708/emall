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

    public TbItem(Long id, String title, String sellPoint, Long price, Integer num, String barcode, String image, Long cid, Byte status, Date created, Date updated) {
        this.id = id;
        this.title = title;
        this.sellPoint = sellPoint;
        this.price = price;
        this.num = num;
        this.barcode = barcode;
        this.image = image;
        this.cid = cid;
        this.status = status;
        this.created = created;
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