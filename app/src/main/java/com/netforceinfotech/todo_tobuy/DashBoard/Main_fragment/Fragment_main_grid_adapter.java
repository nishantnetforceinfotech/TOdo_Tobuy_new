package com.netforceinfotech.todo_tobuy.DashBoard.Main_fragment;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Fragment_tobuy;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.group_info_fragment;
import com.netforceinfotech.todo_tobuy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ritesh on 22-Aug-16.
 */
public class Fragment_main_grid_adapter extends RecyclerView.Adapter<CommomHolder_grid>implements View.OnClickListener {
    Context context2;
   // ArrayList<CommomData> commomDatas;
FragmentManager fm;
    public Fragment_main_grid_adapter(Context context,FragmentManager fm) {
        context2 = context;
        this.fm=fm;

    }

    @Override
    public CommomHolder_grid onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_grid_row, parent, false);
        CommomHolder_grid viewHolder = new CommomHolder_grid(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommomHolder_grid holder, int position) {
        Picasso.with(context2).load(R.drawable.vegetables).into(holder.imageview);
        holder.imageview.setOnClickListener(this);
        holder.add_or_delete_grp.setOnClickListener(this);
        holder.desription_grp.setOnClickListener(this);
//        holder.cardview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(context, CategoryActivity.class);
////                context.startActivity(intent);
//                ((AppCompatActivity)context2).overridePendingTransition(R.anim.enter, R.anim.exit);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return 18;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.imageView14:
                group_info_fragment f2=new group_info_fragment();


                        fm.beginTransaction()
                                .replace(R.id.container_main, f2,"group_info_fragment")
                        .addToBackStack("group_info_fragment")
                        .commit();
                break;
            case R.id.relativeLayout16:
                Group_Fragment_tobuy f=new Group_Fragment_tobuy();


                fm.beginTransaction()
                        .replace(R.id.container_main, f,"Group_Fragment_tobuy")
                        .addToBackStack("Group_Fragment_tobuy")
                        .commit();







        }

    }
}
