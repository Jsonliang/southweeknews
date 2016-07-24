package com.example.administrator.southweek.ui.adapter;

import android.transition.CircularPropagation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.activity.SettingActivity;
import com.example.administrator.southweek.ui.bean.SettingActivityData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/19.
 */
public class SettingLVadapter extends BaseAdapter {
    List<SettingActivityData> list;
    private String tvNume;

    public void setTvNume(String tvNume) {
        this.tvNume = tvNume;
    }

    public SettingLVadapter(List<SettingActivityData> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public SettingActivityData getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).
                    inflate(R.layout.activity_setting_lv_item_layout, null);
            viewHolder = new ViewHolder(convertView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        SettingActivityData sd = getItem(position);
        int tag = sd.getTag();
        String title = sd.getTitle();
        switch (tag) {
            case 1:
                viewHolder.imageButton.setVisibility(View.VISIBLE);
                viewHolder.iV.setVisibility(View.GONE);
                viewHolder.tvNumber.setVisibility(View.GONE);
                break;
            case 2:
                viewHolder.imageButton.setVisibility(View.GONE);
                viewHolder.iV.setVisibility(View.GONE);
                viewHolder.tvNumber.setVisibility(View.VISIBLE);
                viewHolder.tvNumber.setText(tvNume);
                parent.getContext().getApplicationContext();
                break;
           default:
                viewHolder.imageButton.setVisibility(View.GONE);
                viewHolder.iV.setVisibility(View.VISIBLE);
                viewHolder.tvNumber.setVisibility(View.GONE);
                break;

        }
        viewHolder.title.setText(title);
        viewHolder.imageButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(parent.getContext(), "关闭推送", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(parent.getContext(), "打开推送", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return convertView;
    }

    public static class ViewHolder {
        @BindView(R.id.setting_lv_itemtitle)
        public TextView title;
        @BindView(R.id.setting_lVitemimage_button)
        public CheckBox imageButton;
        @BindView(R.id.setting_lVitem_tv)
        public TextView tvNumber;
        @BindView(R.id.setting_lVitem_iv)
        public ImageView iV;

        public ViewHolder(View convertView) {
            ButterKnife.bind(ViewHolder.this,convertView);
        }

    }
}
