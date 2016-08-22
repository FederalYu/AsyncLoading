package com.yujun.listviewpageloading;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by 于军 on 2016/8/22.
 */
public class LoadListView extends ListView implements AbsListView.OnScrollListener {

    private View footer;
    private int totalItemCount; //总数量
    private int lastVisibleItem; //最后一个可见的item
    private boolean isLoading; //是否正在加载
    private ILoadListener iLoadListener;

    public LoadListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footer = inflater.inflate(R.layout.footer_layout, null);
        footer.findViewById(R.id.load_layout).setVisibility(GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (totalItemCount == lastVisibleItem
                && scrollState == SCROLL_STATE_IDLE) {
            if (!isLoading) {
                isLoading = true;
                footer.findViewById(R.id.load_layout).setVisibility(
                        View.VISIBLE);
                // 加载更多
                iLoadListener.onLoad();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.lastVisibleItem = firstVisibleItem + visibleItemCount;
        this.totalItemCount = totalItemCount;
    }

    //加载完毕
    public void loadComplete() {
        isLoading = false;
        footer.findViewById(R.id.load_layout).setVisibility(
                View.GONE);
    }

    public void setInterface(ILoadListener iLoadListener) {
        this.iLoadListener = iLoadListener;
    }

    //加载更多数据的回调接口
    public interface ILoadListener {
        void onLoad();
    }
}
