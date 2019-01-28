package com.youyicheng.KaoLiao.adapters;

import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.OrangeApp;
import com.youyicheng.KaoLiao.module.AddressBean;

import java.util.List;

public class AddressAdapter extends BaseQuickAdapter<AddressBean.DataBean, BaseViewHolder> {
    private int state;
    private String s = "";

    public AddressAdapter(@Nullable List<AddressBean.DataBean> dataBeans, int state) {
        super(R.layout.address_adpater_layout, dataBeans);
        this.state = state;
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressBean.DataBean item) {

        TextView address_tv = helper.getView(R.id.address_tv);
        address_tv.setText(item.category_name);


    }

    public void setText(String s) {
        this.s = s;
    }
}
