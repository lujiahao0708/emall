package com.lujiahao.mapping.pojo;

public class TbOrderItem {
    private String id;

    private String itemId;

    private String orderId;

    private Integer num;

    private String title;

    private Long price;

    private Long totalFee;

    private String picPath;

    public TbOrderItem(String id, String itemId, String orderId, Integer num, String title, Long price, Long totalFee, String picPath) {
        this.id = id;
        this.itemId = itemId;
        this.orderId = orderId;
        this.num = num;
        this.title = title;
        this.price = price;
        this.totalFee = totalFee;
        this.picPath = picPath;
    }

    public String getId() {
        return id;
    }

    public String getItemId() {
        return itemId;
    }

    public String getOrderId() {
        return orderId;
    }

    public Integer getNum() {
        return num;
    }

    public String getTitle() {
        return title;
    }

    public Long getPrice() {
        return price;
    }

    public Long getTotalFee() {
        return totalFee;
    }

    public String getPicPath() {
        return picPath;
    }
}