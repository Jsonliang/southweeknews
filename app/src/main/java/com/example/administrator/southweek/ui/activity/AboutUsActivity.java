package com.example.administrator.southweek.ui.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.adapter.SettingLVadapter;
import com.example.administrator.southweek.ui.bean.SettingActivityData;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutUsActivity extends AppCompatActivity {
    @BindView(R.id.app_verson)
    public TextView mVerson;
    @BindView(R.id.aboutus_content)
    public TextView mContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_aboutus);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        View view=this.findViewById(R.id.aboutus_toolbar);
        view.findViewById(R.id.tool_bar_goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AboutUsActivity.this.finish();
            }
        });

        TextView title= (TextView) view.findViewById(R.id.tool_bar_title);
        title.setText("关于我们");

        PackageManager pm = getApplicationContext().getPackageManager();
        try {
           PackageInfo info= pm.getPackageInfo(getApplicationContext().getPackageName(),
                   PackageManager.GET_CONFIGURATIONS);

            String verson=info.versionName;
            mVerson.setText(verson);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


    }

}

