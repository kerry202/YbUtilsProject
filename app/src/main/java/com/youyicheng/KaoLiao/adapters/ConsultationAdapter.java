package com.youyicheng.KaoLiao.adapters;

import android.support.annotation.Nullable;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;

import java.util.ArrayList;
import java.util.List;

public class ConsultationAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public ConsultationAdapter(@Nullable List<String> data) {
        super(R.layout.consultation_adapter_layout, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
