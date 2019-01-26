package com.youyicheng.KaoLiao.http;

import android.app.Activity;
import android.os.Bundle;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.youyicheng.KaoLiao.base.OrangeApp;
import com.youyicheng.KaoLiao.config.MyInterface;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class HttpUtils<T> {


    private static HttpUtils sUtils;
    private String BaseUrl = MyInterface.BaseUrl;

    private HttpUtils() {

    }

    public static synchronized HttpUtils getInstance() {
        if (sUtils == null)
            sUtils = new HttpUtils();
        return sUtils;
    }


    public void sendRequest(final Activity activity, HashMap<String, String> params, final RequestState state, String url, final OnDataListener onDataListener) {

        JSONObject jsonObject = new JSONObject(params);
        if (state == RequestState.STATE_DIALOG)
            showDialog(activity);

        OkGo.post(BaseUrl + url).tag(activity)
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        onDataListener.onSuccess(s);

                        if (state == RequestState.STATE_DIALOG)
                            dialog.dismiss();
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        onDataListener.onError(e.getMessage());
                        if (state == RequestState.STATE_DIALOG)
                            dialog.dismiss();
                    }
                });
    }


    private KProgressHUD dialog;

    public void showDialog(Activity activity) {
        try {

            if (dialog == null || !dialog.isShowing()) {
                dialog = KProgressHUD.create(activity)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setCancellable(true)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
            }


        } catch (Exception e) {

        }
    }

}
