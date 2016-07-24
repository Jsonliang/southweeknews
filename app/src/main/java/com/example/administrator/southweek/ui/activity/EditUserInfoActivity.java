package com.example.administrator.southweek.ui.activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.southweek.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditUserInfoActivity extends AppCompatActivity {

    @BindView(R.id.userInfo_lv)
    public ListView mListView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_edit_user_info);
        ButterKnife.bind(this);
        initToolbar();
    }
    private void initToolbar(){
        TextView title = (TextView) findViewById(R.id.toobar_center);
        title.setVisibility(View.VISIBLE);
        title.setText("个人信息");
        title.setTextColor(Color.BLACK);
        findViewById(R.id.toobar_right).setVisibility(View.GONE);


        findViewById(R.id.toolbar_line).setVisibility(View.VISIBLE);
        TextView goBack = (TextView) findViewById(R.id.toobar_goback);
        goBack.setText("");
        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.ic_back_grey);
        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
        goBack.setCompoundDrawables(drawable, null, null, null);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditUserInfoActivity.this.finish();
            }
        });
    }
}
