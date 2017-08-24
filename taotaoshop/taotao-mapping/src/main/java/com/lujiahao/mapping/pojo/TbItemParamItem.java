package com.lujiahao.mapping.pojo;

import java.util.Date;

public class TbItemParamItem {
    private Long id;

    private Long itemId;

    private Date created;

    private Date updated;

    private String paramData;

    public TbItemParamItem(Long id, Long itemId, Date created, Date updated, String paramData) {
        this.id = id;
        this.itemId = itemId;
        this.created = created;
        this.updated = updated;
        this.paramData = paramData;
    }

    public Long getId() {
        return id;
    }

    public Long getItemId() {
        return itemId;
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