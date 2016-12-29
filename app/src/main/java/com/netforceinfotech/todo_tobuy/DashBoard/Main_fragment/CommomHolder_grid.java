package com.netforceinfotech.todo_tobuy.DashBoard.Main_fragment;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.ItemTouchHelperViewHolder;
import com.netforceinfotech.todo_tobuy.R;


/**
 * Created by Ritesh on 22-Aug-16.
 */
public class CommomHolder_grid extends RecyclerView.ViewHolder implements
        ItemTouchHelperViewHolder {
    CardView cardview;
    ImageView imageview;
    RelativeLayout add_or_delete_grp, desription_grp;
    TextView item_count;

    public CommomHolder_grid(View itemView) {
        super(itemView);
        imageview = (ImageView) itemView.findViewById(R.id.imageView14);
        add_or_delete_grp = (RelativeLayout) itemView.findViewById(R.id.relativeLayout16);
        desription_grp = (RelativeLayout) itemView.findViewById(R.id.relativeLayout17);
        item_count = (TextView) itemView.findViewById(R.id.item_count);

        // cardview= (CardView) itemView.findViewById(R.id.cardview_subcategory);
    }

    @Override
    public void onItemSelected() {

        itemView.setBackgroundColor(Color.WHITE);
        Log.e("test", "test");
    }

    @Override
    public void onItemClear() {
        itemView.setBackgroundColor(Color.WHITE);
        Log.e("test1", "test1");
    }
}