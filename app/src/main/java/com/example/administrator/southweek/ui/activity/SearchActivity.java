package com.example.administrator.southweek.ui.activity;

import android.app.assist.AssistStructure;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.WindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.utils.HttpHelper;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/18.
 */
public class SearchActivity extends AppCompatActivity {
    @BindView(R.id.search_goback)
    public ImageView goback;
    @BindView(R.id.search_edit)
    public EditText searchEdt;
    @BindView(R.id.action_search)
    public ImageView actionSearch;
    @BindView(R.id.result_lv)
    public ListView resultLv;
    @BindView(R.id.result_title)
    public TextView resultTitle;
    private String TAG=SearchActivity.class.getSimpleName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchActivity.this.finish();
            }
        });

        actionSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = searchEdt.getText().toString();
                if (!TextUtils.isEmpty(keyword)) {
                  String url="http://www.infzm.com/mobile/search?&keywords[]=" +
                          keyword+"&start=0&count=10&hash=" +
                          "82e1ea97d3514c62ea40028218187310&platform"+
                          "android&version=4.1.4";
                    HttpHelper.getInstance().requestByGet(url, new HttpHelper.StringCallBack() {
                        @Override
                        public void onFailure(Exception e) {

                        }

                        @Override
                        public void onResult(Object string) {
                                String str= (String) string;
                            Log.i(TAG, "onResult: "+str);
                            FileOutputStream fous=null;
                            try {
                                fous=new FileOutputStream("/mnt/sdcard/searchResult.txt");
                                fous.write(str.getBytes("utf-8"),0,str.getBytes("utf-8").length);
                                Log.i(TAG, "onResult:  ----->ok");
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }finally {
                                if(fous!=null){
                                    try {
                                        fous.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }


                        }
                    });

                } else {
                    Toast.makeText(SearchActivity.
                            this, "关键字不能为空",
                            Toast.LENGTH_SHORT).show();
                    return;

                }

            }
        });
    }
}
