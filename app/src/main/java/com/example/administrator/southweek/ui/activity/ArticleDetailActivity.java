package com.example.administrator.southweek.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.bean.ArtcleDetail;
import com.example.administrator.southweek.ui.utils.Contants;
import com.example.administrator.southweek.ui.utils.DetailXmlParserUtils;
import com.example.administrator.southweek.ui.utils.HttpHelper;

import java.io.InputStream;

/**
 * 新闻详情
 */
public class ArticleDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView mWebView;
    private   ArtcleDetail mDetail ;
    private final static int DETAIL_HANDLER_WHAT = 1000 ;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           int what = msg.what ;
            if(what == DETAIL_HANDLER_WHAT) {
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.setWebViewClient(new WebViewClient());
                mWebView.getSettings().setDefaultTextEncodingName("utf-8");
                mWebView.getSettings().setLayoutAlgorithm(WebSettings.
                        LayoutAlgorithm.SINGLE_COLUMN);

                sub_subject.setText(mDetail.getSub_subject());
                author.setText(mDetail.getAuthor());
                time.setText(mDetail.getPublish_time());
                source.setText(mDetail.getSource());
                mWebView.loadData(mDetail.getFulltext(), "text/html; charset=UTF-8", null);
                //setData();
            }
        }
    };

    private void setData() {
        int comment=Integer.parseInt(mDetail.getComment_count());
        int share=Integer.parseInt(mDetail.getShare_count());
        if (comment>0){
            tv_comment_num.setVisibility(View.VISIBLE);
            tv_comment_num.setText(comment);
        }
        if (share>0){
            tv_share_num.setVisibility(View.VISIBLE);
            tv_share_num.setText(comment);
        }
    }

    private TextView sub_subject,source,time,author;
    private TextView tv_like_num,tv_comment_num,tv_share_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);

        initView();
    }

    private void initView() {
        sub_subject = (TextView) findViewById(R.id.detail_tv_sub_subject);
        author= (TextView) findViewById(R.id.detail_tv_author);
        time= (TextView) findViewById(R.id.detail_tv_time);
        source= (TextView) findViewById(R.id.detail_tv_source);

        mWebView= (WebView) findViewById(R.id.detail_webview);
        View view = findViewById(R.id.bottom_layout);
        view.findViewById(R.id.layout_back).setOnClickListener(this);
        tv_like_num= (TextView) view.findViewById(R.id.tv_like_num);
        tv_comment_num= (TextView) view.findViewById(R.id.tv_comment_num);
        tv_share_num= (TextView) view.findViewById(R.id.tv_share_num);
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt("id");
        //详情页URL，替换ID
       /*
       http://www.infzm.com/mobile/get_contents?platform=android&token=&
       version=4.1.4&user=&hash=82e1ea97d3514c62ea40028218187310
       */
        String url = Contants.SUBJECT_DETAIL +id ;
        //联网下载
        HttpHelper helper=HttpHelper.getInstance();
        //流类型数据
        helper.requestByGet(url, new HttpHelper.CallBack() {
            @Override
            public void onFailure(Exception e) {

            }

            @Override
            public void onResult(final Object inputStream) {
                //返回的数据：inputStream
                //解析XML,要在子线程中
                try {
                    new Thread() {
                        @Override
                        public void run() {
                            try {
                                ArtcleDetail list = DetailXmlParserUtils.parserXML((InputStream) inputStream);
                                if (list != null) {
                                    //流是在子线程中
                                    mDetail = list;
                                    handler.sendEmptyMessage(DETAIL_HANDLER_WHAT);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_back://返回
                ArticleDetailActivity.this.finish();
                break;
        }
    }
}
