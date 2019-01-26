package com.youyicheng.KaoLiao.http;

/**
 * 回调信息接口
 */
public interface OnDataListener{

    public void onSuccess(String data);

    public void onError(String msg);
}
