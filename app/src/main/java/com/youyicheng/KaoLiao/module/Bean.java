package com.youyicheng.KaoLiao.module;

public class Bean {
    /**
     * result : ERROR
     * ERROR_NO : -1
     * ERROR_NOTICE : token
     * message : 参数错误！
     */

    public String result;
    public int ERROR_NO;
    public String ERROR_NOTICE;
    public String message;

    @Override
    public String toString() {
        return "IsSenior{" +
                "result='" + result + '\'' +
                ", ERROR_NO=" + ERROR_NO +
                ", ERROR_NOTICE='" + ERROR_NOTICE + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
