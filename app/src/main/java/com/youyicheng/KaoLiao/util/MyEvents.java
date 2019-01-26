package com.youyicheng.KaoLiao.util;


import java.util.List;


/**
 * 通知工具
 */
public class MyEvents<T> {


    //信息状态
    public static final int status_empty = 0;
    public static final int status_ok = 1; // 成功
    public static final int status_error = 2; // 网络错误
    public static final int status_msg = 3;//后台信息
    public static final int status_failure = 4;//登录失效
    public static final int status_NoInternet = 10;// 没有网络
    public static final int status_others = 5;//其他

    public static final int status_pass = 6;//传递数据


    //模型
    public int status; //状态
    public String status_type; //类型
    public String errmsg; //说明
    public T something; //数据


}


