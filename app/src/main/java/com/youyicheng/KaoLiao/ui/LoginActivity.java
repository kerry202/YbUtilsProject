package com.youyicheng.KaoLiao.ui;

import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.youyicheng.KaoLiao.R;
import com.youyicheng.KaoLiao.base.BaseActivity;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.http.HttpUtils;
import com.youyicheng.KaoLiao.http.OnDataListener;
import com.youyicheng.KaoLiao.http.RequestState;
import com.youyicheng.KaoLiao.module.RegisterModule;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.ToastUtil;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.next_tv)
    TextView nextTv;
    @BindView(R.id.get_code)
    TextView getCode;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.phone_number)
    EditText phoneNumber;
    @BindView(R.id.code)
    EditText code;

    private int conut = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            conut--;
            if (conut >= 0) {
                getCode.setText("重新发送(" + conut + "s)");
                handler.sendEmptyMessageDelayed(1, 1000);
                getCode.setClickable(false);
            } else {
                conut = 60;
                getCode.setClickable(true);
                getCode.setText("获取验证码");
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.login_layout;
    }

    @Override
    protected void initView() {

        getCode.setClickable(false);
        getCode.setTextColor(activity.getResources().getColor(R.color.gray_c));
        code.setClickable(false);
        code.setTextColor(activity.getResources().getColor(R.color.gray_c));

        phoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 11) {
                    getCode.setClickable(true);
                    getCode.setTextColor(activity.getResources().getColor(R.color.price_red));
                } else {
                    getCode.setClickable(false);
                    getCode.setTextColor(activity.getResources().getColor(R.color.gray_c));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (s.length() == 6) {
                    code.setClickable(true);
                    code.setTextColor(activity.getResources().getColor(R.color.price_red));
                } else {
                    code.setClickable(false);
                    code.setTextColor(activity.getResources().getColor(R.color.gray_c));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    protected void initData() {


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread() {

    }


    @OnClick({R.id.next_tv, R.id.get_code, R.id.login_bt})
    public void onViewClicked(View view) {

        String phone = phoneNumber.getText().toString();
        String code1 = code.getText().toString();
        switch (view.getId()) {
            case R.id.next_tv:
                break;
            case R.id.get_code:

                getCode(phone);

                break;
            case R.id.login_bt:
                login();
                break;
        }
    }

    private void login() {


    }

    private void getCode(String phone) {

        HashMap<String, String> params = new HashMap<>();
        params.put("phone", phone);
        HttpUtils.getInstance().sendRequest(activity, params, RequestState.STATE_DIALOG, MyInterface.SendCode, new OnDataListener() {
            @Override
            public void onSuccess(String data) {
                Logs.s("     注册接口1 onNext  " + data);
                RegisterModule registerModule = new Gson().fromJson(data, RegisterModule.class);
                if (registerModule != null) {
                    if (registerModule.result.equalsIgnoreCase("SUCCESS")) {
                        handler.sendEmptyMessageDelayed(1, 1000);
                    } else {
                        ToastUtil.show(activity, registerModule.message);
                    }
                }
            }

            @Override
            public void onError(String msg) {
                Logs.s("     注册接口1 onNext  " + msg);

            }
        });
    }
}
