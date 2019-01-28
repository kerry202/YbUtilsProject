package com.youyicheng.KaoLiao.module;

import java.util.List;

public class SchoolBean {

    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : [{"id":71,"school_name":"中国人民公安大学"}]
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "SchoolBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 71
         * school_name : 中国人民公安大学
         */

        public int id;
        public String school_name;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", school_name='" + school_name + '\'' +
                    '}';
        }
    }
}
