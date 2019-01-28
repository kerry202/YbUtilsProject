package com.youyicheng.KaoLiao.module;

public class MyBean {
    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : {"nickname":"料友42949383","head_img":"https://asd.asd.com/asd.jpg","sign":null}
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public DataBean data;

    @Override
    public String toString() {
        return "MyBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * nickname : 料友42949383
         * head_img : https://asd.asd.com/asd.jpg
         * sign : null
         */

        public String nickname;
        public String head_img;
        public String sign;

        @Override
        public String toString() {
            return "DataBean{" +
                    "nickname='" + nickname + '\'' +
                    ", head_img='" + head_img + '\'' +
                    ", sign=" + sign +
                    '}';
        }
    }
}
