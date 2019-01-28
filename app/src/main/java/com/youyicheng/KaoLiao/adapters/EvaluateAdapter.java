package com.youyicheng.KaoLiao.adapters;

import android.app.Activity;
import android.support.annotation.Nullable;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.module.CommentBean;

import java.util.List;

public class EvaluateAdapter extends BaseQuickAdapter<CommentBean.DataBean, BaseViewHolder> {
    Activity activity;

    public EvaluateAdapter(Activity activity, @Nullable List<CommentBean.DataBean> data) {
        super(R.layout.evaluate_adapter_layout, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean.DataBean item) {

    }
}
