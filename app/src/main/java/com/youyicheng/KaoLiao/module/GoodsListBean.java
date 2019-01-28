package com.youyicheng.KaoLiao.module;

import java.util.List;

public class GoodsListBean {

    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 7
     * data : [{"goods_id":9,"goods_name":"测试商品123","goods_img":"111","intro":null,"price":"0.00","views":23,"comments":0},{"goods_id":1,"goods_name":"测试经验贴","goods_img":"https://img.aaa.com/1,jpg","intro":"这里是介绍，200字以内","price":"0.00","views":2,"comments":0},{"goods_id":2,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0},{"goods_id":3,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0},{"goods_id":4,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0},{"goods_id":5,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0},{"goods_id":6,"goods_name":"测试商品","goods_img":"111","intro":null,"price":"0.00","views":0,"comments":0}]
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "GoodsListBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * goods_id : 9
         * goods_name : 测试商品123
         * goods_img : 111
         * intro : null
         * price : 0.00
         * views : 23
         * comments : 0
         */

        public int goods_id;
        public String goods_name;
        public String goods_img;
        public Object intro;
        public String price;
        public int views;
        public int comments;

        @Override
        public String toString() {
            return "DataBean{" +
                    "goods_id=" + goods_id +
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
