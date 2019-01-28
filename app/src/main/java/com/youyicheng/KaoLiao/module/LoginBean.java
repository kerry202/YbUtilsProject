package com.youyicheng.KaoLiao.module;


public class LoginBean {


    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : {"token":"evVAbtKO9Hl4qo5FvLyu0OkWixoMP2Ey","reg":false}
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public DataBean data;

    @Override
    public String toString() {
        return "LoginBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * token : evVAbtKO9Hl4qo5FvLyu0OkWixoMP2Ey
         * reg : false
         */

        public String token;
        public boolean reg;

        @Override
        public String toString() {
            return "DataBean{" +
                    "token='" + token + '\'' +
                    ", reg=" + reg +
                    '}';
        }
    }
}
