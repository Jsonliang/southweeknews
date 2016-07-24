package com.example.administrator.southweek.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.adapter.RecyclerViewAdapter;
import com.example.administrator.southweek.ui.bean.SubjectInfo;
import com.example.administrator.southweek.ui.utils.Contants;
import com.example.administrator.southweek.ui.utils.HttpHelper;
import com.example.administrator.southweek.ui.utils.XmlParserUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nineoldandroids.view.ViewHelper;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.SMSSDK;
public class MainActivity extends FragmentActivity {
    private RecyclerView mRecyclerView;
    private DrawerLayout mDrawerLayout;
    private RecyclerViewAdapter mRVAdapter;
    private List<SubjectInfo> mDatas;
    //private DrawerLayout.DrawerListener mDrawerListenner;
    private RelativeLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Fresco.initialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        SMSSDK.initSDK(MainActivity.this, "151c605ca59b1", 
		"c311d88898ad6aaccecd1ddb3feed96f");
        /*show draweelayout
        * */
        initDatas();
        initView();
        initEvents();
    }

    private HttpHelper helper;
    //联网下载数据
    private void initDatas() {
        mDatas=new ArrayList<>();
        helper=HttpHelper.getInstance();
        //用get请求方式,返回流类型
        helper.requestByGet(Contants.SUBJECT_CONTENT, new HttpHelper.CallBack() {
            @Override
            public void onFailure(Exception e) {
                //失败
                Toast.makeText(MainActivity.this, Html.fromHtml("<u>网络连接不正常...</u>"), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResult(Object inputStream) {
                //成功,解析数据：xml格式
                try {
                    //注意这里不能直接赋给 mDatas
                    final List<SubjectInfo>  list= XmlParserUtils.parserXML((InputStream) inputStream);
                    if(list != null && list.size() != 0){
                     //流是在子线程中
                     runOnUiThread(new Runnable() {
                         @Override
                         public void run() {
                             mDatas.clear();
                             mDatas.addAll(list);
                             mRVAdapter.notifyDataSetChanged();
                         }
                     });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private SharedPreferences pref;
    @Override
    protected void onResume() {
        super.onResume();
        pref = getSharedPreferences("system", Context.MODE_PRIVATE);
        pref.edit().putBoolean("isfirst", true).commit();
    }

    private void initView() {
        View view = findViewById(R.id.mainActivity_content);
        View fragment = findViewById(R.id.mainActivity_fragment);
        view.findViewById(R.id.mainActivity_menu).setOnClickListener(new 
		View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRightMenu();
            }
        });
        view.findViewById(R.id.mainActivity_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        mDrawerLayout = (DrawerLayout) findViewById(R.id.mainActivity_drawerlayout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED,
                Gravity.LEFT);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.mainActivity_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false));
        //把下载的数据传到adapter
        mRVAdapter=new RecyclerViewAdapter(this,mDatas);
        //设置监听
        mRVAdapter.setOnItemClickListener(new 
		RecyclerViewAdapter.onItemClickListener() {
            //item的点击事件，调转到详情页
            @Override
            public void OnItemClick(View v, int pos) {
                //Toast.makeText(MainActivity.this, "OnItemLongClick:pos=" + pos, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplication(),ArticleDetailActivity.class);
                Bundle bundle=new Bundle();
                bundle.putInt("id", pos);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        mRecyclerView.setAdapter(mRVAdapter);
        // mRecyclerView.setItemAnimator(new DefaultItemAnimator());//默认动画
        //添加自定义的分割线
       /* mRecyclerView.addItemDecoration(new RecycleViewDivider(
                mContext, LinearLayoutManager.VERTICAL, 10,
                getResources().getColor(R.color.colorDivideWhite,null)));
    }*/
    }
    public void openRightMenu() {
        mDrawerLayout.openDrawer(Gravity.LEFT);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED,
                Gravity.LEFT);
    }

    private void initEvents() {
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() 
		{
            @Override
            public void onDrawerStateChanged(int newState) {
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) 
			{
                View mContent = mDrawerLayout.getChildAt(0);
                View mMenu = drawerView;

                float scale = 1 - slideOffset;
                float rightScale = 0.8f + scale * 0.2f;

                float leftScale = 1 - 0.3f * scale;
                ViewHelper.setScaleX(mMenu, leftScale);
                ViewHelper.setScaleY(mMenu, leftScale);
                ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));
                ViewHelper.setTranslationX(mContent,
                        mMenu.getMeasuredWidth() * (1 - scale));
                ViewHelper.setPivotX(mContent, 0);
                ViewHelper.setPivotY(mContent,
                        mContent.getMeasuredHeight() / 2);
                mContent.invalidate();
                ViewHelper.setScaleX(mContent, rightScale);
                ViewHelper.setScaleY(mContent, rightScale);
            }


            @Override
            public void onDrawerOpened(View drawerView) {
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                mDrawerLayout.setDrawerLockMode(
                        DrawerLayout.LOCK_MODE_LOCKED_CLOSED, 
						Gravity.LEFT);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        mDrawerLayout.closeDrawer(Gravity.LEFT);
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }
    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", 
			Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

        } else {
            finish();
            System.exit(0);
        }
    }
}

