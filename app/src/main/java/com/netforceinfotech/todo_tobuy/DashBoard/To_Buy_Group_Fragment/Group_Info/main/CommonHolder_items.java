package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
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
public class CommonHolder_items  extends RecyclerView.ViewHolder {
//    CardView cardview;
//    ImageView imageview;
TextView quantity;
    RelativeLayout rl_quantity;
    CheckBox checkBox;
    Button button3;
    public CommonHolder_items(View itemView) {
        super(itemView);
        quantity=(TextView)itemView.findViewById(R.id.editText10);
        rl_quantity=(RelativeLayout)itemView.findViewById(R.id.rl_quantity) ;
        checkBox=(CheckBox)itemView.findViewById(R.id.checkBox3);
        button3= (Button) itemView.findViewById(R.id.button3);
       // imageview=(ImageView)itemView.findViewById(R.id.imageView14);

        // cardview= (CardView) itemView.findViewById(R.id.cardview_subcategory);
    }
}