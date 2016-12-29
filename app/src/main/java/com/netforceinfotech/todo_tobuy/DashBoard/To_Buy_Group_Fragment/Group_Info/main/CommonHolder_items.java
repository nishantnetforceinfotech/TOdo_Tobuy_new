package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by abcd on 8/30/2016.
 */
public class CommonHolder_items extends RecyclerView.ViewHolder {

    EditText quantity;
    RelativeLayout rl_quantity;
    CheckBox checkBox;
    Button button3;
    ImageView delete_item,Item_description;

    public CommonHolder_items(View itemView) {
        super(itemView);

        quantity = (EditText) itemView.findViewById(R.id.editText10);
        rl_quantity = (RelativeLayout) itemView.findViewById(R.id.rl_quantity);
        checkBox = (CheckBox) itemView.findViewById(R.id.checkBox3);
        button3 = (Button) itemView.findViewById(R.id.button3);
        delete_item=(ImageView)itemView.findViewById(R.id.delete_item);
        Item_description=(ImageView)itemView.findViewById(R.id.item_info);

    }
}