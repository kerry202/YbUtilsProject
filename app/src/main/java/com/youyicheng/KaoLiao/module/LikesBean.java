package com.youyicheng.KaoLiao.module;

public class LikesBean {
    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : {"is_likes":false}
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public DataBean data;

    @Override
    public String toString() {
        return "LikesBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * is_likes : false
         */

        public boolean is_likes;

        @Override
        public String toString() {
            return "DataBean{" +
                    "is_likes=" + is_likes +
                    '}';
        }
    }
}
