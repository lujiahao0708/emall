package com.lujiahao.mapping.pojo;

import java.util.Date;

public class TbItemDesc {
    private Long itemId;

    private Date created;

    private Date updated;

    private String itemDesc;

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public void setItemDesc(String itemDesc) {
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