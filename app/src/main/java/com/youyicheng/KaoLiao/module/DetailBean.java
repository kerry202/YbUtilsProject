package com.youyicheng.KaoLiao.module;


import java.util.List;

public class DetailBean {
    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : {"goods_id":7,"goods_type":1,"goods_name":"测试商品","goods_img":"111","intro":null,"content":"","price":"0.00","postage_type":0,"uid":2,"status":"0","views":0,"comments":0,"goods_slide":[{"img_url":"111"},{"img_url":"222"}],"senior":{"uid":2,"nickname":"testx","head_img":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqQ4Qib7yhHB83VWMOCia9pic6X8L2ib6Lnsiaw26MzVXe1rcXPlPbV1iagrxQUNROO4GLRKXvxGGvUUlibQ/132","type_id":0,"school_id":253,"major_id":318,"school":"北京理工大学","major":"081200计算机科学与技术","had_exp":1,"had_notice":1,"had_1v1":0,"is_followed":0},"goods_tag":[{"id":0,"type_name":"考研","class":1},{"id":253,"school_name":"北京理工大学","class":2},{"id":318,"major_name":"081200计算机科学与技术","class":3}],"goods":[{"goods_id":9,"goods_type":0,"goods_name":"测试商品123","goods_img":"111","intro":null,"price":"0.00","views":44,"comments":0},{"goods_id":8,"goods_type":1,"goods_name":"测试商品321","goods_img":"111","intro":null,"price":"9.90","views":1,"comments":0},{"goods_id":6,"goods_type":0,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0},{"goods_id":5,"goods_type":0,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0},{"goods_id":4,"goods_type":0,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0}]}
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public DataBean data;

    @Override
    public String toString() {
        return "DetailBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * goods_id : 7
         * goods_type : 1
         * goods_name : 测试商品
         * goods_img : 111
         * intro : null
         * content :
         * price : 0.00
         * postage_type : 0
         * uid : 2
         * status : 0
         * views : 0
         * comments : 0
         * goods_slide : [{"img_url":"111"},{"img_url":"222"}]
         * senior : {"uid":2,"nickname":"testx","head_img":"https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqQ4Qib7yhHB83VWMOCia9pic6X8L2ib6Lnsiaw26MzVXe1rcXPlPbV1iagrxQUNROO4GLRKXvxGGvUUlibQ/132","type_id":0,"school_id":253,"major_id":318,"school":"北京理工大学","major":"081200计算机科学与技术","had_exp":1,"had_notice":1,"had_1v1":0,"is_followed":0}
         * goods_tag : [{"id":0,"type_name":"考研","class":1},{"id":253,"school_name":"北京理工大学","class":2},{"id":318,"major_name":"081200计算机科学与技术","class":3}]
         * goods : [{"goods_id":9,"goods_type":0,"goods_name":"测试商品123","goods_img":"111","intro":null,"price":"0.00","views":44,"comments":0},{"goods_id":8,"goods_type":1,"goods_name":"测试商品321","goods_img":"111","intro":null,"price":"9.90","views":1,"comments":0},{"goods_id":6,"goods_type":0,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0},{"goods_id":5,"goods_type":0,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0},{"goods_id":4,"goods_type":0,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0}]
         */

        public int goods_id;
        public int goods_type;
        public String goods_name;
        public String goods_img;
        public Object intro;
        public String content;
        public String price;
        public int postage_type;
        public int uid;
        public String status;
        public int views;
        public int comments;
        public SeniorBean senior;
        public List<GoodsSlideBean> goods_slide;
        public List<GoodsTagBean> goods_tag;
        public List<GoodsBean> goods;

        @Override
        public String toString() {
            return "DataBean{" +
                    "goods_id=" + goods_id +
                    ", goods_type=" + goods_type +
                    ", goods_name='" + goods_name + '\'' +
                    ", goods_img='" + goods_img + '\'' +
                    ", intro=" + intro +
                    ", content='" + content + '\'' +
                    ", price='" + price + '\'' +
                    ", postage_type=" + postage_type +
                    ", uid=" + uid +
                    ", status='" + status + '\'' +
                    ", views=" + views +
                    ", comments=" + comments +
                    ", senior=" + senior +
                    ", goods_slide=" + goods_slide +
                    ", goods_tag=" + goods_tag +
                    ", goods=" + goods +
                    '}';
        }

        public static class SeniorBean {
            /**
             * uid : 2
             * nickname : testx
             * head_img : https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eqQ4Qib7yhHB83VWMOCia9pic6X8L2ib6Lnsiaw26MzVXe1rcXPlPbV1iagrxQUNROO4GLRKXvxGGvUUlibQ/132
             * type_id : 0
             * school_id : 253
             * major_id : 318
             * school : 北京理工大学
             * major : 081200计算机科学与技术
             * had_exp : 1
             * had_notice : 1
             * had_1v1 : 0
             * is_followed : 0
             */

            public int uid;
            public String nickname;
            public String head_img;
            public int type_id;
            public int school_id;
            public int major_id;
            public String school;
            public String major;
            public int had_exp;
            public int had_notice;
            public int had_1v1;
            public int is_followed;

            @Override
            public String toString() {
                return "SeniorBean{" +
                        "uid=" + uid +
                        ", nickname='" + nickname + '\'' +
                        ", head_img='" + head_img + '\'' +
                        ", type_id=" + type_id +
                        ", school_id=" + school_id +
                        ", major_id=" + major_id +
                        ", school='" + school + '\'' +
                        ", major='" + major + '\'' +
                        ", had_exp=" + had_exp +
                        ", had_notice=" + had_notice +
                        ", had_1v1=" + had_1v1 +
                        ", is_followed=" + is_followed +
                        '}';
            }
        }

        public static class GoodsSlideBean {
            /**
             * img_url : 111
             */

            public String img_url;

            @Override
            public String toString() {
                return "GoodsSlideBean{" +
                        "img_url='" + img_url + '\'' +
                        '}';
            }
        }

        public static class GoodsTagBean {
            /**
             * id : 0
             * type_name : 考研
             * class : 1
             * school_name : 北京理工大学
             * major_name : 081200计算机科学与技术
             */

            public int id;
            public String type_name;
            public int classX;
            public String school_name;
            public String major_name;

            @Override
            public String toString() {
                return "GoodsTagBean{" +
                        "id=" + id +
                        ", type_name='" + type_name + '\'' +
                        ", classX=" + classX +
                        ", school_name='" + school_name + '\'' +
                        ", major_name='" + major_name + '\'' +
                        '}';
            }
        }

        public static class GoodsBean {
            /**
             * goods_id : 9
             * goods_type : 0
             * goods_name : 测试商品123
             * goods_img : 111
             * intro : null
             * price : 0.00
             * views : 44
             * comments : 0
             */

            public int goods_id;
            public int goods_type;
            public String goods_name;
            public String goods_img;
            public Object intro;
            public String price;
            public int views;
            public int comments;

            @Override
            public String toString() {
                return "GoodsBean{" +
                        "goods_id=" + goods_id +
                        ", goods_type=" + goods_type +
                        ", goods_name='" + goods_name + '\'' +
                        ", goods_img='" + goods_img + '\'' +
                        ", intro=" + intro +
                        ", price='" + price + '\'' +
                        ", views=" + views +
                        ", comments=" + comments +
                        '}';
            }
        }
    }
}
