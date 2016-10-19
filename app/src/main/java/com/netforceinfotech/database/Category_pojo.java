package com.netforceinfotech.database;

/**
 * Created by abcd on 10/18/2016.
 */
public class Category_pojo {
    String category_name;
    String count;
    String type;

    public Category_pojo(String category_name, String count,String type) {
        this.category_name = category_name;
        this.count = count;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
