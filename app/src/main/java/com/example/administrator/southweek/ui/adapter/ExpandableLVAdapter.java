package com.example.administrator.southweek.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.bean.Bean_Group;
import com.example.administrator.southweek.ui.activity.Main2Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class ExpandableLVAdapter extends BaseExpandableListAdapter {
    int[] pics = new int[]{R.drawable.ic_leftnav_1, R.drawable.ic_leftnav_2, R.drawable.ic_leftnav_3, R.drawable.ic_leftnav_4, R.drawable.ic_leftnav_5, R.drawable.ic_leftnav_6};
    int[] nums = new int[]{34, 35, 36, 37, 38, 49};
    Context context;
    LayoutInflater inflater;
    List<Bean_Group> list;

    public void setData(List<Bean_Group> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public ExpandableLVAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        list = new ArrayList<>();
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getChildren().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getChildren().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int i, boolean b, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandview_group_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.expandview_tv);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.group_right_pic);
            viewHolder.imageView2 = (ImageView) convertView.findViewById(R.id.group_left_pic);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(i).getName());
        if (b) {
            viewHolder.imageView.setImageResource(R.drawable.ic_leftnav_up);
        } else {
            viewHolder.imageView.setImageResource(R.drawable.ic_leftnav_next);
        }
        viewHolder.imageView2.setImageResource(pics[i]);

        viewHolder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Main2Activity.class);
                intent.putExtra("title", list.get(i).getName());
                intent.putExtra("url", "http://www.infzm.com/mobile/get_list_by_cat_ids?count=11&platform=android&click=1&version=4.1.4&start=0&cat_id%5B%5D=48" + nums[i] + "&hash=82e1ea97d3514c62ea40028218187310");
                Log.d("fromsky","http://www.infzm.com/mobile/get_list_by_cat_ids?count=11&platform=android&click=1&version=4.1.4&start=0&cat_id%5B%5D=48" + nums[i] + "&hash=82e1ea97d3514c62ea40028218187310");
                context.startActivity(intent);



            }
        });
        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.expandview_child_item, null);
            viewHolder = new ViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.expandview_child_tv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(i).getChildren().get(i1).getTitle());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    class ViewHolder {
        TextView textView;
        ImageView imageView, imageView2;
    }
}
