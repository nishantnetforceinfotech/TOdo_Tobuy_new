package com.netforceinfotech.todo_tobuy.DashBoard.navigation;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.netforceinfotech.todo.task.TodoDashboardActivity;
import com.netforceinfotech.todo.task.TodoListFolderActivity;
import com.netforceinfotech.todo_tobuy.All_group_tobuy.All_group_tobuy_Fragment;
import com.netforceinfotech.todo_tobuy.DashBoard.Deshboard;
import com.netforceinfotech.todo_tobuy.DashBoard.navigation.Navigation_items.Contact_us;
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

        holder.main_lin.setTag(position);
        holder.main_lin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences pref1 = context.getApplicationContext().getSharedPreferences("ToDo-ToBuy", 0);
                String chkto = pref1.getString("chkto", "tobuy");

                int pos = (Integer) v.getTag();

                if (data_title[pos].equals("More")) {

                    if (chkto.equals("tobuy")) {

                        Intent in = new Intent(context, TodoDashboardActivity.class);
                        context.startActivity(in);

                    } else {

                        context.startActivity(new Intent(context, Deshboard.class));

                    }

                }

                if (data_title[pos].equals("Contact Us")) {

                    Replace_contact_us();
                    NavigationFragment.mDrawerLayout.closeDrawers();
                   // NavigationFragment.mDrawerToggle.syncState();

                }




            }
        });

    }


    @Override
    public int getItemCount() {
        return data_title.length;
    }



    private void Replace_contact_us() {
        Contact_us f = new Contact_us();
       ((FragmentActivity)context).getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, f, "Replace_contact_us")
                .addToBackStack("Replace_contact_us")
                .commit();


    }
}