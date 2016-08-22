package com.yujun.listviewpageloading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

/**
 * Created by 于军 on 2016/8/22.
 */
public class MyAdapter extends BaseAdapter {
    private List<News> mList;
    private LayoutInflater mInflater;

    public MyAdapter(Context context, List<News> list) {
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    public void onDataChange(List<News> list) {
        this.mList = list;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_layout, null);
            viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        News news = mList.get(position);
        //加载图片
        ImageLoader imageLoader = new ImageLoader(MyApplication.getHttpQueue(), new BitmapCache());
        ImageLoader.ImageListener listener = imageLoader.getImageListener(viewHolder.ivIcon, R.mipmap.ic_launcher, R.mipmap.ic_launcher);
        imageLoader.get(news.getNewsUrl(), listener);

        viewHolder.ivIcon.setTag(news.getNewsUrl());//给图片设置tag解决由listview的缓存机制造成的图片错位
        viewHolder.tvTitle.setText(news.getNewsTitle());
        viewHolder.tvContent.setText(news.getNewsContent());

        return convertView;
    }

    private static class ViewHolder {
        public ImageView ivIcon;
        public TextView tvTitle;
        public TextView tvContent;
    }
}
