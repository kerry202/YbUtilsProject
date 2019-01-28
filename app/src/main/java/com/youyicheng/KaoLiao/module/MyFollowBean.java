package com.youyicheng.KaoLiao.module;

import java.util.List;

public class MyFollowBean {
    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : [{"uid":2,"nickname":"testx","head_img":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqQ4Qib7yhHB83VWMOCia9pic6X8L2ib6Lnsiaw26MzVXe1rcXPlPbV1iagrxQUNROO4GLRKXvxGGvUUlibQ/132","fans":1}]
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "MyFollowBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * uid : 2
         * nickname : testx
         * head_img : https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqQ4Qib7yhHB83VWMOCia9pic6X8L2ib6Lnsiaw26MzVXe1rcXPlPbV1iagrxQUNROO4GLRKXvxGGvUUlibQ/132
         * fans : 1
         */

        public int uid;
        public String nickname;
        public String head_img;
        public int fans;

        @Override
        public String toString() {
            return "DataBean{" +
                    "uid=" + uid +
                    ", nickname='" + nickname + '\'' +
                    ", head_img='" + head_img + '\'' +
                    ", fans=" + fans +
                    '}';
        }
    }
}
