package com.example.administrator.southweek.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.southweek.R;
import com.example.administrator.southweek.ui.bean.SubjectInfo;
import com.example.administrator.southweek.ui.utils.Contants;
import com.example.administrator.southweek.ui.utils.HttpHelper;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{
    private Context mContext;
    private List<SubjectInfo> mDatas;
    private HttpHelper helper;
    public RecyclerViewAdapter(Context mContext,List<SubjectInfo> mDatas) {
        super();
        this.mContext=mContext;
        this.mDatas=mDatas;
    }
    //设置接口类
    public interface onItemClickListener{
        //接口方法
        void OnItemClick(View v,int pos);
    };
    private onItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(onItemClickListener listener){
        mOnItemClickListener=listener;
    }
    //布局填充
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //itemView
        MyViewHolder holder=new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        //holder.tv.setText(mDatas.get(position));
        // 绑定每一个itemView的事件  抽取方法 供子类复用
       /* List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for(SubjectInfo s:mDatas){
            HashMap<String,Object> item=new HashMap<String,Object>();
            item.put()
        }*/
        SubjectInfo subject = mDatas.get(position);
        holder.tv_source.setText(subject.getSource().toString());
        holder.tv_short_subject.setText(subject.getShort_subject());

        String display_time = subject.getDisplay_time();
//        SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
//        String time = sdf.format(display_time);
        holder.tv_display_time.setText(display_time);

        holder.tv_comment_count.setText(subject.getComment_count());
        holder.tv_share_count.setText(subject.getShare_count());
        String media = subject.getMedia();
        String bitmap_url= Contants.MEDIA_IMAGE_URL+media;
        System.out.println("bitmap_url="+bitmap_url);

        //Picasso
        Picasso.with(mContext)
                .load(bitmap_url)
                .placeholder(R.drawable.pic_home_shadow_big)
                .into(holder.iv_main);
        //下载图片
        /*helper=HttpHelper.getInstance();
        helper.requestByGet(bitmap_url, new HttpHelper.BitmapCallBack() {
            @Override
            public void onFailure(Exception e) {
            }

            @Override
            public void onResult(Object bitmap) {
                Bitmap bt= (Bitmap) bitmap;
                if (bt==null){
                    System.out.println("图片为空");
                }
                holder.iv_main.setImageBitmap(bt);
            }
        });*/

        setItemViewEvent(holder);
    }

    private void setItemViewEvent(final MyViewHolder holder) {
        if (mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int realpos=holder.getLayoutPosition();
                    mOnItemClickListener.OnItemClick(holder.itemView,mDatas.get(realpos).getId());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_source,tv_short_subject,
                tv_display_time,tv_comment_count,tv_share_count;
        ImageView iv_comment,iv_share;
        ImageView iv_main;
        public MyViewHolder(View view) {
            super(view);
            tv_source= (TextView) view.findViewById(R.id.tv_source);
            tv_short_subject= (TextView) view.findViewById(R.id.tv_short_subject);
            tv_display_time= (TextView) view.findViewById(R.id.tv_display_time);
            tv_comment_count= (TextView) view.findViewById(R.id.tv_comment_count);
            tv_share_count= (TextView) view.findViewById(R.id.tv_share_count);

            iv_main = (ImageView) view.findViewById(R.id.iv_main);
            iv_comment= (ImageView) view.findViewById(R.id.iv_comment);
            iv_share= (ImageView) view.findViewById(R.id.iv_share);
        }
    }
}
