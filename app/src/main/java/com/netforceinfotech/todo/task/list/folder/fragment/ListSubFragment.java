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

import com.netforceinfotech.genral.Validation;
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
    ImageView clearlist_checked;
    public static ArrayList<NewGroupData> newdata = new ArrayList<>();
    public static ArrayList<OldGroupData> olddata = new ArrayList<>();
    ImageView addtask;
  //  TextView addnewtask;
    EditText edittaskname;
    RelativeLayout add_rel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.todo_sub_inbox_fragment, container, false);



        ln_newmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ln_oldmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recycle_new = (RecyclerView) v.findViewById(R.id.recycleview_newtask);
        clearlist_checked=(ImageView)v.findViewById(R.id.clearlist);
        recycle_old = (RecyclerView) v.findViewById(R.id.recycleview_oldtask);

        //addtask = (ImageView) v.findViewById(R.id.addtask);
       // addnewtask = (TextView) v.findViewById(R.id.addnewtask);
        edittaskname = (EditText) v.findViewById(R.id.edittaskname);
        add_rel = (RelativeLayout) v.findViewById(R.id.add_rel);

        //.setVisibility(View.VISIBLE);
//        edittaskname.setVisibility(View.GONE);
//        add_rel.setVisibility(View.GONE);

//        addtask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                addnewtask.setVisibility(View.GONE);
////                edittaskname.setVisibility(View.VISIBLE);
////                add_rel.setVisibility(View.VISIBLE);
//            }
//        });

        recycle_new.setLayoutManager(ln_newmanager);
        recycle_old.setLayoutManager(ln_oldmanager);
        newdata.clear();
        olddata.clear();


        if(newdata.size()>0) {


            newtaskAdapter = new ListSubFragmentNewtaskAdapter(getActivity(), newdata);
            oldtaskAdapter = new ListSubFragmentOldtaskAdapter(getActivity(), olddata);
            recycle_new.setAdapter(newtaskAdapter);
            recycle_old.setAdapter(oldtaskAdapter);
        }

        add_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Validation.hasText(edittaskname))

                {
                    if (newdata.size() <= 0) {
                        newdata.add(new NewGroupData(edittaskname.getText().toString(), "Friday,15 May 2016", false, false));
                        newtaskAdapter = new ListSubFragmentNewtaskAdapter(getActivity(), newdata);
                        recycle_new.setAdapter(newtaskAdapter);
                    } else {
                        newdata.add(new NewGroupData(edittaskname.getText().toString(), "Friday,15 May 2016", false, false));
                        newtaskAdapter.notifyDataSetChanged();
                    }


                }

               // olddata.add(new OldGroupData("task #","Maonday,25 May 2016",false,false));
//                ListAddtaskFragment ff = new ListAddtaskFragment();
//                getActivity().getSupportFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.sub_frag, ff, "add_task_fragment")
//                        .addToBackStack("addtask")
//                        .commit();

            }
        });

        clearlist_checked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for (int i = 0; i < newdata.size(); i++)
                {
                    if(newdata.get(i).isTask_select())
                    {
//                        olddata.get(i).setStar_select(false);
//                        olddata.get(i).setTask_date(newdata.get(i).getTask_date());
//                        olddata.get(i).setTask_name(newdata.get(i).getTask_name());
//                        olddata.get(i).setTask_select(false);

                        if(olddata.size()<=0)
                        {olddata.add(new OldGroupData(newdata.get(i).getTask_name(),newdata.get(i).getTask_date(),false,false));
                            oldtaskAdapter = new ListSubFragmentOldtaskAdapter(getActivity(), olddata);
                            recycle_old.setAdapter(oldtaskAdapter);

                        }
                        else{

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
