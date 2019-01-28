package com.youyicheng.KaoLiao.adapters;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.recycler.baseholder.BaseQuickAdapter;
import com.recycler.baseholder.BaseViewHolder;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.module.DetailBean;

import org.w3c.dom.Text;

import java.util.List;

public class DetailsAdapter extends BaseQuickAdapter<DetailBean.DataBean.GoodsBean, BaseViewHolder> {
    private Activity activity;

    public DetailsAdapter(Activity activity, @Nullable List<DetailBean.DataBean.GoodsBean> goods) {
        super(R.layout.details_adapter_layout, goods);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, DetailBean.DataBean.GoodsBean item) {

        ImageView view = helper.getView(R.id.iv);
        TextView title = helper.getView(R.id.title);
        TextView des = helper.getView(R.id.des);
        TextView count = helper.getView(R.id.count);
        TextView tag = helper.getView(R.id.tag);

        Glide.with(activity)
                .load(item.goods_img)
                .into(view);

//        "¥ " + item.price + "/" + item.comments + "分钟"

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
