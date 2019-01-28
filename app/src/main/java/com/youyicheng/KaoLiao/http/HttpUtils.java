package com.youyicheng.KaoLiao.http;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.request.PostRequest;
import com.youyicheng.KaoLiao.base.OrangeApp;
import com.youyicheng.KaoLiao.config.MyInterface;
import com.youyicheng.KaoLiao.module.Bean;
import com.youyicheng.KaoLiao.module.IsSenior;
import com.youyicheng.KaoLiao.ui.LoginActivity;
import com.youyicheng.KaoLiao.util.Logs;
import com.youyicheng.KaoLiao.util.SPUtils;
import com.youyicheng.KaoLiao.util.ToastUtil;

import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
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


    public void sendRequest(final Activity activity, HashMap<String, Object> params, final RequestState state, String url, final OnDataListener onDataListener) {

        String token = (String) SPUtils.getParam(activity, "token", "");

        params.put("token", token);

        JSONObject jsonObject = new JSONObject(params);
        if (state == RequestState.STATE_DIALOG)
            showDialog(activity);


        Logs.s("   jsonObject   " + jsonObject);
        Logs.s("   myInterface_url   " + url);
        OkGo.post(BaseUrl + url).tag(activity)
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logs.s("     返回数据onSuccess json  " + s);
                        Bean bean = new Gson().fromJson(s, Bean.class);
                        Logs.s("     返回数据onSuccess  bean  " + bean);
                        if (bean.ERROR_NO == 101) {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            try {
                                if (bean.message.length() > 0) {
                                    ToastUtil.show(activity, bean.message);
                                }
                            } catch (Exception e) {

                            }
                        } else {
                            onDataListener.onSuccess(s);
                            if (state == RequestState.STATE_DIALOG)
                                dialog.dismiss();
                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        onDataListener.onError(e.getMessage());
                        if (state == RequestState.STATE_DIALOG)
                            dialog.dismiss();
                    }
                });
    }


    public void sendPhoto(final Activity activity, HashMap<String, byte[]> params, final RequestState state, String url, final OnDataListener onDataListener) {


//        PostRequest post = OkGo.post(BaseUrl + url);
//
//        String reQuestParam = "?";
//
//        for (String param : params.keySet()) {
//            post.params("file", params.get(param));
//            reQuestParam = reQuestParam + param + "=" + params.get(param) + "&";
//        }
//
//
//        post.cacheKey(BaseUrl + url)
//                .isMultipart(true).cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(String s, Call call, Response response) {
//                        onDataListener.onSuccess(s);
//                    }
//
//                    @Override
//                    public void onError(Call call, Response response, Exception e) {
//                        onDataListener.onError(e.getMessage());
//                    }
//                });


        JSONObject jsonObject = new JSONObject(params);
        if (state == RequestState.STATE_DIALOG)
            showDialog(activity);

        Logs.s("   jsonObject   " + jsonObject);

        OkGo.post(BaseUrl + url).tag(activity)
                .upJson(jsonObject)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        Logs.s("     返回数据onSuccess json  " + s);
                        Bean bean = new Gson().fromJson(s, Bean.class);
                        Logs.s("     返回数据onSuccess  bean  " + bean);
                        if (bean.ERROR_NO == 101) {
                            activity.startActivity(new Intent(activity, LoginActivity.class));
                            try {
                                if (bean.message.length() > 0) {
                                    ToastUtil.show(activity, bean.message);
                                }
                            } catch (Exception e) {

                            }
                        } else {
                            onDataListener.onSuccess(s);
                            if (state == RequestState.STATE_DIALOG)
                                dialog.dismiss();
                        }

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
