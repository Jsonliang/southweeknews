package com.example.administrator.southweek.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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
 * Created by from -sky on 2016/7/21.
 */
public class Main3ListViewAdapter extends BaseAdapter {
    List<Bean_News> list;
    Context context;
    LayoutInflater inflater;

    public Main3ListViewAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }
    public void  setData(List<Bean_News> list){
        if (list!=null){
            this.list=list;
            notifyDataSetChanged();
        }
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.main3_listview_item, null);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
           viewHolder= (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load("http://images.infzm.com/medias/" + list.get(position).getMedia()).placeholder(R.drawable.pic_loading_logo).into(viewHolder.pic);
        viewHolder.source.setText(list.get(position).getSource());
        viewHolder.subject.setText(String.format(list.get(position).getShort_subject()));
        viewHolder.comment.setText(String.format(list.get(position).getComment_count()));
        viewHolder.share.setText(String.format(list.get(position).getShare_count()));
        viewHolder.display_time.setText(HttpUtil.getTime(list.get(position).getDisplay_time()));
        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.main3_listview_item_image)
        ImageView pic;
        @BindView(R.id.main3_listview_item_subject)
        TextView subject;
        @BindView(R.id.main3_listview_item_display_time)
        TextView display_time;
        @BindView(R.id.main3_listview_item_source)
        TextView source;
        @BindView(R.id.main3_listview_item_comment_count)
        TextView comment;
        @BindView(R.id.main3_listview_item_share_count)
        TextView share;

        public ViewHolder(View view) {
            ButterKnife.bind(this,view);
        }
    }
}
