package com.example.administrator.southweek.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.bean.SettingActivityData;
import com.example.administrator.southweek.ui.adapter.SettingLVadapter;
import com.example.administrator.southweek.ui.bean.SettingActivityData;
import com.example.administrator.southweek.ui.utils.DataCleanManager;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nineoldandroids.view.ViewHelper;

import java.util.List;

public class SettingActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;

    private ListView mLv;
    private SettingLVadapter adapter;
    private List<SettingActivityData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_setting);
        initView();
    }

    private String dataSize;

    private void initView() {
        mLv = (ListView) findViewById(R.id.setting_lv);
        View view = findViewById(R.id.setting_toolbar);
        /**
         * calculate cache
         */

        try {
            dataSize = DataCleanManager.getTotalCacheSize(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.findViewById(R.id.tool_bar_goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.this.finish();
            }
        });

        TextView textView= (TextView) view.findViewById(R.id.tool_bar_title);
        textView.setText("设置");

        list =SettingActivityData.getSettingData();
        adapter = new SettingLVadapter(list);
        adapter.setTvNume(dataSize);
        mLv.setAdapter(adapter);

        findViewById(R.id.recommand_layout).
                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                /*intent to appmarket
                * */
                        Toast.makeText(getApplicationContext(),
                                "知道", Toast.LENGTH_SHORT).show();
                    }
                });

        /*item click event
        * */
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int tag = list.get(position).getTag();
                Intent intent;
                switch (tag) {

                    case 2:
                        Toast.makeText(getApplicationContext(),
                                "清除缓存", Toast.LENGTH_SHORT).show();
                        DataCleanManager.clearAllCache(getApplicationContext());
                        try {
                            dataSize = DataCleanManager.getTotalCacheSize(getApplicationContext());
                            adapter.setTvNume(dataSize);
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),
                                "意见反馈", Toast.LENGTH_SHORT).show();
                        Intent intent_feedback = new Intent(getApplicationContext()
                                , FeedBackActivity.class);
                        startActivity(intent_feedback);
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(),
                                "为我们评分", Toast.LENGTH_SHORT).show();
                        Intent inten_tomarket = new Intent();
                        inten_tomarket.setAction("android.intent.action.MAIN");
                        inten_tomarket.addCategory("android.intent.category.APP_MARKET");
                        startActivity(inten_tomarket);
                        break;
                    case 5:
                        Toast.makeText(getApplicationContext(),
                                "关于我们", Toast.LENGTH_SHORT).show();
                        intent = new Intent(getApplicationContext(), AboutUsActivity.class);
                        startActivity(intent);
                        break;

                }
            }
        });
    }


}

