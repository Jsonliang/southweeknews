package com.example.administrator.southweek.ui.utils;

import com.example.administrator.southweek.ui.bean.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/7/21 0021.
 */
public class JsonParserUtils {

    /**
     * 解析第三方登录是获取用户信息
     * @param jsonStr
     * @return
     */
    public static UserInfo getUserInfo(String jsonStr){
        UserInfo info = null ;
        try {
            JSONObject object = new JSONObject(jsonStr);
            info = new UserInfo();

            info.setName(object.optString("screen_name"));
            info.setLocation(object.optString("location"));
            info.setImg(object.optString("profile_image_url"));
            info.setGender(object.optString("gender"));

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return info ;
    }
}
