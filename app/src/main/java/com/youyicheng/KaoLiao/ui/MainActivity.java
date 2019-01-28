package com.youyicheng.KaoLiao.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.fragemnt.HomeFragment;
import com.youyicheng.KaoLiao.fragemnt.MeFragment;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.IsSenior;
import com.youyicheng.KaoLiao.module.RegisterModule;
import com.youyicheng.KaoLiao.util.CustomPopWindow;
import com.youyicheng.KaoLiao.util.DialogUtils;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.MyEvents;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.home_tv)
    TextView homeTv;
    @BindView(R.id.send_tv)
    ImageView sendTv;
    @BindView(R.id.me_tv)
    TextView meTv;
    @BindView(R.id.button_ll)
    LinearLayout buttonLl;
    @BindView(R.id.fragment)
    FrameLayout fragment;
    private FragmentManager fragmentManager;

    private HomeFragment homeFragment;
    private MeFragment meFragment;

    private Drawable drawable;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

        homeFragment = new HomeFragment();
        meFragment = new MeFragment();

        fragmentManager = getSupportFragmentManager();


        setHomeIcon();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.add(R.id.fragment, homeFragment);
        ft.add(R.id.fragment, meFragment);

        ft.show(homeFragment)
                .hide(meFragment)
                .commit();

    }

    @Override
    protected void initData() {

    }


    @OnClick({R.id.home_tv, R.id.send_tv, R.id.me_tv})
    public void onViewClicked(View view) {

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (view.getId()) {
            case R.id.home_tv:
                setHomeIcon();
                fragmentTransaction.show(homeFragment)
                        .hide(meFragment)
                        .commit();

                break;
            case R.id.send_tv:

                isSenior();

                break;
            case R.id.me_tv:

                setMeIcon();
                fragmentManager.beginTransaction().show(meFragment)
                        .hide(homeFragment)
                        .commit();
                break;
        }
    }

    private void isSenior() {
        HashMap<String, String> params = new HashMap<>();
        String token = (String) SPUtils.getParam(activity, "token", "");
        params.put("token", token);

        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.IsSenior, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                IsSenior registerModule = new Gson().fromJson(data, IsSenior.class);
                Logs.s("     验证学长 onNext  " + registerModule);
                if (registerModule != null) {
                    if (registerModule.data.is_senior) {
                        showDialog();
                    } else {
                        startActivity(new Intent(activity, ApplyActivity.class));

                    }
                }
            }

            @Override
            public void onError(String msg) {
                Logs.s("     验证学长 onError  " + msg);

            }
        });
    }

    private void showDialog() {

        View view = LayoutInflater.from(this).inflate(R.layout.send_dialog, null);
        final Dialog dialog = new AlertDialog.Builder(this, R.style.home_dialog)
                .setView(view)
                .setCancelable(true)
                .create();
        dialog.show();

        Window win = dialog.getWindow();
        win.setGravity(Gravity.BOTTOM);
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

        dialog.getWindow().setBackgroundDrawable(null);

        win.setAttributes(lp);

        view.findViewById(R.id.cancle_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.experience_home_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(activity, SendExperienceActivity.class));
            }
        });
        view.findViewById(R.id.consultation_home_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(activity, SendConsultationActivity.class));
            }
        });

        view.findViewById(R.id.data_home_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(activity, SendGoodsActivity.class));
            }
        });

    }

    private void setHomeIcon() {

        drawable = getResources().getDrawable(R.mipmap.home_icon);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        homeTv.setCompoundDrawables(null, drawable, null, null);

        drawable = getResources().getDrawable(R.mipmap.me_no_icon);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        meTv.setCompoundDrawables(null, drawable, null, null);

    }

    private void setMeIcon() {

        drawable = getResources().getDrawable(R.mipmap.home_no_icon);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        homeTv.setCompoundDrawables(null, drawable, null, null);

        drawable = getResources().getDrawable(R.mipmap.me_yes_icon);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        meTv.setCompoundDrawables(null, drawable, null, null);

    }


}
