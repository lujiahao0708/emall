package com.lujiahao.mapping.pojo;

import java.util.Date;

public class TbContent {
    private Long id;

    private Long categoryId;

    private String title;

    private String subTitle;

    private String titleDesc;

    private String url;

    private String pic;

    private String pic2;

    private Date created;

    private Date updated;

    private String content;

    public TbContent(Long id, Long categoryId, String title, String subTitle, String titleDesc, String url, String pic, String pic2, Date created, Date updated, String content) {
        this.id = id;
        this.categoryId = categoryId;
        this.title = title;
        this.subTitle = subTitle;
        this.titleDesc = titleDesc;
        this.url = url;
        this.pic = pic;
        this.pic2 = pic2;
        this.created = created;
        this.updated = updated;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getTitle() {
        return title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public String getTitleDesc() {
        return titleDesc;
    }

    public String getUrl() {
        return url;
    }

    public String getPic() {
        return pic;
    }

    public String getPic2() {
        return pic2;
    }

    public Date getCreated() {
        return created;
    }

    public Date getUpdated() {
        return updated;
    }

    public String getContent() {
        return content;
    }
}