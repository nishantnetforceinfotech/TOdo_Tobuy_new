package com.netforceinfotech.todo.task.list.folder.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by owner on 10/14/2016.
 */
public class ListSubFragmentNewtaskAdapter extends
        RecyclerView.Adapter<ListSubFragmentNewtaskAdapter.ViewHolder> {

    ArrayList<NewGroupData> groupDatas;
    Context context;

    public ListSubFragmentNewtaskAdapter(Context context, ArrayList<NewGroupData> groupDatas) {
        this.context = context;
        this.groupDatas = groupDatas;
    }

    @Override
    public ListSubFragmentNewtaskAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todo_sub_inbox_fragment_items,
                viewGroup, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        NewGroupData data = groupDatas.get(position);
        holder.task_name.setText(data.getTask_name());
        holder.task_date.setText(data.getTask_date());

        holder.row_chk.setOnCheckedChangeListener(null);

        holder.row_chk.setChecked(groupDatas.get(position).isTask_select());

        holder.row_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    groupDatas.get(holder.getAdapterPosition()).setTask_select(true);
                    holder.task_name.setPaintFlags(holder.task_name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                } else {

                    groupDatas.get(holder.getAdapterPosition()).setTask_select(false);
                    holder.task_name.setPaintFlags(Paint.LINEAR_TEXT_FLAG);

                }
            }
        });

        holder.star_chk.setOnCheckedChangeListener(null);

        holder.star_chk.setChecked(groupDatas.get(position).isStar_select());

        holder.star_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    groupDatas.get(holder.getAdapterPosition()).setStar_select(true);
                    //Collections.rotate(groupDatas.subList(holder.getAdapterPosition(), 0), 1) ;
                    //Collections.swap(groupDatas, holder.getAdapterPosition(), 0);

int a=holder.getAdapterPosition();
                    groupDatas.add(0, groupDatas.get(holder.getAdapterPosition()));
                    groupDatas.remove(a+1);
                    notifyDataSetChanged();


                } else {

                    groupDatas.get(holder.getAdapterPosition()).setStar_select(false);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return groupDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        CheckBox row_chk;
        CheckBox star_chk;
        TextView task_name;
        TextView task_date;

        public ViewHolder(View itemView) {
            super(itemView);

            row_chk = (CheckBox) itemView.findViewById(R.id.checkBox);
            star_chk = (CheckBox) itemView.findViewById(R.id.checkstar);
            task_name = (TextView) itemView.findViewById(R.id.taskname);
            task_date = (TextView) itemView.findViewById(R.id.taskdate);

        }
    }


}
