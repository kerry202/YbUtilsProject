package com.youyicheng.KaoLiao.adapters;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.module.AddressBean;
import com.youyicheng.KaoLiao.module.MajorBean;

import java.util.List;

public class MajorAdapter extends BaseQuickAdapter<MajorBean.DataBean, BaseViewHolder> {
    private int state;
    private String s = "";

    public MajorAdapter(@Nullable List<MajorBean.DataBean> dataBeans, int state) {
        super(R.layout.address_adpater_layout, dataBeans);
        this.state = state;
    }

    @Override
    protected void convert(BaseViewHolder helper, MajorBean.DataBean item) {

        TextView address_tv = helper.getView(R.id.address_tv);
        address_tv.setText(item.major_name);


    }

    public void setText(String s) {
        this.s = s;
    }
}
