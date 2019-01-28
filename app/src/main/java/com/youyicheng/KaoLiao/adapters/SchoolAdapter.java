package com.youyicheng.KaoLiao.adapters;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.module.CityBean;
import com.youyicheng.KaoLiao.module.SchoolBean;

import java.util.List;

public class SchoolAdapter extends BaseQuickAdapter<SchoolBean.DataBean, BaseViewHolder> {
    private int state;
    private String s = "";

    public SchoolAdapter(@Nullable List<SchoolBean.DataBean> dataBeans, int state) {
        super(R.layout.address_adpater_layout, dataBeans);
        this.state = state;
    }

    @Override
    protected void convert(BaseViewHolder helper, SchoolBean.DataBean item) {

        TextView address_tv = helper.getView(R.id.address_tv);
        address_tv.setText(item.school_name);


    }

    public void setText(String s) {
        this.s = s;
    }
}
