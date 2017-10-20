package com.lujiahao.mapping.pojo;

import java.util.Date;

public class TbItemDesc {
    private Long itemId;

    private Date created;

    private Date updated;

    private String itemDesc;

    public TbItemDesc(Long itemId, Date created, Date updated, String itemDesc) {
        this.itemId = itemId;
        this.created = created;
        this.updated = updated;
        this.itemDesc = itemDesc;
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

    public String getItemDesc() {
        return itemDesc;
    }
}