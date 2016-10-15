package com.netforceinfotech.todo.task.list.folder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by owner on 10/14/2016.
 */
public class ListSubFragment extends Fragment {

    LinearLayoutManager ln_newmanager,ln_oldmanager;
    public static RecyclerView recycle_new,recycle_old;
    ListSubFragmentNewtaskAdapter newtaskAdapter;
    ListSubFragmentOldtaskAdapter oldtaskAdapter;
    public static ArrayList<NewGroupData> newdata = new ArrayList<>();
    public static ArrayList<OldGroupData> olddata = new ArrayList<>();
    ImageView addtask;
    TextView addnewtask;
    EditText edittaskname;
    RelativeLayout add_rel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.todo_sub_inbox_fragment, container, false);
        ln_newmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ln_oldmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycle_new = (RecyclerView) v.findViewById(R.id.recycleview_newtask);
        recycle_old = (RecyclerView) v.findViewById(R.id.recycleview_oldtask);

        addtask = (ImageView) v.findViewById(R.id.addtask);
        addnewtask = (TextView) v.findViewById(R.id.addnewtask);
        edittaskname = (EditText) v.findViewById(R.id.edittaskname);
        add_rel = (RelativeLayout) v.findViewById(R.id.add_rel);

        addnewtask.setVisibility(View.VISIBLE);
        edittaskname.setVisibility(View.GONE);
        add_rel.setVisibility(View.GONE);

        addtask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addnewtask.setVisibility(View.GONE);
                edittaskname.setVisibility(View.VISIBLE);
                add_rel.setVisibility(View.VISIBLE);
            }
        });

        recycle_new.setLayoutManager(ln_newmanager);
        recycle_old.setLayoutManager(ln_oldmanager);

        newdata.clear();
        olddata.clear();

        for(int i=1;i<11;i++){

            newdata.add(new NewGroupData("task #"+i,"Friday,15 May 2016",false,false));
        }

        for(int i=1;i<20;i++){

            olddata.add(new OldGroupData("task #"+i,"Maonday,25 May 2016",false,false));
        }

        newtaskAdapter = new ListSubFragmentNewtaskAdapter(getActivity(),newdata);
        oldtaskAdapter = new ListSubFragmentOldtaskAdapter(getActivity(),olddata);
        recycle_new.setAdapter(newtaskAdapter);
        recycle_old.setAdapter(oldtaskAdapter);

        add_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ListAddtaskFragment ff = new ListAddtaskFragment();
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.sub_frag, ff, "add_task_fragment")
                        .addToBackStack("addtask")
                        .commit();

            }
        });


        return v;
    }
}
