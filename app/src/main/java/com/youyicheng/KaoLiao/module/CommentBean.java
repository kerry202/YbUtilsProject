package com.youyicheng.KaoLiao.module;

import java.util.List;

public class CommentBean {
    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 2
     * data : [{"comment_id":3,"fid":0,"uid":3,"nickname":"test3","head_img":"https://wx.qlogo.cn/mmopen/vi_32/GdXayBYoiau8N9uYoMGFicfV4TlIz3fAhr2Kq2MB2L7vK5x8aohTF0vAalXOUq07cFmA41snbvWib1t2Vib4Hxmibmg/132","add_time":"2019-01-22 14:36:14","reply":[]},{"comment_id":1,"fid":0,"uid":3,"nickname":"test3","head_img":"https://wx.qlogo.cn/mmopen/vi_32/GdXayBYoiau8N9uYoMGFicfV4TlIz3fAhr2Kq2MB2L7vK5x8aohTF0vAalXOUq07cFmA41snbvWib1t2Vib4Hxmibmg/132","add_time":"2019-01-21 18:47:53","reply":[{"reply_id":4,"fid":1,"uid":3,"nickname":"test3","head_img":"https://wx.qlogo.cn/mmopen/vi_32/GdXayBYoiau8N9uYoMGFicfV4TlIz3fAhr2Kq2MB2L7vK5x8aohTF0vAalXOUq07cFmA41snbvWib1t2Vib4Hxmibmg/132","add_time":"2019-01-22 14:36:34"},{"reply_id":2,"fid":1,"uid":2,"nickname":"testx","head_img":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqQ4Qib7yhHB83VWMOCia9pic6X8L2ib6Lnsiaw26MzVXe1rcXPlPbV1iagrxQUNROO4GLRKXvxGGvUUlibQ/132","add_time":"2019-01-22 14:36:05"}]}]
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "CommentBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * comment_id : 3
         * fid : 0
         * uid : 3
         * nickname : test3
         * head_img : https://wx.qlogo.cn/mmopen/vi_32/GdXayBYoiau8N9uYoMGFicfV4TlIz3fAhr2Kq2MB2L7vK5x8aohTF0vAalXOUq07cFmA41snbvWib1t2Vib4Hxmibmg/132
         * add_time : 2019-01-22 14:36:14
         * reply : []
         */

        public int comment_id;
        public int fid;
        public int uid;
        public String nickname;
        public String head_img;
        public String add_time;
        public List<?> reply;

        @Override
        public String toString() {
            return "DataBean{" +
                    "comment_id=" + comment_id +
                    ", fid=" + fid +
                    ", uid=" + uid +
                    ", nickname='" + nickname + '\'' +
                    ", head_img='" + head_img + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", reply=" + reply +
                    '}';
        }
    }
}
