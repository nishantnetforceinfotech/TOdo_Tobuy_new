package com.netforceinfotech.todo.task.dashboard.mainfragment;

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
public class CommomHolder_list extends RecyclerView.ViewHolder {
      //  implements ItemTouchHelperViewHolder {

        TextView msg;
        TextView msg_num;
        ImageView img;
        RelativeLayout main_rel;

    public CommomHolder_list(View itemView) {
        super(itemView);

        msg = (TextView) itemView.findViewById(R.id.list_name);
        msg_num = (TextView) itemView.findViewById(R.id.msg_num);
        img = (ImageView) itemView.findViewById(R.id.img_icon);
        main_rel = (RelativeLayout) itemView.findViewById(R.id.main_rel);

    }

/*
    @Override
    public void onItemSelected() {

        itemView.setBackgroundColor(Color.parseColor("#E2E0E0"));
        Log.e("test", "test");
    }

    @Override
    public void onItemClear() {
        itemView.setBackgroundColor(Color.parseColor("#E2E0E0"));
        Log.e("test1", "test1");
    }
*/
}