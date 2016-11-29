package com.netforceinfotech.database;

/**
 * Created by owner on 10/19/2016.
 */
public class Task_Pojo {

    String category_name;
    String list_name;
    String task_name;
    String task_selected;
    String task_date;
    boolean chk_selected;
    boolean star_selected;
    String row_pos;

    public Task_Pojo(String category_name,String list_name, String task_name,
                     String task_selected, String task_date,
                     boolean chk,boolean star,String row_pos) {

        this.category_name = category_name;
        this.list_name = list_name;
        this.task_name = task_name;
        this.task_selected = task_selected;
        this.task_date = task_date;
        chk_selected = chk;
        star_selected = star;
        this.row_pos = row_pos;
    }

    public String getRow_pos() {
        return row_pos;
    }

    public void setRow_pos(String row_pos) {
        this.row_pos = row_pos;
    }

    public boolean isStar_selected() {
        return star_selected;
    }

    public void setStar_selected(boolean star_selected) {
        this.star_selected = star_selected;
    }

    public boolean isChk_selected() {
        return chk_selected;
    }

    public void setChk_selected(boolean chk_selected) {
        this.chk_selected = chk_selected;
    }


    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getList_name() {
        return category_name;
    }

    public void setList_name(String list_name) {
        this.list_name = list_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_selected() {
        return task_selected;
    }

    public void setTask_selected(String task_selected) {
        this.task_selected = task_selected;
    }

    public String getTask_date() {
        return task_date;
    }

    public void setTask_date(String task_date) {
        this.task_date = task_date;
    }



}
