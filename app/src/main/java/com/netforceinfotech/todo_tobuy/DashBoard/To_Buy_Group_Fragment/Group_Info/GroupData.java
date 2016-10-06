package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info;

/**
 * Created by abcd on 9/3/2016.
 */
public class GroupData {
    String name;
    String quantity;
    boolean checked;
    boolean fav;

    public GroupData(String name, String quantity, boolean checked, boolean fav) {
        this.name = name;
        this.quantity = quantity;
        this.checked = checked;
        this.fav = fav;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }


}
