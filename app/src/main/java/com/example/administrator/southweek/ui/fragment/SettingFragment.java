package com.example.administrator.southweek.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.activity.LoginActivity;
import com.example.administrator.southweek.ui.activity.Main3Activity;
import com.example.administrator.southweek.ui.activity.MyCollectionActivity;
import com.example.administrator.southweek.ui.activity.PersonInfoActivity;
import com.example.administrator.southweek.ui.activity.SettingActivity;
import com.example.administrator.southweek.ui.adapter.ExpandableLVAdapter;
import com.example.administrator.southweek.ui.app.App;
import com.example.administrator.southweek.ui.base.BaseExpandablelistview;
import com.example.administrator.southweek.ui.bean.Bean_Child;
import com.example.administrator.southweek.ui.bean.Bean_Group;
import com.example.administrator.southweek.ui.utils.HttpUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/7/18.
 */
public class SettingFragment extends Fragment {
    private View mLayout;
    private SimpleDraweeView mImage;

    private BaseExpandablelistview mELV;
    private ExpandableLVAdapter adapter;
    private TextView settingText;
    private TextView collection;
    private App appCotext = null;
    private boolean isFirst = true;
    private List<Bean_Group> dataList;
    //title数据集

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mLayout = inflater.inflate(R.layout.fragment_setting_layout, container, false);
        ButterKnife.bind(this, mLayout);
        appCotext = (App) this.getActivity().getApplication();
        initView();
        downResorce();
        return mLayout;
    }

    private void downResorce() {
        HttpUtil.getString("http://www.infzm.com/mobile/get_source_list?platform=android&version=4.1.4&hash=82e1ea97d3514c62ea40028218187310&format=json", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    JSONArray sources = object.getJSONArray("sources");
                    dataList = new ArrayList<Bean_Group>();
                    dataList.add(new Bean_Group(new ArrayList<Bean_Child>(), "大事件"));
                    dataList.add(new Bean_Group(new ArrayList<Bean_Child>(), "市场派"));
                    dataList.add(new Bean_Group(new ArrayList<Bean_Child>(), "文化控"));
                    dataList.add(new Bean_Group(new ArrayList<Bean_Child>(), "舆论场"));
                    dataList.add(new Bean_Group(new ArrayList<Bean_Child>(), "视觉志"));
                    dataList.add(new Bean_Group(new ArrayList<Bean_Child>(), "新生活"));
                    Bean_Group group = null;
                    for (int i = 0; i < sources.length(); i++) {
                        int id = sources.getJSONObject(i).getInt("id");
                        String title = sources.getJSONObject(i).getString("title");
                        if (id < 35) {
                            dataList.get(0).getChildren().add(new Bean_Child(id, title));
                        } else if (id < 43) {
                            dataList.get(1).getChildren().add(new Bean_Child(id, title));
                        } else if (id < 50) {
                            dataList.get(2).getChildren().add(new Bean_Child(id, title));
                        } else if (id < 56) {
                            dataList.get(3).getChildren().add(new Bean_Child(id, title));
                        } else if (id < 62) {
                            dataList.get(4).getChildren().add(new Bean_Child(id, title));
                        } else {
                            dataList.get(5).getChildren().add(new Bean_Child(id, title));
                        }
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.setData(dataList);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private boolean isLogIn = false;
    private Intent intent = null;

    private void initView() {
        isLogIn = appCotext.isLogin();
        intent = new Intent();
        mImage = (SimpleDraweeView) mLayout.findViewById(R.id.slidImage);
        mELV = (BaseExpandablelistview) mLayout.findViewById(R.id.frag_expandlistview);
        adapter = new ExpandableLVAdapter(getActivity());
        mELV.setAdapter(adapter);
        /*header icon click event
        * */
        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if login
                * */
                if (isLogIn) {
                    /*intent to personInfoActivity
                    * */
                    intent.setClass(appCotext, PersonInfoActivity.class);
                } else {
                    /*intent to loginActivity
                    * */
                    intent.setClass(appCotext,
                            LoginActivity.class);
                }
                SettingFragment.this.getActivity().startActivity(intent);
            }
        });
        /*intent to settingActivity
        * */
        settingText = (TextView) mLayout.findViewById(R.id.frag_setting);
        settingText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(appCotext,
                        SettingActivity.class);
                SettingFragment.this.getActivity().startActivity(intent);
            }
        });
        /*intent to Collection tActivity
        * */
        collection = (TextView) mLayout.findViewById(R.id.frag_colletion);
        collection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                    if login
                 */
                if (isLogIn) {
                    intent.setClass(appCotext, MyCollectionActivity.class);
                } else {
                    intent.setClass(appCotext,
                            LoginActivity.class);
                }
                SettingFragment.this.getActivity().startActivity(intent);
            }
        });
        mELV.setGroupIndicator(null);
        mELV.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //
                Intent intent =new Intent(getActivity(), Main3Activity.class);
                intent.putExtra("url","http://www.infzm.com/mobile/get_list_by_cat_ids?count=10&platform=android&click=1&version=4.1.4&start=0&cat_id%5B%5D=47"+(dataList.get(groupPosition).getChildren().get(childPosition).getId()-14)+"&hash=cbd74a71c07d15f2ddafa2afa7871fca");
                startActivity(intent);

                return false;
            }
        });
    }

}
