package com.netforceinfotech.todo_tobuy.DashBoard.navigation;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by abcd on 8/26/2016.
 */
public class Navigation_data_adapter extends RecyclerView.Adapter<CommonHolder_my_navigationdata> {
    Context context;
    String data_title[] = {"Home", "Stores", "Coupons", "Services", "How to Use app", "News", "Setting", "Synchronize",
            "Share app", "Rate Us", "Contact Us", "More", "Logout"};
    int data_images[] = {R.drawable.home_icon, R.drawable.stores_icon, R.drawable.coupan_icon, R.drawable.service_icon,
            R.drawable.howtouse_icon, R.drawable.news_icon, R.drawable.setting_icon,
            R.drawable.sycronize_icon, R.drawable.share_appicon, R.drawable.rate_usicon, R.drawable.contact_usicon,
            R.drawable.more_icon, R.drawable.logout_icon
    };


    public Navigation_data_adapter(Context context) {
        this.context = context;

    }

    @Override
    public CommonHolder_my_navigationdata onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.navigation_data_single_row, parent, false);
        CommonHolder_my_navigationdata viewHolder = new CommonHolder_my_navigationdata(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonHolder_my_navigationdata holder, int position) {
        holder.data_title.setText(data_title[position].toString());
        holder.data_icon.setImageResource(data_images[position]);
//        holder.cardview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(context, CategoryActivity.class);
////                context.startActivity(intent);
//                ((AppCompatActivity)context).overridePendingTransition(R.anim.enter, R.anim.exit);
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return data_title.length;
    }
}