package com.youyicheng.KaoLiao.http;



public enum RequestState {

    //状态分别为：弹框模式 、 刷新、 翻页更多、
    STATE_DIALOG,//弹框模式
    STATE_REFRESH, //刷新
    STATE_LOADMORE, //翻页
    STATE_ALL_SCREEN_AND_DIALOG,//全屏加弹框
    STATE_ALL_SCREEN;//全屏不需要弹框

}
