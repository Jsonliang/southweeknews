package com.example.administrator.southweek.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.bean.Bean_News;
import com.example.administrator.southweek.ui.utils.HttpUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by from -sky on 2016/7/20.
 */
public class BaseGridViewAdapter extends BaseAdapter {
    List<Bean_News> list;
    Context context;
    LayoutInflater inflater;
    LinearLayout.LayoutParams p;

    public void setP(LinearLayout.LayoutParams p) {
        this.p = p;
    }

    public BaseGridViewAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
        list.add(new Bean_News());
    }

    @Override
    public int getCount() {
        if (list.size() == 0) {
            return 0;
        }
        return list.size() - 1;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position + 1);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void setData(List<Bean_News> list) {
        if (list != null) {
            this.list = list;
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.main2_gridview_item, null);
            viewHolder = new ViewHolder(convertView);
            viewHolder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.main2_relativelayout);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.relativeLayout.setLayoutParams(p);
        Picasso.with(context).load("http://images.infzm.com/medias/" + list.get(position + 1).getMedia()).placeholder(R.drawable.pic_home_shadow_big).into(viewHolder.pic);
        viewHolder.source.setText(list.get(position + 1).getSource());
        viewHolder.subject.setText(String.format(list.get(position + 1).getShort_subject()));
        viewHolder.display_time.setText(HttpUtil.getTime(list.get(position + 1).getDisplay_time()));
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.main2_gridview_item_pic)
        ImageView pic;
        @BindView(R.id.main2_gridview_item_source)
        TextView source;
        @BindView(R.id.main2_gridview_item_subject)
        TextView subject;
        @BindView(R.id.main2_gridview_item_display_time)
        TextView display_time;

        RelativeLayout relativeLayout;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
