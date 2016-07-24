package com.example.administrator.southweek.ui.utils;

/**
 * Created by Administrator on 2016/7/19.
 * URL常量
 */
public class Contants {

    /**
     * 首页的列表
     */
    public static final String SUBJECT_CONTENT="http://www.infzm.com/mobile/get_list_by_cat_ids?" +
            "count=10&platform=android&version=4.1.4&start=0&" +
            "cat_id%5B%5D=4815&hash=ea361cdb5f7462698bfe488da86b453a";
           //&hash=82e1ea97d3514c62ea40028218187310
    /**
     * 首页点击进入的详情页
     * 替换id
     */
    public static final String SUBJECT_DETAIL="http://www.infzm.com/mobile/get_contents?" +
            "platform=android&token=&version=4.1.4&user=&hash=ea361cdb5f7462698bfe488da86b453a&id%5B%5D=";


    public static final String APP_KEY = "2070646501"; // 应用的APP_KEY
    public static final String REDIRECT_URL = "https://api.weibo.com/oauth2/default.html";// 应用的回调页
    public static final String SCOPE = // 应用申请的高级权限
            "email,direct_messages_read,direct_messages_write,"
                    + "friendships_groups_read,friendships_groups_write,statuses_to_me_read,"
                    + "follow_app_official_microblog," + "invitation_write";


    public static final String USER_PATH =
            "https://api.weibo.com/2/users/show.json?access_token=";
			    /**
     * 不是完整的图片地址，要加上图片media=2016/0720/104976.jpeg
     * http://images.infzm.com/medias/2016/0720/104976.jpeg
     */
    public static final String MEDIA_IMAGE_URL="http://images.infzm.com/medias/";
}
