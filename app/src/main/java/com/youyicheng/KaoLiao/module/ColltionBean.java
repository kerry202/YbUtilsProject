package com.youyicheng.KaoLiao.module;

public class ColltionBean {
    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : {"is_favorite":true}
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public DataBean data;

    @Override
    public String toString() {
        return "ColltionBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * is_favorite : true
         */

        public boolean is_favorite;

        @Override
        public String toString() {
            return "DataBean{" +
                    "is_favorite=" + is_favorite +
                    '}';
        }
    }
}
