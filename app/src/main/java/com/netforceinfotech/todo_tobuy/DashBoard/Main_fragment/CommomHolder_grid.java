package com.netforceinfotech.todo_tobuy.DashBoard.Main_fragment;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.netforceinfotech.todo_tobuy.R;


/**
 * Created by Ritesh on 22-Aug-16.
 */
public class CommomHolder_grid extends RecyclerView.ViewHolder {
    CardView cardview;
    ImageView imageview;
    RelativeLayout add_or_delete_grp,desription_grp;
    public CommomHolder_grid(View itemView) {
        super(itemView);
        imageview=(ImageView)itemView.findViewById(R.id.imageView14);
        add_or_delete_grp=(RelativeLayout)itemView.findViewById(R.id.relativeLayout16);
        desription_grp =(RelativeLayout)itemView.findViewById(R.id.relativeLayout17);

       // cardview= (CardView) itemView.findViewById(R.id.cardview_subcategory);
    }
}