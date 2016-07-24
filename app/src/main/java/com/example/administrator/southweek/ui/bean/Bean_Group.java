package com.example.administrator.southweek.ui.bean;

import java.util.List;

/**
 * Created by from -sky on 2016/7/19.
 */
public class Bean_Group {
    private String name;
    private List<Bean_Child> children;

    public Bean_Group(List<Bean_Child> children, String name) {
        this.children = children;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Bean_Group{" +
                "children=" + children +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Bean_Child> getChildren() {
        return children;
    }

    public void setChildren(List<Bean_Child> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
