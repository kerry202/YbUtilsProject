package com.youyicheng.KaoLiao.adapters;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.OrangeApp;

public class DetailsPagerAdapter extends PagerAdapter {
    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view =View.inflate(OrangeApp.mContext, R.layout.details_pager_adapter_layout,null);
        ImageView pager_img = view.findViewById(R.id.pager_img);
        if (position==0) {
            pager_img.setBackgroundColor(OrangeApp.mContext.getResources().getColor(R.color.price_red));
        }else if (position==1){
            pager_img.setBackgroundColor(OrangeApp.mContext.getResources().getColor(R.color.colorPrimary));
        }else {
            pager_img.setBackgroundColor(OrangeApp.mContext.getResources().getColor(R.color.gray_5));
        }
        container.addView(pager_img);

        return pager_img;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
