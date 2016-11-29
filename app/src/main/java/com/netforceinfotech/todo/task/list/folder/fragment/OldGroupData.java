package com.netforceinfotech.todo.task.list.folder.fragment;

/**
 * Created by owner on 10/14/2016.
 */
public class OldGroupData {
    String task_name;
    String task_date;
    boolean task_select;
    boolean star_select;

    public OldGroupData(String task_name, String task_date, boolean task_select, boolean star_select) {
        this.task_name = task_name;
        this.task_date = task_date;
        this.task_select = task_select;
        this.star_select = star_select;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_date() {
        return task_date;
    }

    public void setTask_date(String task_date) {
        this.task_date = task_date;
    }

    public boolean isTask_select() {
        return task_select;
    }

    public void setTask_select(boolean task_select) {
        this.task_select = task_select;
    }

    public boolean isStar_select() {
        return star_select;
    }

    public void setStar_select(boolean star_select) {
        this.star_select = star_select;
    }


}

