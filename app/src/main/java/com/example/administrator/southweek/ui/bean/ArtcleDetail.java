package com.example.administrator.southweek.ui.bean;

/**
 * Created by Administrator on 2016/7/20.
 */
public class ArtcleDetail {
    private Integer id;
    private String subject;
    private String short_subject;
    private String sub_subject;//骆惠宁入晋三周，要放哪些大招
    private String author;//南方周末记者 刘昊 南方周末实习生 施佳琦
    private String publish_time;//2016-07-20 01:26:25
    private String source;//时局
    private String description;//资料图：骆惠宁
    //private String author;//东方ic
    private String tag;//骆惠宁 山西 省委书记
    private String share_count;//分享总数
    private String comment_count;//评论总数
    private String fulltext;
    private String introtext;
    private String snsShare;

    public String getSnsShare() {
        return snsShare;
    }

    public void setSnsShare(String snsShare) {
        this.snsShare = snsShare;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getShort_subject() {
        return short_subject;
    }

    public void setShort_subject(String short_subject) {
        this.short_subject = short_subject;
    }

    public String getSub_subject() {
        return sub_subject;
    }

    public void setSub_subject(String sub_subject) {
        this.sub_subject = sub_subject;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getShare_count() {
        return share_count;
    }

    public void setShare_count(String share_count) {
        this.share_count = share_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    public String getIntrotext() {
        return introtext;
    }

    public void setIntrotext(String introtext) {
        this.introtext = introtext;
    }

    @Override
    public String toString() {
        return "ArtcleDetail{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", short_subject='" + short_subject + '\'' +
                ", sub_subject='" + sub_subject + '\'' +
                ", author='" + author + '\'' +
                ", publish_time='" + publish_time + '\'' +
                ", source='" + source + '\'' +
                ", description='" + description + '\'' +
                ", tag='" + tag + '\'' +
                ", share_count='" + share_count + '\'' +
                ", comment_count='" + comment_count + '\'' +
                ", fulltext='" + fulltext + '\'' +
                ", introtext='" + introtext + '\'' +
                ", snsShare='" + snsShare + '\'' +
                '}';
    }
}
