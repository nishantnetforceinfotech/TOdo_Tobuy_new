package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by abcd on 9/3/2016.
 */
public class CommonHolder_selected_items extends RecyclerView.ViewHolder {
    //    CardView cardview;
//    ImageView imageview;

    EditText quantity;
    RelativeLayout rl_quantity,rl_delete_item,rl_description_unchecked;
    CheckBox checkBox;
    TextView groupname;
    ImageView delete;
    public CommonHolder_selected_items(View itemView) {
        super(itemView);
        quantity=(EditText)itemView.findViewById(R.id.editText10);
        rl_quantity=(RelativeLayout)itemView.findViewById(R.id.rl_quantity) ;
        checkBox=(CheckBox)itemView.findViewById(R.id.checkBox3);
        groupname=(TextView)itemView.findViewById(R.id.tv_3);
        rl_delete_item=(RelativeLayout)itemView.findViewById(R.id.rl_delete_item);
        delete=(ImageView)itemView.findViewById(R.id.imageView26);
        rl_description_unchecked=(RelativeLayout)itemView.findViewById(R.id.rl_description_unchecked);

        // imageview=(ImageView)itemView.findViewById(R.id.imageView14);

        // cardview= (CardView) itemView.findViewById(R.id.cardview_subcategory);
    }
}