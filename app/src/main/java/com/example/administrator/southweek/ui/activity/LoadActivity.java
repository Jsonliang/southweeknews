package com.example.administrator.southweek.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.adapter.RecyclerViewAdapter;
import com.example.administrator.southweek.ui.bean.SubjectInfo;
import com.example.administrator.southweek.ui.utils.Contants;
import com.example.administrator.southweek.ui.utils.HttpHelper;
import com.example.administrator.southweek.ui.utils.XmlParserUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nineoldandroids.view.ViewHelper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LoadActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private SharedPreferences pref;
    private static final String TAG = "LoadActivity";
    //boolean isf=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_loading);
        pref = getSharedPreferences("system", Context.MODE_PRIVATE);

HttpHelper.getInstance().requestByGet("http://www.infzm.com/mobile/search?&keywords[]" +
        "=%E5%8D%97%E6%B5%B7&start=0&count=10&hash=82e1ea97d3514c62ea40028218187310&platform=" +
        "android&version=4.1.4", new HttpHelper.StringCallBack() {
    @Override
    public void onFailure(Exception e) {

    }

    @Override
    public void onResult(Object string) {
String str= (String) string;
        System.out.println(str);
    }
});
        final Intent intent = new Intent();
        //如果是第一次
        if (true) {
            intent.setClass(LoadActivity.this, GuidActivity.class);
            startActivity(intent);
            finish();
        } else {
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    intent.setClass(LoadActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 2000);
        }
    }


    private boolean isFirst() {
        return pref.getBoolean("isfirst", true);
    }


}

