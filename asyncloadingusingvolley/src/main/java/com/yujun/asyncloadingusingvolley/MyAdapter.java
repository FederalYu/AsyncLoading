package com.yujun.asyncloadingusingvolley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by 于军 on 2016/8/21.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<News> mData;
    private LayoutInflater mInflater;

    public MyAdapter(Context context, List<News> data) {
        this.mData = data;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_layout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News news = mData.get(position);

        ImageLoader imageLoader = new ImageLoader(MyApplication.getHttpQueue(), new BitmapCache());
        ImageLoader.ImageListener listener = imageLoader.getImageListener(holder.ivIcon, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(news.getNewsUrl(), listener);
        
        holder.tvTitle.setText(news.getNewsTitle());
        holder.tvContent.setText(news.getNewsContent());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIcon;
        private TextView tvTitle;
        private TextView tvContent;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icon);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            tvContent = (TextView) itemView.findViewById(R.id.tv_content);
        }
    }
}
