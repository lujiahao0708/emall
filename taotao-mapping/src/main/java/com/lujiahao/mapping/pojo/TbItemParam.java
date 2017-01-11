package com.lujiahao.mapping.pojo;

import java.util.Date;

public class TbItemParam {
    private Long id;

    private Long itemCatId;

    private Date created;

    private Date updated;

    private String paramData;

    public TbItemParam(Long id, Long itemCatId, Date created, Date updated, String paramData) {
        this.id = id;
        this.itemCatId = itemCatId;
        this.created = created;
        this.updated = updated;
        this.paramData = paramData;
    }

    public Long getId() {
        return id;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getParamData() {
        return paramData;
    }
}