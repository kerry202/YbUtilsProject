package com.youyicheng.KaoLiao.module;

public class IsSenior {

    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : {"is_senior":false}
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public DataBean data;

    @Override
    public String toString() {
        return "IsSenior{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * is_senior : false
         */

        public boolean is_senior;

        @Override
        public String toString() {
            return "DataBean{" +
                    "is_senior=" + is_senior +
                    '}';
        }
    }
}
