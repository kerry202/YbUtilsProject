package com.youyicheng.KaoLiao.adapters;

import android.support.annotation.Nullable;
import android.text.Html;
import android.widget.TextView;

import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.OrangeApp;

import java.util.List;

public class AddressAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private int state;
    private String s = "";

    public AddressAdapter(@Nullable List<String> data, int state) {
        super(R.layout.address_adpater_layout, data);
        this.state = state;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {


        TextView address_tv = helper.getView(R.id.address_tv);

        if (state == 1) {
            address_tv.setTextColor(OrangeApp.mContext.getResources().getColor(R.color.gray_666));
            try {
                String replace = item.replace(s, "<font color='#333'>" + s + "</font>");
                address_tv.setText(Html.fromHtml(replace));

            } catch (Exception e) {
            }

        } else {
            address_tv.setText(item);

        }
    }

    public void setText(String s) {
        this.s = s;
    }
}
