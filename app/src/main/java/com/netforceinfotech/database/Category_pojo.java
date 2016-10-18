package com.netforceinfotech.database;

/**
 * Created by abcd on 10/18/2016.
 */
public class Category_pojo {
    String category_name;
    String count;

    public Category_pojo(String category_name, String count) {
        this.category_name = category_name;
        this.count = count;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
