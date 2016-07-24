package com.example.administrator.southweek.ui.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class SharePreferenceUtils {

    /**
     * 用来设置用户是否登录得状态
     * @param context
     * @param state
     */
    public static void saveLoginState(Context context,boolean state){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("user_config",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit() ;
        editor.putBoolean("login_state",state);
        editor.commit();
    }

    /**
     * 获取用户是否已经登录的状态
     * @param context
     * @return
     */
    public static boolean getLoginState(Context context){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("user_config",Context.MODE_PRIVATE);
        boolean isLogin = sharedPreferences.getBoolean("login_state",false);

        return isLogin ;
    }

     /**
     * 用来保存第三方登录授权获取到的 accessToken值,并且保存时间。
     * @param context
     * @param access_token
     * @param saveTime
     */
    public static void saveUserAccessToken(Context context,String access_token,String saveTime){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("author_config",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit() ;
        editor.putString("access_token", access_token);
        editor.putString("time",saveTime);
        editor.commit();
    }


    /**
     *
     * @param context
     * @return
     */
    public static String getUserAccessToken(Context context){
        SharedPreferences sharedPreferences =
                context.getSharedPreferences("author_config", Context.MODE_PRIVATE);

        String time = sharedPreferences.getString("time", "0");

        // 首先判断 token是否过期
        String access_token = "" ;
        try{
            long diffTime = System.currentTimeMillis() - Long.parseLong(time);
            if(diffTime < 121206){
                access_token  = sharedPreferences.getString("access_token", "");
            }

        }catch(Exception e){

        }


        return access_token ;
    }

}
