package com.youyicheng.KaoLiao.module;

import java.util.List;

public class CityBean {
    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : [{"id":17,"city_name":"江苏"},{"id":23,"city_name":"湖北"},{"id":31,"city_name":"重庆"}]
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "CityBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 17
         * city_name : 江苏
         */

        public int id;
        public String city_name;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", city_name='" + city_name + '\'' +
                    '}';
        }
    }
}
