package com.netforceinfotech.todo_tobuy.DashBoard.Offers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by abcd on 9/8/2016.
 */
public class CommonHolder_Recycler_offerlist extends RecyclerView.ViewHolder {
    RecyclerView recyclerView;
    TextView store_name;
    LinearLayout ll_store_name;

    //    CardView cardview;
//    ImageView imageview;


    public CommonHolder_Recycler_offerlist(View itemView) {
        super(itemView);
        store_name=(TextView)itemView.findViewById(R.id.textView19);
        recyclerView=(RecyclerView)itemView.findViewById(R.id.recycleview_allgroup_sub);
        ll_store_name=(LinearLayout)itemView.findViewById(R.id.ll_store_name);


    }
}