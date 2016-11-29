package com.netforceinfotech.todo.task.list.folder.fragment;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.database.Category_pojo;
import com.netforceinfotech.database.DBHelper;
import com.netforceinfotech.database.Task_Pojo;
import com.netforceinfotech.genral.Global_Variable;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by owner on 10/14/2016.
 */
public class ListSubFragmentNewtaskAdapter extends
        RecyclerView.Adapter<ListSubFragmentNewtaskAdapter.ViewHolder> {

    ArrayList<Task_Pojo> groupDatas;
    ArrayList<Category_pojo> category;
    DBHelper db;
    Context context;

    public ListSubFragmentNewtaskAdapter(Context context, ArrayList<Task_Pojo> groupDatas) {
        this.context = context;
        this.groupDatas = groupDatas;
        db = new DBHelper(context);
        category = new ArrayList<>();
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

        Task_Pojo data = groupDatas.get(position);
        holder.task_name.setText(data.getTask_name());
        holder.task_date.setText(data.getTask_date());

        holder.task_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListAddtaskFragment ff = new ListAddtaskFragment();
                FragmentManager manager = ((AppCompatActivity) context).getSupportFragmentManager();
                manager
                        .beginTransaction()
                        .replace(R.id.sub_frag, ff, "add_task_fragment")
                        .addToBackStack("addtask")
                        .commit();
            }
        });

        holder.row_chk.setOnCheckedChangeListener(null);

        holder.row_chk.setChecked(groupDatas.get(position).isChk_selected());

        holder.row_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    groupDatas.get(holder.getAdapterPosition()).setChk_selected(true);
                    holder.task_name.setPaintFlags(holder.task_name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

                } else {

                    groupDatas.get(holder.getAdapterPosition()).setChk_selected(false);
                    holder.task_name.setPaintFlags(Paint.LINEAR_TEXT_FLAG);

                }
            }
        });

        holder.star_chk.setOnCheckedChangeListener(null);

        holder.star_chk.setChecked(groupDatas.get(position).isStar_selected());

        holder.star_chk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    try {

                        int pos = Integer.parseInt(groupDatas.get(holder.getAdapterPosition()).getRow_pos()) + 1;
                        groupDatas.get(holder.getAdapterPosition()).setRow_pos(String.valueOf(pos));
                        db.updateCategoryTask(Global_Variable.category_name, "Urgent", groupDatas.get(holder.getAdapterPosition()).getTask_name(),
                                "true", "", String.valueOf(pos));

                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    try {

                        category = db.getcategory(Global_Variable.category_name, "Urgent");

                        if (!Global_Variable.check_db_foldername) {

                            String category_count = category.get(0).getCount();
                            int a = Integer.parseInt(category_count) + 1;
                            db.updateList(Global_Variable.category_name, "Urgent", String.valueOf(a), Global_Variable.type);
                            Log.e("update", "update");
                        } else {

                            db.addCategory(Global_Variable.category_name, "Urgent", "1", Global_Variable.type);
                            Log.e("insert", "insert");

                        }

                    } catch (Exception e) {

                        e.printStackTrace();

                    } finally {
                        db.close();
                    }

                    groupDatas.get(holder.getAdapterPosition()).setStar_selected(true);

                    int a = holder.getAdapterPosition();
                    groupDatas.add(0, groupDatas.get(holder.getAdapterPosition()));
                    groupDatas.remove(a + 1);
                    notifyDataSetChanged();


                } else {


                    try {

                        groupDatas.get(holder.getAdapterPosition()).setRow_pos("0");
                        //db.deleteTask(Global_Variable.category_name,"Urgent",groupDatas.get(holder.getAdapterPosition()).getTask_name());
                        db.updateCategoryTask(Global_Variable.category_name, "", groupDatas.get(holder.getAdapterPosition()).getTask_name(),
                                "false", "", "0");

                    } catch (Exception e) {

                        e.printStackTrace();
                    }


                    try {
                        category = db.getcategory(Global_Variable.category_name, "Urgent");

                        if (!Global_Variable.check_db_foldername) {

                            String category_count = category.get(0).getCount();
                            int a = Integer.parseInt(category_count) - 1;
                            if (a == 0) {

                                db.deletelist(Global_Variable.category_name,"Urgent");
                            } else {
                                db.updateList(Global_Variable.category_name, "Urgent", String.valueOf(a), Global_Variable.type);
                            }
                        } else {
                            db.addCategory(Global_Variable.category_name, "Urgent", "1", Global_Variable.type);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        db.close();
                    }
                    groupDatas.get(holder.getAdapterPosition()).setStar_selected(false);
                    int a = holder.getAdapterPosition();
                    if(Global_Variable.type.equals("category")) {

                        groupDatas.add(groupDatas.size(), groupDatas.get(holder.getAdapterPosition()));
                    }
                    groupDatas.remove(a);
                    notifyDataSetChanged();
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
        RelativeLayout task_container;

        public ViewHolder(View itemView) {
            super(itemView);

            row_chk = (CheckBox) itemView.findViewById(R.id.checkBox);
            star_chk = (CheckBox) itemView.findViewById(R.id.checkstar);
            task_name = (TextView) itemView.findViewById(R.id.taskname);
            task_date = (TextView) itemView.findViewById(R.id.taskdate);
            task_container = (RelativeLayout) itemView.findViewById(R.id.relright);

        }
    }


}
