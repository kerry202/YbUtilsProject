package com.youyicheng.KaoLiao.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class NoDataActivity extends BaseActivity {

    @BindView(R.id.title_back)
    RelativeLayout titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_send)
    TextView titleSend;
    @BindView(R.id.err_im)
    ImageView errIm;
    @BindView(R.id.err_des)
    TextView errDes;
    @BindView(R.id.err_bt)
    TextView errBt;

    @Override
    protected int getLayoutId() {
        return R.layout.no_data_layout;
    }

    @Override
    protected void initView() {

        titleName.setText("搜索");
        titleSend.setVisibility(View.GONE);
        String state = getIntent().getStringExtra("state");


    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.title_back, R.id.err_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.err_bt:
                break;
        }
    }
}
