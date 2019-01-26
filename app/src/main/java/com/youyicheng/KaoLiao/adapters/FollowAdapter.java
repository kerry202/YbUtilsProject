package com.youyicheng.KaoLiao.adapters;

import android.support.annotation.Nullable;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;

import java.util.List;

public class FollowAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public FollowAdapter(@Nullable List<String> data) {
        super(R.layout.follow_adapter_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
