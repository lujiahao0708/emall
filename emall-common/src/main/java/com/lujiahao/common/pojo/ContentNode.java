package com.lujiahao.common.pojo;

/**
 * @author lujiahao
 * @version V1.0
 * @create 2016-09-11 16:09
 */
public class ContentNode {
    // 节点id
    private long id;
    // 父节点id
    private long parentId;
    // 节点名称
    private String text;
    // 节点状态 1正常 2删除
    private String state;

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
