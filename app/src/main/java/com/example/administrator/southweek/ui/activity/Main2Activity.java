package com.example.administrator.southweek.ui.activity;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.os.Handler;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.adapter.BaseGridViewAdapter;
import com.example.administrator.southweek.ui.base.BaseGridView;
import com.example.administrator.southweek.ui.base.PullToRefreshLayout;
import com.example.administrator.southweek.ui.base.PullableScrollView;
import com.example.administrator.southweek.ui.bean.Bean_News;
import com.example.administrator.southweek.ui.utils.HttpUtil;
import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main2Activity extends AppCompatActivity {
    @BindView(R.id.toolBar)
    Toolbar toolbar;
    @BindView(R.id.main2_title)
    TextView title;
    @BindView(R.id.pulltorefeshlayout)
    PullToRefreshLayout pulltorefeshlayout;
    @BindView(R.id.main2_pullableScrollView)
    PullableScrollView scrollView;
    Handler handler;
    List<Bean_News> list;
    Bean_News news;
    //top  控件
    @BindView(R.id.top_image)
    ImageView top_pic;
    @BindView(R.id.top_source)
    TextView top_source;
    @BindView(R.id.top_subject)
    TextView top_subject;
    @BindView(R.id.top_display_time)
    TextView top_display_time;
    @BindView(R.id.top_comment_count)
    TextView top_comment_count;
    @BindView(R.id.top_share_count)
    TextView top_share_count;
    @BindView(R.id.main2_relativelayout_1)
    RelativeLayout relativeLayout;
    //GridView
    @BindView(R.id.main2_gridview)
    BaseGridView gridView;
    BaseGridViewAdapter adapter;

    private boolean isFirst = true;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        intView();
        url = getIntent().getStringExtra("url");
        downData(url);
    }

    private void downData(String url) {

        HttpUtil.getString(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                pulltorefeshlayout.refreshFinish(PullToRefreshLayout.FAIL);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //xml解析
                XmlPullParser parser =
                        null;
                try {
//                    Log.d("fromsky",response.body().string());
                    parser = XmlPullParserFactory.newInstance().newPullParser();
                    parser.setInput(response.body().byteStream(), "utf-8");
                    int event = parser.getEventType();
                    String id = null;
                    String cat_id = null;
                    String link_id = null;
                    String subject = null;
                    String short_subject = null;
                    String sub_subject = null;
                    String tag = null;
                    String source = null;
                    String author = null;
                    String status = null;
                    String ordering = null;
                    String created = null;
                    String modified = null;
                    String publish_time = null;
                    String content_type = null;
                    String template = null;
                    String media = null;
                    String display_time = null;
                    String comment_count = null;
                    String share_count = null;
                    String is_hot = null;
                    String author_avatar = null;
                    while (event != XmlPullParser.END_DOCUMENT) {
                        switch (event) {
                            case XmlPullParser.START_DOCUMENT:
                                list = new ArrayList<Bean_News>();
                                break;
                            case XmlPullParser.START_TAG:
                                if ("item".equals(parser.getName())) {
                                    id = parser.getAttributeValue(0);
                                    cat_id = parser.getAttributeValue(1);
                                    link_id = parser.getAttributeValue(2);
                                    subject = parser.getAttributeValue(3);
                                    short_subject = parser.getAttributeValue(4);
                                    sub_subject = parser.getAttributeValue(5);
                                    tag = parser.getAttributeValue(6);
                                    source = parser.getAttributeValue(7);
                                    author = parser.getAttributeValue(8);
                                    status = parser.getAttributeValue(9);
                                    ordering = parser.getAttributeValue(10);
                                    created = parser.getAttributeValue(11);
                                    modified = parser.getAttributeValue(12);
                                    publish_time = parser.getAttributeValue(13);
                                    content_type = parser.getAttributeValue(14);
                                    template = parser.getAttributeValue(15);
                                    media = parser.getAttributeValue(16);
                                    display_time = parser.getAttributeValue(17);
                                    comment_count = parser.getAttributeValue(18);
                                    share_count = parser.getAttributeValue(19);
                                    is_hot = parser.getAttributeValue(20);
                                    author_avatar = parser.getAttributeValue(21);
                                }

                                break;
                            case XmlPullParser.END_TAG:
                                if ("item".equals(parser.getName()))
                                    list.add(new Bean_News(author, author_avatar, cat_id, comment_count, content_type, created, display_time, id, is_hot, link_id, media, modified, ordering, publish_time, share_count, short_subject, source, status, sub_subject, subject, tag, template));
                                break;
                        }
                        event = parser.next();
                    }
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                Main2Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Picasso.with(Main2Activity.this).load("http://images.infzm.com/medias/" + list.get(0).getMedia()).into(top_pic);
                        top_source.setText(list.get(0).getSource());
                        top_subject.setText(list.get(0).getShort_subject());
                        top_comment_count.setText(list.get(0).getComment_count());
                        top_share_count.setText(list.get(0).getShare_count());
                        top_display_time.setText(HttpUtil.getTime(list.get(0).getDisplay_time()));
                        adapter.setData(list);
                        scrollView.setVisibility(View.VISIBLE);
                        scrollView.smoothScrollTo(0, 0);
                        if (isFirst) {
                            pulltorefeshlayout.setUsePullDown(true);
                            isFirst = false;
                        }else{
                            pulltorefeshlayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                        }
                    }
                });

            }
        });
    }


    private void intView() {
        toolbar.setTitle("");
        //用ToolBar代替ActionBar
        setSupportActionBar(toolbar);
        //显示导航按钮并使之处于可点击状态
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        title.setText(getIntent().getStringExtra("title"));
        adapter = new BaseGridViewAdapter(this);
        gridView.setAdapter(adapter);
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, metric.widthPixels / 2);
        relativeLayout.setLayoutParams(p);
        LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, metric.widthPixels / 2 * 7 / 8);
        adapter.setP(p1);
        scrollView.setVisibility(View.INVISIBLE);
        pulltorefeshlayout.setUsePullDown(false);
        pulltorefeshlayout.setUsePullUp(false);
        pulltorefeshlayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
                downData(url);
            }

            @Override
            public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub

        //add top-left icon click event deal
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
