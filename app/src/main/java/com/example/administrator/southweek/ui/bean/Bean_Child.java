package com.example.administrator.southweek.ui.bean;

/**
 * Created by from -sky on 2016/7/19.
 */
public class Bean_Child {
    private String title;
    private int id;

    public Bean_Child(int id, String title) {
        this.id = id;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Bean_Child{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
