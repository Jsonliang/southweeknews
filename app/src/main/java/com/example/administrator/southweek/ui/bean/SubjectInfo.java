package com.example.administrator.southweek.ui.bean;

/**
 * Created by Administrator on 2016/7/19.
 * subject实体类
 */
public class SubjectInfo {
    private Integer id;//118342
    private String source;//大参考
    private String short_subject;//孩子受得了吗——反思“读经运动”
    private String sub_subject;//"孩子受得了吗 <br/>反思“读经运动”"
    private String display_time;//07-18
    private String comment_count;//4
    private String share_count;//45
    private String author;//贺希荣
    private String media;//media="2016/0714/104900.jpeg"
    private String tag;//读经教育 国学 义务教育
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public String getDisplay_time() {
        return display_time;
    }

    public void setDisplay_time(String display_time) {
        this.display_time = display_time;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getShare_count() {
        return share_count;
    }

    public void setShare_count(String share_count) {
        this.share_count = share_count;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "SubjectInfo{" +
                "id='"+id+'\''+
                "source='" + source + '\'' +
                ", short_subject='" + short_subject + '\'' +
                ", sub_subject='" + sub_subject + '\'' +
                ", display_time='" + display_time + '\'' +
                ", comment_count='" + comment_count + '\'' +
                ", share_count='" + share_count + '\'' +
                ", author='" + author + '\'' +
                ", media='" + media + '\'' +
                ", tag='" + tag + '\'' +
                '}';
    }
}
