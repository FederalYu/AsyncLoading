package com.yujun.asyncloading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 于军 on 2016/5/3.
 */
public class NewsAdapter extends BaseAdapter implements AbsListView.OnScrollListener {

    private List<NewsBean> mList;
    private LayoutInflater mInflater;
    private ImageLoader mImageLoader;
    private int mStart, mEnd;
    public static String[] URLS;
    public boolean mFirstIn;

    public NewsAdapter(Context context, List<NewsBean> list, ListView listView) {
        mList = list;
        mInflater = LayoutInflater.from(context);
        mImageLoader = new ImageLoader(listView);
        URLS = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            URLS[i] = list.get(i).getNewIconUrl();
        }
        mFirstIn = true;
        //一定要注册对应的事件
        listView.setOnScrollListener(this);
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

        //优化一：重用convertView
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
        mImageLoader.showImageByAsyncTask(viewHolder.ivIcon, mList.get(position).getNewIconUrl());
        viewHolder.ivIcon.setTag(mList.get(position).getNewIconUrl());//给图片设置tag解决由listview的缓存机制造成的图片错位
        viewHolder.tvTitle.setText(mList.get(position).getNewTitle());
        viewHolder.tvContent.setText(mList.get(position).getNewsContent());

        return convertView;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            //加载数据
            mImageLoader.loadImage(mStart, mEnd);
        } else {
            //停止任务
            mImageLoader.cancleAllTask();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        mStart = firstVisibleItem;
        mEnd = firstVisibleItem + visibleItemCount;
        if (mFirstIn && visibleItemCount > 0) {
            mImageLoader.loadImage(mStart, mEnd);
            mFirstIn = false;
        }
    }

    //优化二：利用ViewHolder静态类。
    // static定义的静态内部类相对独立，不能访问所在类的方法和实体，占用资源更少。
    // 如果去掉static,可以彼此互相访问实体,会浪费一些资源
    //优化三：利用依赖注入框架精简代码
    private static class ViewHolder {
        public ImageView ivIcon;
        public TextView tvTitle;
        public TextView tvContent;
    }

}
