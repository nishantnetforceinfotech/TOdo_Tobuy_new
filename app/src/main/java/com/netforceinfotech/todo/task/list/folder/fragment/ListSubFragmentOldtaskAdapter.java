package com.netforceinfotech.todo.task.list.folder.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by owner on 10/14/2016.
 */
public class ListSubFragmentOldtaskAdapter  extends
        RecyclerView.Adapter<ListSubFragmentOldtaskAdapter.ViewHolder> {

    ArrayList<OldGroupData> groupDatas;
    Context context;

    public ListSubFragmentOldtaskAdapter(Context context, ArrayList<OldGroupData> groupDatas) {
        this.context = context;
        this.groupDatas = groupDatas;
    }

    @Override
    public ListSubFragmentOldtaskAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.todo_sub_inbox_fragment_items,
                viewGroup, false);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        OldGroupData data = groupDatas.get(position);
        holder.task_name.setText(data.getTask_name());
        holder.task_date.setText(data.getTask_date());

        holder.row_chk.setOnCheckedChangeListener(null);

        holder.row_chk.setChecked(groupDatas.get(position).isTask_select());

        holder.row_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    groupDatas.get(holder.getAdapterPosition()).setTask_select(true);


                } else {

                    groupDatas.get(holder.getAdapterPosition()).setTask_select(false);

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

