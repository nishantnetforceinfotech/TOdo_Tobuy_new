package com.netforceinfotech.todo.task.dashboard.mainfragment;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.ItemTouchHelperAdapter;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.OnStartDragListener;
import com.netforceinfotech.todo_tobuy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by owner on 10/13/2016.
 */
public class Subfragment_listAdapter extends RecyclerView.Adapter<CommomHolder_list>
        implements ItemTouchHelperAdapter {

    Context context2;
    ArrayList<String> commomDatas,commomDatas1;
    private final OnStartDragListener mDragStartListener;


    public Subfragment_listAdapter(Context context,
                                      OnStartDragListener dragStartListener,
                                   ArrayList<String> commomDatas,ArrayList<String> commomDatas1) {
        context2 = context;
        mDragStartListener = dragStartListener;
        this.commomDatas = commomDatas;
        this.commomDatas1 = commomDatas1;
    }

    @Override
    public CommomHolder_list onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_dashboard_sub_fragment_items, parent, false);
        CommomHolder_list viewHolder = new CommomHolder_list(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommomHolder_list holder, int position) {

        Picasso.with(context2).load(R.drawable.vegetables).into(holder.img);
        holder.msg.setText(commomDatas.get(position));
        holder.msg_num.setText(commomDatas1.get(position));

        holder.main_rel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });

    }

    @Override
    public void onItemDismiss(int position) {
        //commomDatas.remove(position);
        //notifyItemRemoved(position);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(commomDatas, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public int getItemCount() {
        return 18;
    }



}
