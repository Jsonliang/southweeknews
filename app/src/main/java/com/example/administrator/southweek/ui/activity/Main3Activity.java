package com.example.administrator.southweek.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.adapter.Main3ListViewAdapter;
import com.example.administrator.southweek.ui.bean.Bean_News;
import com.example.administrator.southweek.ui.utils.HttpUtil;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Main3Activity extends AppCompatActivity {
    @BindView(R.id.main3_listview)
    ListView listView;
    Main3ListViewAdapter adapter;
    List<Bean_News> list;
    Bean_News news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        ButterKnife.bind(this);
        intiView();
        downData(getIntent().getStringExtra("url"));
    }

    private void intiView() {
         adapter = new Main3ListViewAdapter(this);
        listView.setAdapter(adapter);
    }

    private void downData(String url) {
        HttpUtil.getString(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

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

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setData(list);
                    }
                });
            }
        });
    }
}
