package com.youyicheng.KaoLiao.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.adapters.AddressAdapter;
import com.youyicheng.KaoLiao.base.BaseActivity;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;

public class SelectAddressActivity extends BaseActivity {
    @BindView(R.id.title_back)
    RelativeLayout titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_send)
    TextView titleSend;
    @BindView(R.id.details_head_rl)
    RelativeLayout detailsHeadRl;
    @BindView(R.id.contact_server_tv)
    TextView contactServerTv;
    @BindView(R.id.address_searsh)
    RelativeLayout addressSearsh;
    @BindView(R.id.address_recycler)
    RecyclerView addressRecycler;

    @Override
    protected int getLayoutId() {
        return R.layout.select_address_layout;
    }

    @Override
    protected void initView() {
        titleSend.setVisibility(View.GONE);
        titleName.setText("选择省份");
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("北京大学");
        arrayList.add("清华大学");
        arrayList.add("北大");
        arrayList.add("北大");
        arrayList.add("北大");
        arrayList.add("北大");
        arrayList.add("北大");
        arrayList.add("北大");
        arrayList.add("北大");
        arrayList.add("北大");
        arrayList.add("北大");
        arrayList.add("北大");
        arrayList.add("北大");
        arrayList.add("北大");


        addressRecycler.setLayoutManager(new LinearLayoutManager(activity));

        AddressAdapter addressAdapter = new AddressAdapter(arrayList, 0);
        addressRecycler.setAdapter(addressAdapter);

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.title_back, R.id.contact_server_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.contact_server_tv:
                break;
        }
    }
}
