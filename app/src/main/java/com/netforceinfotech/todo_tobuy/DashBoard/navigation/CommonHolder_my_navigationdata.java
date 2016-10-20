package com.netforceinfotech.todo_tobuy.DashBoard.navigation;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by abcd on 8/27/2016.
 */
public class CommonHolder_my_navigationdata extends RecyclerView.ViewHolder {
    TextView data_title;
    ImageView data_icon;
    LinearLayout main_lin;

    public CommonHolder_my_navigationdata(View itemView) {
        super(itemView);
        data_title = (TextView) itemView.findViewById(R.id.textView8);
        data_icon = (ImageView) itemView.findViewById(R.id.imageView6);
        main_lin = (LinearLayout) itemView.findViewById(R.id.main_lin);
    }
}