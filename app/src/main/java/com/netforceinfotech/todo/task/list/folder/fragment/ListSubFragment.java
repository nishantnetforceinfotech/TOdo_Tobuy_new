package com.netforceinfotech.todo.task.list.folder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.netforceinfotech.database.Category_pojo;
import com.netforceinfotech.database.DBHelper;
import com.netforceinfotech.database.Task_Pojo;
import com.netforceinfotech.genral.Validation;
import com.netforceinfotech.genral.Global_Variable;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by owner on 10/14/2016.
 */
public class ListSubFragment extends Fragment {

    public static RecyclerView recycle_new, recycle_old;
    ListSubFragmentNewtaskAdapter newtaskAdapter;
    ListSubFragmentOldtaskAdapter oldtaskAdapter;
    ImageView clearlist_checked;
    public static ArrayList<NewGroupData> newdata = new ArrayList<>();
    public static ArrayList<OldGroupData> olddata = new ArrayList<>();

    public static ArrayList<Task_Pojo> taskpojo = new ArrayList<>();

    EditText edittaskname;
    RelativeLayout add_rel;
    DBHelper db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.todo_sub_inbox_fragment, container, false);
        db = new DBHelper(getActivity());


        LinearLayoutManager ln_newmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        LinearLayoutManager ln_oldmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycle_new = (RecyclerView) v.findViewById(R.id.recycleview_newtask);
        clearlist_checked = (ImageView) v.findViewById(R.id.clearlist);
        recycle_old = (RecyclerView) v.findViewById(R.id.recycleview_oldtask);
        edittaskname = (EditText) v.findViewById(R.id.edittaskname);
        add_rel = (RelativeLayout) v.findViewById(R.id.add_rel);

        recycle_new.setLayoutManager(ln_newmanager);
        recycle_old.setLayoutManager(ln_oldmanager);

        taskpojo.clear();
        //Check database and get task from database

        if (Global_Variable.type.equals("category")) {

            Log.e("if","if");
            taskpojo = db.getCategoryTask(Global_Variable.category_name);
            if (taskpojo != null && taskpojo.size() > 0) {

                Global_Variable.row_pos_count = taskpojo.get(0).getRow_pos();
                for (int i = 0; i < taskpojo.size(); i++) {

                    if (taskpojo.get(i).getTask_selected().equals("true")) {
                        taskpojo.get(i).setStar_selected(true);
                    } else {
                        taskpojo.get(i).setStar_selected(false);

                    }
                }
                newtaskAdapter = new ListSubFragmentNewtaskAdapter(getActivity(), taskpojo);
                recycle_new.setAdapter(newtaskAdapter);
            }

        } else {

            Log.e("else","else");
            taskpojo = db.getListTask(Global_Variable.category_name, Global_Variable.listname);
            if (taskpojo != null && taskpojo.size() > 0) {

                Global_Variable.row_pos_count = taskpojo.get(0).getRow_pos();
                for (int i = 0; i < taskpojo.size(); i++) {

                    if (taskpojo.get(i).getTask_selected().equals("true")) {
                        taskpojo.get(i).setStar_selected(true);
                    } else {
                        taskpojo.get(i).setStar_selected(false);

                    }
                }
                newtaskAdapter = new ListSubFragmentNewtaskAdapter(getActivity(), taskpojo);
                recycle_new.setAdapter(newtaskAdapter);
            }
        }


       /* if (newdata.size() > 0) {


            newtaskAdapter = new ListSubFragmentNewtaskAdapter(getActivity(), newdata);
            oldtaskAdapter = new ListSubFragmentOldtaskAdapter(getActivity(), olddata);
            recycle_new.setAdapter(newtaskAdapter);
            recycle_old.setAdapter(oldtaskAdapter);
        }
*/
        add_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Validation.hasText(edittaskname))

                {

                    if (taskpojo.size() <= 0) {

                        taskpojo.add(new Task_Pojo(Global_Variable.category_name, Global_Variable.listname,
                                edittaskname.getText().toString(), "false", "", false, false, "0"));

                        newtaskAdapter = new ListSubFragmentNewtaskAdapter(getActivity(), taskpojo);
                        recycle_new.setAdapter(newtaskAdapter);

                    } else {

                        taskpojo.add(new Task_Pojo(Global_Variable.category_name, Global_Variable.listname,
                                edittaskname.getText().toString(), "false", "", false, false, "0"));
                        newtaskAdapter.notifyDataSetChanged();
                    }


                    try {

                        db.addTask(Global_Variable.category_name, Global_Variable.listname,
                                edittaskname.getText().toString(), "false", "", "0");

                    } catch (Exception e) {

                        e.printStackTrace();
                    }

                    ArrayList<Category_pojo> category = new ArrayList<Category_pojo>();

                    try {

                        if (Global_Variable.listname.equals("") && !Global_Variable.category_name.equals("")) {

                            category = db.getcategoryInbox(Global_Variable.category_name);

                            String category_count = category.get(0).getCount();
                            db.updateCategory(Global_Variable.category_name, Global_Variable.listname,
                                    String.valueOf(Integer.parseInt(category_count) + 1), Global_Variable.type);

                        } else {

                            //add stuff here for list
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        db.close();
                    }

                }


            }
        });

        clearlist_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < newdata.size(); i++) {
                    if (newdata.get(i).isTask_select()) {

                        if (olddata.size() <= 0) {
                            olddata.add(new OldGroupData(newdata.get(i).getTask_name(), newdata.get(i).getTask_date(), false, false));
                            oldtaskAdapter = new ListSubFragmentOldtaskAdapter(getActivity(), olddata);
                            recycle_old.setAdapter(oldtaskAdapter);

                        } else {

                            olddata.get(i).setStar_select(false);
                            olddata.get(i).setTask_date(newdata.get(i).getTask_date());
                            olddata.get(i).setTask_name(newdata.get(i).getTask_name());
                            olddata.get(i).setTask_select(false);

                            oldtaskAdapter.notifyDataSetChanged();
                        }
                        newdata.remove(i);
                        newtaskAdapter.notifyDataSetChanged();

                    }

                }
            }
        });
        return v;
    }

}
