package com.example.administrator.southweek.ui.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class SettingActivityData {
    private String title;
    private int tag;//according tag show different icon in list item
    public static String[] titles={"推送通知","本地缓存","意见反馈","为我们评分","关于我们"};
    public static int[] showTag={1,2,3,4,5};
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public SettingActivityData(String title, int tag) {
        this.title = title;
        this.tag = tag;
    }


    public static List<SettingActivityData> getSettingData(){
        List<SettingActivityData> list=new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            list.add(new SettingActivityData(titles[i],showTag[i]));
        }
        return list;
    }
}
