package com.example.administrator.southweek.ui.app;

import android.app.Application;

import cn.smssdk.SMSSDK;


/**
 * Created by Administrator on 2016/7/19.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

    }

    /**
     * 是否登录
     */
    private boolean  isLogin = false;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
}
