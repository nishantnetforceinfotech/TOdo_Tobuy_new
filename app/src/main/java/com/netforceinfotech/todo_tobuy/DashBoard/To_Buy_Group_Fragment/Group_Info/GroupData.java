package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info;

/**
 * Created by abcd on 9/3/2016.
 */
public class GroupData {
    public String name, quantity;
    public boolean checked, fav;

    public GroupData(String name, String quantity, boolean checked, boolean fav) {
        this.name = name;
        this.quantity = quantity;
        this.checked = checked;
        this.fav = fav;
    }
}
