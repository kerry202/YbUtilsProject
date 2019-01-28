package com.youyicheng.KaoLiao.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.OrangeApp;
import com.youyicheng.KaoLiao.module.DetailBean;

import java.util.List;

public class DetailsPagerAdapter extends PagerAdapter {
    private List<DetailBean.DataBean.GoodsSlideBean> goods_slide;
    private Activity activity;

    public DetailsPagerAdapter(Activity activity, List<DetailBean.DataBean.GoodsSlideBean> goods_slide) {
        this.goods_slide = goods_slide;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return goods_slide.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = View.inflate(OrangeApp.mContext, R.layout.details_pager_adapter_layout, null);
        ImageView pager_img = view.findViewById(R.id.pager_img);
        Glide.with(activity).load(goods_slide.get(position).img_url)
                .into(pager_img);

        container.addView(pager_img);
        return pager_img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
