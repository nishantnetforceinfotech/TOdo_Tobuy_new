package com.netforceinfotech.database;

/**
 * Created by abcd on 10/18/2016.
 */
public class Category_pojo {
    String category_name;
    String count;
    String type;
    String list_name;

    public Category_pojo(String category_name, String count,String type,String list_name) {
        this.category_name = category_name;
        this.count = count;
        this.type = type;
        this.list_name = list_name;
    }


    public String getList_name() {
        return list_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
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
