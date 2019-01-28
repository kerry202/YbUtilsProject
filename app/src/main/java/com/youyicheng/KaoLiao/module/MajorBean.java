package com.youyicheng.KaoLiao.module;

import java.util.List;

public class MajorBean {

    /**
     * result : SUCCESS
     * message :  City
     * pageCount : 1
     * dataTotal : 1
     * data : [{"id":72,"major_name":"030600公安学"}]
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "MajorBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 72
         * major_name : 030600公安学
         */

        public int id;
        public String major_name;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", major_name='" + major_name + '\'' +
                    '}';
        }
    }
}
