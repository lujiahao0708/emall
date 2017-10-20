package com.lujiahao.mapping.pojo;

import java.util.Date;

public class TbContentCategory {
    private Long id;

    private Long parentId;

    private String name;

    private Integer status;

    private Integer sortOrder;

    private Boolean isParent;

    private Date created;

    private Date updated;

    public TbContentCategory(Long id, Long parentId, String name, Integer status, Integer sortOrder, Boolean isParent, Date created, Date updated) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
        this.status = status;
        this.sortOrder = sortOrder;
        this.isParent = isParent;
        this.created = created;
        this.updated = updated;
    }

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