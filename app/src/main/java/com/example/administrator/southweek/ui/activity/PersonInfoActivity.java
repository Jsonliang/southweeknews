package com.example.administrator.southweek.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.southweek.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/7/18.
 */
public class PersonInfoActivity extends AppCompatActivity {
    @BindView(R.id.my_goback)
    public ImageView mGoBack ;
    @BindView(R.id.my_edit)
    public ImageView mEdit ;
    @BindView(R.id.my_user_img)
    public ImageView mUserImg ;
    @BindView(R.id.my_collection)
    public TextView mCollection ;
    @BindView(R.id.my_comment)
    public TextView mComment ;
    @BindView(R.id.my_exit)
    public TextView mExitUser ;

    private Intent intent = null ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personinfo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.my_goback,R.id.my_edit,R.id.my_user_img,R.id.my_collection,
    R.id.my_comment,R.id.my_exit})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.my_goback:
                PersonInfoActivity.this.finish();
                break;

            case R.id.my_edit:
                intent = new  Intent(PersonInfoActivity.this,EditUserInfoActivity.class);
                startActivity(intent);
                break;

            case R.id.my_user_img:
                intent = new  Intent(PersonInfoActivity.this,EditUserInfoActivity.class);
                startActivity(intent);
                break;

            case R.id.my_collection:
                 intent = new  Intent(PersonInfoActivity.this,MyCollectionActivity.class);
                 startActivity(intent);
                break;

            case R.id.my_comment:
                break;

            case R.id.my_exit:

        }
    }
}
