package com.youyicheng.KaoLiao.module;

public class SendBean {
    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : {"goods_id":"33","links":4}
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public DataBean data;

    @Override
    public String toString() {
        return "SendBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * goods_id : 33
         * links : 4
         */

        public String goods_id;
        public int links;

        @Override
        public String toString() {
            return "DataBean{" +
                    "goods_id='" + goods_id + '\'' +
                    ", links=" + links +
                    '}';
        }
    }
}
