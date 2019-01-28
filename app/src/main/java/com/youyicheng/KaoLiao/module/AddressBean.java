package com.youyicheng.KaoLiao.module;

import java.util.List;

public class AddressBean {
    /**
     * result : SUCCESS
     * message :
     * pageCount : 1
     * dataTotal : 1
     * data : [{"id":1,"category_name":"公安学"},{"id":2,"category_name":"军事学专硕"},{"id":3,"category_name":"军事学学硕"},{"id":4,"category_name":"农学专硕"},{"id":5,"category_name":"农学学硕"},{"id":6,"category_name":"医学专硕"},{"id":7,"category_name":"医学学硕"},{"id":8,"category_name":"历史学专硕"},{"id":9,"category_name":"历史学学硕"},{"id":10,"category_name":"哲学学硕"},{"id":11,"category_name":"工学专硕"},{"id":12,"category_name":"工学学硕"},{"id":13,"category_name":"教育学专硕"},{"id":14,"category_name":"教育学学硕"},{"id":15,"category_name":"文学专硕"},{"id":16,"category_name":"文学学硕"},{"id":17,"category_name":"法学专硕"},{"id":18,"category_name":"法学学硕"},{"id":19,"category_name":"理学专硕"},{"id":20,"category_name":"理学学硕"},{"id":21,"category_name":"管理学专硕"},{"id":22,"category_name":"管理学学硕"},{"id":23,"category_name":"经济学专硕"},{"id":24,"category_name":"经济学学硕"},{"id":25,"category_name":"艺术学专硕"},{"id":26,"category_name":"艺术学学硕"}]
     */

    public String result;
    public String message;
    public int pageCount;
    public int dataTotal;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "AddressBean{" +
                "result='" + result + '\'' +
                ", message='" + message + '\'' +
                ", pageCount=" + pageCount +
                ", dataTotal=" + dataTotal +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * id : 1
         * category_name : 公安学
         */

        public int id;
        public String category_name;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", category_name='" + category_name + '\'' +
                    '}';
        }
    }
}
