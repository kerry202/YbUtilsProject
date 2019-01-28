package com.youyicheng.KaoLiao.adapters;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.module.DetailBean;
import com.youyicheng.KaoLiao.module.MyColltionBean;

import java.util.ArrayList;
import java.util.List;

public class MyColltionAdapter extends BaseQuickAdapter<MyColltionBean.DataBean, BaseViewHolder> {
    private Activity activity;

    public MyColltionAdapter(Activity activity, @Nullable List<MyColltionBean.DataBean> arrayList) {
        super(R.layout.details_adapter_layout, arrayList);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, MyColltionBean.DataBean item) {

        ImageView view = helper.getView(R.id.iv);
        TextView title = helper.getView(R.id.title);
        TextView des = helper.getView(R.id.des);
        TextView count = helper.getView(R.id.count);
        TextView tag = helper.getView(R.id.tag);

        Glide.with(activity)
                .load(item.goods_img)
                .into(view);


        if (item.goods_type == 0) {
            count.setText(item.views + "");
            tag.setText("经验帖");
            count.setTextColor(activity.getResources().getColor(R.color.gray_999));
        } else if (item.goods_type == 1) {
            count.setText("¥ " + item.price);
            tag.setText("1v1咨询");
            count.setTextColor(activity.getResources().getColor(R.color.price_red));
        } else if (item.goods_type == 2) {
            count.setText(item.price + "");
            tag.setText("备考资料");
            count.setTextColor(activity.getResources().getColor(R.color.price_red));
        }

        title.setText(item.goods_type + "");
        des.setText(item.comments + "");

    }
}
