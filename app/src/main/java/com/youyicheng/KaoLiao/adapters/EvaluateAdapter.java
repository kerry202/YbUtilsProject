package com.youyicheng.KaoLiao.adapters;

import android.support.annotation.Nullable;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;

import java.util.List;

public class EvaluateAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public EvaluateAdapter(@Nullable List<String> data) {
        super(R.layout.evaluate_adapter_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
