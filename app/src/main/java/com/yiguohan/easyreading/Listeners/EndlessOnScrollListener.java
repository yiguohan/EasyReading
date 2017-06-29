package com.yiguohan.easyreading.Listeners;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 上拉加载更多监听器
 * Created by yiguohan on 2017/6/29.
 */

public abstract class EndlessOnScrollListener extends RecyclerView.OnScrollListener {


    private LinearLayoutManager mLinearLayoutManager;

    private int totalItemCount;//显示的总个数

    private int previousTotal = 0;//加载之前的总个数记载

    private int visiableItemCount;//屏幕中显示的个数

    private int firstVisibleItem;//当前屏幕中第一个项目的位置

    private boolean isLoading = true;//是否正在加载

    public EndlessOnScrollListener(LinearLayoutManager mLinearLayoutManager) {
        this.mLinearLayoutManager = mLinearLayoutManager;
    }

    /**
     * 当屏幕滑动时回调的方法
     *
     * @param recyclerView 被滑动的RecyclerView对象
     * @param dx           被滑动的RecyclerView对象水平移动的距离
     * @param dy           被滑动的RecyclerView对象竖直移动的距离
     */
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visiableItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (isLoading) {
            if (totalItemCount > previousTotal) {
                isLoading = false;
                previousTotal = totalItemCount;
            }
        }

        if (!isLoading && totalItemCount - visiableItemCount <= firstVisibleItem){
            isLoading = true;
            onLoadMore();
        }

    }

    /**
     * 加载数据的抽象方法
     */
    public abstract void onLoadMore();
}
