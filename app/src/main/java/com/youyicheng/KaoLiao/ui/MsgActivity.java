package com.youyicheng.KaoLiao.ui;


import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.util.MyEvents;
import org.greenrobot.eventbus.EventBus;
import butterknife.BindView;
import butterknife.OnClick;

public class MsgActivity extends BaseActivity {


    @BindView(R.id.msg_back)
    RelativeLayout msgBack;
    @BindView(R.id.msg_head_rl)
    RelativeLayout msgHeadRl;
    @BindView(R.id.user_photo)
    ImageView userPhoto;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.user_flag)
    TextView userFlag;
    @BindView(R.id.msg_photo)
    ImageView msgPhoto;

    @Override
    protected int getLayoutId() {
        return R.layout.msg_layout;
    }

    @Override
    protected void initView() {

        //测试 eventBus
        MyEvents<Object> objectMyEvents = new MyEvents<>();
        objectMyEvents.errmsg = "错误消息";
        EventBus.getDefault().post(objectMyEvents);
        //TODO

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.msg_back, R.id.msg_head_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.msg_back:
                finish();
                break;
            case R.id.msg_head_rl:
                break;
        }
    }
}
