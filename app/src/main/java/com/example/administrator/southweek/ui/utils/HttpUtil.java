package com.example.administrator.southweek.ui.utils;


import android.util.Log;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by from -sky on 2016/7/16.
 */
public class HttpUtil {
    public static void getString(String url, Callback callback) {
        if (url == null) {
            return;
        }
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    //2016-05-20 10:28:37
    public static String getTime(String time) {
        return Integer.parseInt(time.substring(5, 7)) + "月" + Integer.parseInt(time.substring(8, 10))+"日";
    }
}
