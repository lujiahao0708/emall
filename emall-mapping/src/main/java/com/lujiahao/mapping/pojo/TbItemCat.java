package com.lujiahao.mapping.pojo;

import java.util.Date;

public class TbItemCat {
    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;

    private Date created;

    private Date updated;


    public Long getId() {
        return id;
    }

    public Long getParentId() {
        return parentId;
    }

    public String getName() {
        return name;
    }

    public Integer getStatus() {
        return status;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }
}