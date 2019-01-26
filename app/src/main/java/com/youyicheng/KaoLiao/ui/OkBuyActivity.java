package com.youyicheng.KaoLiao.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OkBuyActivity extends BaseActivity {
    @BindView(R.id.okbuy_back)
    RelativeLayout okbuyBack;
    @BindView(R.id.details_head_rl)
    RelativeLayout detailsHeadRl;
    @BindView(R.id.okbuy_icon)
    ImageView okbuyIcon;
    @BindView(R.id.okbuy_reduce)
    ImageView okbuyReduce;
    @BindView(R.id.okbuy_add)
    ImageView okbuyAdd;
    @BindView(R.id.go_pay)
    TextView goPay;

    @Override
    protected int getLayoutId() {
        return R.layout.okbuy_layout;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.okbuy_back, R.id.okbuy_reduce, R.id.address_tv, R.id.okbuy_add, R.id.go_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.okbuy_back:
                finish();
                break;
            case R.id.okbuy_reduce:
                break;
            case R.id.okbuy_add:
                break;
            case R.id.go_pay:
                break;

            case R.id.address_tv:

                startActivity(new Intent(activity,SelectAddressActivity.class));

                break;
        }
    }
}
