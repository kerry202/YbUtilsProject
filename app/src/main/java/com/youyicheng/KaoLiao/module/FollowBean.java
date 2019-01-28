package com.youyicheng.KaoLiao.module;

import java.util.List;

public class FollowBean {
    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : []
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public List<?> data;

    @Override
    public String toString() {
        return "FollowBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }
}
