package com.example.administrator.southweek.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.southweek.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/20 0020.
 */
public class CollectionLVAdapter extends BaseAdapter {
    private List<Object> data ;

    public CollectionLVAdapter( List<Object> data){
        this.data = data ;
    }
    @Override
    public int getCount() {
        return data == null ? 0:data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
         ViewHolder holder = null ;

        if(view == null){

            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_item_layout,
                    parent,false);
            holder = new ViewHolder() ;
            ButterKnife.bind(holder,view);
            view.setTag(holder);
        }
        holder = (ViewHolder) view.getTag();
        //TODO
        return view;
    }

    public class ViewHolder{
        @BindView(R.id.collection_subject)
        public TextView  tv_subject ;
        @BindView(R.id.collection_pubdate)
        public TextView  tv_pubDate ;
        @BindView(R.id.collection_views)
        public TextView  tv_views ;
        @BindView(R.id.collection_share)
        public TextView  tv_share ;
        @BindView(R.id.collection_sort)
        public TextView  tv_sort ;

        @BindView(R.id.collection_img)
        public ImageView iv_img ;
    }
}
