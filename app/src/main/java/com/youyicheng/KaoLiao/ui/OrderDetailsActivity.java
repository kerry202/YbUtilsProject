package com.youyicheng.KaoLiao.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.OrderDetailsAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseActivity {
    @BindView(R.id.order_details_back)
    RelativeLayout orderDetailsBack;
    @BindView(R.id.order_details_im)
    ImageView orderDetailsIm;
    @BindView(R.id.order_details_tv1)
    TextView orderDetailsTv1;
    @BindView(R.id.order_details_tv2)
    TextView orderDetailsTv2;
    @BindView(R.id.order_details_tv3)
    TextView orderDetailsTv3;
    @BindView(R.id.order_details_recycer)
    RecyclerView orderDetailsRecycer;

    @Override
    protected int getLayoutId() {
        return R.layout.order_details_layout;
    }

    @Override
    protected void initView() {

        orderDetailsRecycer.setLayoutManager(new LinearLayoutManager(activity));

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("");
        arrayList.add("");
        arrayList.add("");
        OrderDetailsAdapter orderDetailsAdapter = new OrderDetailsAdapter(activity, arrayList);

        orderDetailsRecycer.setAdapter(orderDetailsAdapter);

    }

    @Override
    protected void initData() {

    }

    @OnClick({R.id.order_details_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.order_details_back:
                finish();
                break;

        }
    }
}
