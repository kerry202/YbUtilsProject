package com.youyicheng.KaoLiao.adapters;

import android.support.annotation.Nullable;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;

import java.util.List;

public class DetailsAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public DetailsAdapter(@Nullable List<String> data) {
        super(R.layout.details_adapter_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
