package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info;

/**
 * Created by abcd on 9/3/2016.
 */
public class GroupData {
    String name;
    String quantity;
    boolean checked;
    boolean fav;

    boolean text_chk;

    public GroupData(String name, String quantity, boolean checked, boolean fav,boolean text_chk) {
        this.name = name;
        this.quantity = quantity;
        this.checked = checked;
        this.fav = fav;
        this.text_chk = text_chk;
    }

    public boolean isText_chk() {
        return text_chk;
    }

    public void setText_chk(boolean text_chk) {
        this.text_chk = text_chk;
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
