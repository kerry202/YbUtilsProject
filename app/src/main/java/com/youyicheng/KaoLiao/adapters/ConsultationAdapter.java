package com.youyicheng.KaoLiao.adapters;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.module.GoodsListBean;

import java.util.ArrayList;
import java.util.List;

public class ConsultationAdapter extends BaseQuickAdapter<GoodsListBean.DataBean, BaseViewHolder> {

    private ImageView goods_iv;
    private TextView goods_title;
    private TextView goods_des;
    private TextView goods_tv;
    Activity activity;

    public ConsultationAdapter(Activity activity, @Nullable List<GoodsListBean.DataBean> arrayList) {
        super(R.layout.consultation_adapter_layout, arrayList);
        this.activity = activity;
    }


    @Override
    protected void convert(BaseViewHolder helper, GoodsListBean.DataBean item) {

        goods_iv = helper.getView(R.id.goods_iv);
        goods_title = helper.getView(R.id.goods_title);
        goods_des = helper.getView(R.id.goods_des);
        goods_tv = helper.getView(R.id.goods_tv);

        goods_title.setText(item.goods_name);
        goods_des.setText("" + item.intro);
        goods_tv.setText("¥ " + item.price + "/" + item.comments + "分钟");

        Glide.with(activity)
                .load(item.goods_img)
                .into(goods_iv);


    }
}
