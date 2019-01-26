package com.youyicheng.KaoLiao.module;

import java.util.List;

public class RegisterModule {
    /**
     * result : SUCCESS
     * message : 大多数情况下为空
     * pageCount : 总页数
     * data : ["返回数据数组"]
     */

    public String result;
    public String message;
    public String pageCount;
    public List<String> data;

    @Override
    public String toString() {
        return "RegisterModule{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount='" + pageCount + '\'' +
                ", data=" + data +
                '}';
    }
}
