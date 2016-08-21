package com.yujun.asyncloading;

/**
 * Created by 于军 on 2016/5/3.
 */
public class NewsBean {
    private String newIconUrl;
    private String newTitle;
    private String newsContent;

    public String getNewIconUrl() {
        return newIconUrl;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewIconUrl(String newIconUrl) {
        this.newIconUrl = newIconUrl;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }
}
