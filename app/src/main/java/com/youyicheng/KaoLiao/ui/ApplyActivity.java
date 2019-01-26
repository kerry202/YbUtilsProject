package com.youyicheng.KaoLiao.ui;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.MyEvents;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;

public class ApplyActivity extends BaseActivity {


    @BindView(R.id.title_back)
    RelativeLayout titleBack;
    @BindView(R.id.title_name)
    TextView titleName;
    @BindView(R.id.title_send)
    TextView titleSend;
    @BindView(R.id.details_head_rl)
    RelativeLayout detailsHeadRl;
    @BindView(R.id.input_class_et)
    EditText inputClassEt;
    @BindView(R.id.input_major_et)
    EditText inputMajorEt;
    @BindView(R.id.input_province_et)
    EditText inputProvinceEt;
    @BindView(R.id.input_school_et)
    EditText inputSchoolEt;
    @BindView(R.id.select_yes)
    TextView selectYes;
    @BindView(R.id.select_no)
    TextView selectNo;
    @BindView(R.id.input_rx_time_et)
    EditText inputRxTimeEt;
    @BindView(R.id.upload_zheng_rl)
    RelativeLayout uploadZhengRl;
    @BindView(R.id.upload_fan_rl)
    RelativeLayout uploadFanRl;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();


    @Override
    protected int getLayoutId() {
        return R.layout.apply_layout;
    }

    @Override
    protected void initView() {
        titleSend.setText("提交");
        titleName.setText("学长申请");

    }

    @Override
    protected void initData() {

    }


    @Subscribe
    public void onEvent(MyEvents event) {


        Logs.s("   收到消息:   " + event.errmsg);
    }

    @OnClick({R.id.title_back, R.id.title_send, R.id.select_yes, R.id.select_no, R.id.upload_zheng_rl, R.id.upload_fan_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back:
                finish();
                break;
            case R.id.title_send:
                break;
            case R.id.select_yes:
                break;
            case R.id.select_no:
                break;
            case R.id.upload_zheng_rl:
                break;
            case R.id.upload_fan_rl:
                break;
        }
    }
}
