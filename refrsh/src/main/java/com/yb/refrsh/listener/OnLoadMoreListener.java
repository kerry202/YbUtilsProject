package com.yb.refrsh.listener;

import android.support.annotation.NonNull;

import com.yb.refrsh.api.RefreshLayout;


/**
 * 加载更多监听器
 */

public interface OnLoadMoreListener {
    void onLoadMore(@NonNull RefreshLayout refreshLayout);
}
