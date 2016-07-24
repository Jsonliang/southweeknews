package com.example.administrator.southweek.ui.bean;

/**
 * Created by from -sky on 2016/7/20.
 */
public class Bean_News {
    String id;
    String  cat_id;
    String  link_id;
    String   subject;
    String    short_subject;
    String   sub_subject;
    String   tag;
    String   source;
    String   author;
    String   status;
    String   ordering;
    String  created;
    String  modified;
    String  publish_time;
    String   content_type;
    String template;
    String media;
    String   display_time;
    String   comment_count;
    String   share_count;
    String  is_hot;
    String author_avatar;

    @Override
    public String toString() {
        return "Bean_News{" +
                "author='" + author + '\'' +
                ", id='" + id + '\'' +
                ", cat_id='" + cat_id + '\'' +
                ", link_id='" + link_id + '\'' +
                ", subject='" + subject + '\'' +
                ", short_subject='" + short_subject + '\'' +
                ", sub_subject='" + sub_subject + '\'' +
                ", tag='" + tag + '\'' +
                ", source='" + source + '\'' +
                ", status='" + status + '\'' +
                ", ordering='" + ordering + '\'' +
                ", created='" + created + '\'' +
                ", modified='" + modified + '\'' +
                ", publish_time='" + publish_time + '\'' +
                ", content_type='" + content_type + '\'' +
                ", template='" + template + '\'' +
                ", media='" + media + '\'' +
                ", display_time='" + display_time + '\'' +
                ", comment_count='" + comment_count + '\'' +
                ", share_count='" + share_count + '\'' +
                ", is_hot='" + is_hot + '\'' +
                ", author_avatar='" + author_avatar + '\'' +
                '}';
    }


    public Bean_News( ){

    }

    public Bean_News(String author, String author_avatar, String cat_id, String comment_count, String content_type, String created, String display_time, String id, String is_hot, String link_id, String media, String modified, String ordering, String publish_time, String share_count, String short_subject, String source, String status, String sub_subject, String subject, String tag, String template) {
        this.author = author;
        this.author_avatar = author_avatar;
        this.cat_id = cat_id;
        this.comment_count = comment_count;
        this.content_type = content_type;
        this.created = created;
        this.display_time = display_time;
        this.id = id;
        this.is_hot = is_hot;
        this.link_id = link_id;
        this.media = media;
        this.modified = modified;
        this.ordering = ordering;
        this.publish_time = publish_time;
        this.share_count = share_count;
        this.short_subject = short_subject;
        this.source = source;
        this.status = status;
        this.sub_subject = sub_subject;
        this.subject = subject;
        this.tag = tag;
        this.template = template;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_avatar() {
        return author_avatar;
    }

    public void setAuthor_avatar(String author_avatar) {
        this.author_avatar = author_avatar;
    }

    public String getOrdering() {
        return ordering;
    }

    public void setOrdering(String ordering) {
        this.ordering = ordering;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getContent_type() {
        return content_type;
    }

    public void setContent_type(String content_type) {
        this.content_type = content_type;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDisplay_time() {
        return display_time;
    }

    public void setDisplay_time(String display_time) {
        this.display_time = display_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(String is_hot) {
        this.is_hot = is_hot;
    }

    public String getLink_id() {
        return link_id;
    }

    public void setLink_id(String link_id) {
        this.link_id = link_id;
    }

    public String getMedia() {
        return media;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(String publish_time) {
        this.publish_time = publish_time;
    }

    public String getShare_count() {
        return share_count;
    }

    public void setShare_count(String share_count) {
        this.share_count = share_count;
    }

    public String getShort_subject() {
        return short_subject;
    }

    public void setShort_subject(String short_subject) {
        this.short_subject = short_subject;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSub_subject() {
        return sub_subject;
    }

    public void setSub_subject(String sub_subject) {
        this.sub_subject = sub_subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
