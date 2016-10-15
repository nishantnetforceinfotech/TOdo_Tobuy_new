package com.netforceinfotech.todo.task.dashboard.mainfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.todo.task.TodoListFolderActivity;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.ItemTouchHelperAdapter;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.OnStartDragListener;
import com.netforceinfotech.todo_tobuy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by owner on 10/13/2016.
 */
public class Subfragment_listAdapter extends RecyclerView.Adapter<CommomHolder_list> {
    // implements ItemTouchHelperAdapter {

    Context context2;
    ArrayList<String> commomDatas, commomDatas1;
   // private final OnStartDragListener mDragStartListener;


    public Subfragment_listAdapter(Context context,
                                  // OnStartDragListener dragStartListener,
                                   ArrayList<String> commomDatas, ArrayList<String> commomDatas1) {
        context2 = context;
      //  mDragStartListener = dragStartListener;
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

        holder.main_rel.setTag(position);
       /* holder.main_rel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
*/
        holder.main_rel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int pos = (Integer) v.getTag();
                customDialog(commomDatas.get(pos));
                return true;
            }
        });

        holder.main_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(context2, TodoListFolderActivity.class);
                context2.startActivity(in);
            }
        });
    }

    /*@Override
    public void onItemDismiss(int position) {
        //commomDatas.remove(position);
        //notifyItemRemoved(position);
    }*/

/*
    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(commomDatas, fromPosition, toPosition);
        Collections.swap(commomDatas1, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }
*/

    @Override
    public int getItemCount() {
        return 18;
    }


    public void customDialog(String listname){

        final Dialog dd = new Dialog(context2);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.todo_sub_fragment_dialog);

        TextView list_name = (TextView) dd.findViewById(R.id.list_name);
        list_name.setText(listname);
        RelativeLayout rel2 = (RelativeLayout) dd.findViewById(R.id.rel2);
        RelativeLayout rel3 = (RelativeLayout) dd.findViewById(R.id.rel3);
        RelativeLayout rel4 = (RelativeLayout) dd.findViewById(R.id.rel4);
        RelativeLayout rel5 = (RelativeLayout) dd.findViewById(R.id.rel5);
        RelativeLayout rel6 = (RelativeLayout) dd.findViewById(R.id.rel6);
        RelativeLayout rel7 = (RelativeLayout) dd.findViewById(R.id.rel7);
        RelativeLayout rel9 = (RelativeLayout) dd.findViewById(R.id.rel9);
        dd.show();

        rel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dd.cancel();
            }
        });
    }

}
