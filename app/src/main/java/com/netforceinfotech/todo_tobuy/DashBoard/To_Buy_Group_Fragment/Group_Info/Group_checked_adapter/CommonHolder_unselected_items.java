package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by abcd on 9/3/2016.
 */
public class CommonHolder_unselected_items extends RecyclerView.ViewHolder {
    //    CardView cardview;
//    ImageView imageview;
    EditText quantity;
    RelativeLayout rl_quantity;
    CheckBox checkBox;
    TextView groupname;
    public CommonHolder_unselected_items(View itemView) {
        super(itemView);
        quantity=(EditText)itemView.findViewById(R.id.editText10);
        rl_quantity=(RelativeLayout)itemView.findViewById(R.id.rl_quantity) ;
        checkBox=(CheckBox)itemView.findViewById(R.id.checkBox3);
        groupname=(TextView)itemView.findViewById(R.id.tv_3);
        // imageview=(ImageView)itemView.findViewById(R.id.imageView14);

        // cardview= (CardView) itemView.findViewById(R.id.cardview_subcategory);
    }
}