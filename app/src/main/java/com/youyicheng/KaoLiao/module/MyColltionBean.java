package com.youyicheng.KaoLiao.module;

import java.util.List;

public class MyColltionBean {

    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 2
     * data : [{"goods_id":9,"goods_type":0,"goods_name":"测试商品123","goods_img":"111","intro":null,"price":"0.00","views":23,"comments":0},{"goods_id":8,"goods_type":1,"goods_name":"测试商品321","goods_img":"111","intro":null,"price":"9.90","views":0,"comments":0}]
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "MyColltionBean{" +
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
         * goods_type : 0
         * goods_name : 测试商品123
         * goods_img : 111
         * intro : null
         * price : 0.00
         * views : 23
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
            return "DataBean{" +
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
