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
import com.youyicheng.KaoLiao.module.CommentBean;

import org.w3c.dom.Text;

import java.util.List;

public class EvaluateAdapter extends BaseQuickAdapter<CommentBean.DataBean, BaseViewHolder> {
    Activity activity;

    public EvaluateAdapter(Activity activity, @Nullable List<CommentBean.DataBean> data) {
        super(R.layout.evaluate_adapter_layout, data);
        this.activity = activity;
    }

    @Override
    protected void convert(BaseViewHolder helper, CommentBean.DataBean item) {

        ImageView evaluate_user_icon = helper.getView(R.id.evaluate_user_icon);

        Glide.with(activity)
                .load(item.head_img)
                .into(evaluate_user_icon);

        TextView evaluate_user_name = helper.getView(R.id.evaluate_user_name);
        TextView evaluate_user_times = helper.getView(R.id.evaluate_user_times);
        TextView evaluate_user_star = helper.getView(R.id.evaluate_user_star);
        TextView des1 = helper.getView(R.id.des1);
        TextView des2 = helper.getView(R.id.des2);
        TextView click_tv = helper.getView(R.id.click_tv);

        evaluate_user_name.setText(item.nickname);
        evaluate_user_times.setText(item.add_time);
        evaluate_user_star.setText(item.fid + "");


    }
}
