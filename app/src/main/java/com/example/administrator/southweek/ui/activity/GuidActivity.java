package com.example.administrator.southweek.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.southweek.R;

public class GuidActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private GuiPagerAdapter adapter;
    private int[] imageRescour={R.drawable.pic_guidepage_1,R.drawable.pic_guidepage_2,
            R.drawable.pic_guidepage_3,R.drawable.pic_guidepage_4};
    private ImageView[] imageViews=new ImageView[imageRescour.length];
    private LinearLayout indicator_layout;
    private int lastIndex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welecom);
        initView();
    }

    TextView textView=null;
    private void initView() {

        textView=(TextView) findViewById(R.id.click_in);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转
                Intent intent=new Intent(GuidActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        viewPager= (ViewPager) this.findViewById(R.id.welcom_Vp);
        indicator_layout= (LinearLayout) findViewById(R.id.welcom_Indicator);

        /*
        数据的获取
         */
        //欢迎页图片布局参数
        ViewGroup.LayoutParams vlp=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        //小圆圈布局参数
        LinearLayout.LayoutParams llp=new LinearLayout.LayoutParams(10,10);
        llp.leftMargin=10;
        for (int i = 0; i < imageRescour.length; i++) {
            imageViews[i]=new ImageView(this);
            imageViews[i].setLayoutParams(vlp);
            imageViews[i].setImageResource(imageRescour[i]);
            imageViews[i].setScaleType(ImageView.ScaleType.FIT_XY);
            ImageView indicator=new ImageView(this);

            indicator.setLayoutParams(llp);
            //启动时是第一张图片
            if(i==0){
                indicator.setBackgroundResource(R.drawable.point2);
            }else {
                indicator.setBackgroundResource(R.drawable.point1);
            }
            indicator_layout.addView(indicator);
        }
        /*
        设置数据
         */
        adapter=new GuiPagerAdapter();
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(adapter);

    }



    public class GuiPagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener{

        @Override
        public int getCount() {
            return imageViews.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView( imageViews[position]);
            return imageViews[position];
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            indicator_layout.getChildAt(lastIndex).setBackgroundResource(R.drawable.point1);
            indicator_layout.getChildAt(position).setBackgroundResource(R.drawable.point2);

           if(position==imageRescour.length-1){
                textView.setVisibility(View.VISIBLE);
            }else {
                textView.setVisibility(View.GONE);
            }
            lastIndex=position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }



}

