package com.example.administrator.southweek.ui.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.southweek.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedBackActivity extends AppCompatActivity {
    @BindView(R.id.feedback_suggestcotent)
    public EditText mSuggestion;
    @BindView(R.id.feedback_suggestnum)
    public EditText mNumorEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_feedback);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        View view = this.findViewById(R.id.feedback_toolbar);
        TextView title = (TextView) view.findViewById(R.id.tool_bar_title);
        title.setText("意见反馈");
        view.findViewById(R.id.tool_bar_goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedBackActivity.this.finish();
            }
        });
        TextView textView= (TextView) view.findViewById(R.id.toolbar_submit);
        textView.setVisibility(View.VISIBLE);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mSuggestion.getText().toString();
                String subnum = mNumorEmail.getText().toString();
                if (!TextUtils.isEmpty(content)) {
                    if (!TextUtils.isEmpty(subnum)) {
                        if(isEmail(subnum)||isMobileNO(subnum)){
                            Toast.makeText(getApplicationContext(),"ok",Toast.LENGTH_SHORT).show();
                            return;
                        }else {
                            Toast.makeText(getApplicationContext(),
                                    "请输入正确的手机号码或者邮箱喔",Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(),"请输入您手机号码或者邮箱"
                                ,Toast.LENGTH_SHORT).show();
                        return;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "多写两句吧",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });


    }
    public static boolean isMobileNO(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,//D])|(18[0,5-9]))//d{8}$");
        Matcher m = p.matcher(mobiles);
        System.out.println(m.matches() + "---");
        return m.matches();
    }
    public static boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }

}

