package com.netforceinfotech.todo_tobuy.DashBoard.Main_fragment;


import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;


import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Fragment_tobuy;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.group_info_fragment;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.ItemTouchHelperAdapter;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.OnStartDragListener;
import com.netforceinfotech.todo_tobuy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ritesh on 22-Aug-16.
 */
public class Fragment_main_grid_adapter extends RecyclerView.Adapter<CommomHolder_grid>
        implements View.OnClickListener, ItemTouchHelperAdapter {

    Context context2;
    ArrayList<Group_dashboard_datas> commomDatas;
    FragmentManager fm;
    private final OnStartDragListener mDragStartListener;

    public Fragment_main_grid_adapter(Context context,
                                      FragmentManager fm,
                                      OnStartDragListener dragStartListener,ArrayList<Group_dashboard_datas> commomDatas) {
        context2 = context;
        this.fm = fm;
        mDragStartListener = dragStartListener;

       this.commomDatas = commomDatas;
    }

    @Override
    public CommomHolder_grid onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_grid_row, parent, false);
        CommomHolder_grid viewHolder = new CommomHolder_grid(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommomHolder_grid holder, int position) {

        Picasso.with(context2).load(commomDatas.get(position).group_image_url).into(holder.imageview);
        holder.imageview.setOnClickListener(this);
        holder.add_or_delete_grp.setOnClickListener(this);
        holder.desription_grp.setOnClickListener(this);
       holder.item_count.setText(commomDatas.get(position).itemcount);
        holder.groupname.setText(commomDatas.get(position).groupname);
        //Log.e("jj",commomDatas.get(position).itemcount);

        holder.imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });

    }

/*
    @Override
    public void onItemDismiss(int position) {
        //commomDatas.remove(position);
        //notifyItemRemoved(position);
    }
*/

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(commomDatas, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return commomDatas.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView14:
                group_info_fragment f2 = new group_info_fragment();


                fm.beginTransaction()
                        .replace(R.id.container_main, f2, "group_info_fragment")
                        .addToBackStack("group_info_fragment")
                        .commit();
                break;
            case R.id.relativeLayout16:
                Group_Fragment_tobuy f = new Group_Fragment_tobuy();


                fm.beginTransaction()
                        .replace(R.id.container_main, f, "Group_Fragment_tobuy")
                        .addToBackStack("Group_Fragment_tobuy")
                        .commit();


        }

    }
}
