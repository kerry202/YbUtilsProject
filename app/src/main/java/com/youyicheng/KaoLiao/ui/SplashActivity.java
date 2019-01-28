package com.youyicheng.KaoLiao.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseActivity;


public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            startActivity(new Intent(activity, MainActivity.class));
            activity.finish();
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.splash_layout;
    }

    @Override
    protected void initView() {
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    @Override
    protected void initData() {

    }
}
