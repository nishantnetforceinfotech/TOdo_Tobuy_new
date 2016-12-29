package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment;

/**
 * Created by abcd on 12/7/2016.
 */
public class ToBuyData {
    public String id,quantity,itemname;

    public ToBuyData(String id, String quantity, String itemname) {
        this.id = id;
        this.quantity = quantity;
        this.itemname = itemname;
    }
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ToBuyData)) {
            return false;
        }

        ToBuyData that = (ToBuyData) obj;
        return this.id.equals(that.id);
    }
}
