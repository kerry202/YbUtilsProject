package com.youyicheng.KaoLiao.module;

import java.util.List;

public class CommentBean {

    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 6
     * data : [{"comment_id":10,"fid":0,"comment":"testcomment","uid":6,"nickname":"料友42949383","head_img":"https://asd.asd.com/asd.jpg","add_time":"2019-01-28 11:22:22","add_timestamp":1548645742,"likes":0,"is_like":0,"reply":[]},{"comment_id":9,"fid":0,"comment":"testcomment","uid":6,"nickname":"料友42949383","head_img":"https://asd.asd.com/asd.jpg","add_time":"2019-01-28 11:22:02","add_timestamp":1548645722,"likes":1,"is_like":0,"reply":[]},{"comment_id":8,"fid":0,"comment":"testcomment","uid":6,"nickname":"料友42949383","head_img":"https://asd.asd.com/asd.jpg","add_time":"2019-01-28 11:22:01","add_timestamp":1548645721,"likes":0,"is_like":0,"reply":[]},{"comment_id":5,"fid":0,"comment":"testcomment","uid":6,"nickname":"料友42949383","head_img":"https://asd.asd.com/asd.jpg","add_time":"2019-01-28 11:01:08","add_timestamp":1548644468,"likes":0,"is_like":0,"reply":[]},{"comment_id":3,"fid":0,"comment":"33333","uid":3,"nickname":"test3","head_img":"https://wx.qlogo.cn/mmopen/vi_32/GdXayBYoiau8N9uYoMGFicfV4TlIz3fAhr2Kq2MB2L7vK5x8aohTF0vAalXOUq07cFmA41snbvWib1t2Vib4Hxmibmg/132","add_time":"2019-01-22 14:36:14","add_timestamp":1548138974,"likes":0,"is_like":0,"reply":[]},{"comment_id":1,"fid":0,"comment":"111111","uid":3,"nickname":"test3","head_img":"https://wx.qlogo.cn/mmopen/vi_32/GdXayBYoiau8N9uYoMGFicfV4TlIz3fAhr2Kq2MB2L7vK5x8aohTF0vAalXOUq07cFmA41snbvWib1t2Vib4Hxmibmg/132","add_time":"2019-01-21 18:47:53","add_timestamp":1548067673,"likes":0,"is_like":0,"reply":[{"reply_id":4,"fid":1,"comment":"123123123","uid":3,"nickname":"test3（作者）","head_img":"https://wx.qlogo.cn/mmopen/vi_32/GdXayBYoiau8N9uYoMGFicfV4TlIz3fAhr2Kq2MB2L7vK5x8aohTF0vAalXOUq07cFmA41snbvWib1t2Vib4Hxmibmg/132","add_time":"2019-01-22 14:36:34","add_timestamp":1548138994},{"reply_id":2,"fid":1,"comment":"22222","uid":2,"nickname":"testx","head_img":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqQ4Qib7yhHB83VWMOCia9pic6X8L2ib6Lnsiaw26MzVXe1rcXPlPbV1iagrxQUNROO4GLRKXvxGGvUUlibQ/132","add_time":"2019-01-22 14:36:05","add_timestamp":1548138965}]}]
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
         * comment_id : 10
         * fid : 0
         * comment : testcomment
         * uid : 6
         * nickname : 料友42949383
         * head_img : https://asd.asd.com/asd.jpg
         * add_time : 2019-01-28 11:22:22
         * add_timestamp : 1548645742
         * likes : 0
         * is_like : 0
         * reply : []
         */

        public int comment_id;
        public int fid;
        public String comment;
        public int uid;
        public String nickname;
        public String head_img;
        public String add_time;
        public int add_timestamp;
        public int likes;
        public int is_like;
        public List<?> reply;

        @Override
        public String toString() {
            return "DataBean{" +
                    "comment_id=" + comment_id +
                    ", fid=" + fid +
                    ", comment='" + comment + '\'' +
                    ", uid=" + uid +
                    ", nickname='" + nickname + '\'' +
                    ", head_img='" + head_img + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", add_timestamp=" + add_timestamp +
                    ", likes=" + likes +
                    ", is_like=" + is_like +
                    ", reply=" + reply +
                    '}';
        }
    }
}
