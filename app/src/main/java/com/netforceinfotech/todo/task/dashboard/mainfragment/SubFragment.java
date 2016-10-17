package com.netforceinfotech.todo.task.dashboard.mainfragment;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.todo.task.TodoListFolderActivity;
import com.netforceinfotech.todo.task.list.folder.fragment.ListMainFragment;
import com.netforceinfotech.todo.task.list.folder.fragment.ListSubFragment;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.OnStartDragListener;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.SimpleItemTouchHelperCallback;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by owner on 10/13/2016.
 */
public class SubFragment extends Fragment{ //implements OnStartDragListener {

    RecyclerView rl_view;
    LinearLayoutManager linearLayoutManager;
    Subfragment_listAdapter sub_list_adapter;
    private ItemTouchHelper mItemTouchHelper;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list1 = new ArrayList<>();
    RelativeLayout rl_inbox;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_dashboard_sub_fragment, container, false);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rl_view = (RecyclerView) view.findViewById(R.id.recycleview_items);
        rl_inbox=(RelativeLayout)view.findViewById(R.id.rl_inbox);
        rl_inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), TodoListFolderActivity.class);
                getActivity().startActivity(in);
            }
        });

//        for(int i=1;i<19;i++){
//
//            list.add("Item"+i);
//            list1.add(i+"");
//        }
      list.add("demo");
        list1.add("demo1");

        sub_list_adapter = new Subfragment_listAdapter(getActivity(),list,list1);

        rl_view.setLayoutManager(linearLayoutManager);
        rl_view.setAdapter(sub_list_adapter);
        // Inflate the layout for this fragment

       /* ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(sub_list_adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rl_view);
*/

        ((TextView)view.findViewById(R.id.newlist)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNewListDialog();
            }
        });
        return view;
    }

   /* @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        //  mItemTouchHelper.startDrag(viewHolder);
        Log.e("hhh", "test");
    }
*/

    private void createNewListDialog(){

        final Dialog dd = new Dialog(getActivity());
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.todo_sub_fragment_createnew_list);
        EditText listname = (EditText) dd.findViewById(R.id.listname);
        EditText email = (EditText) dd.findViewById(R.id.email);
        EditText password = (EditText) dd.findViewById(R.id.password);
        ImageView save = (ImageView) dd.findViewById(R.id.save);
        ImageView quit = (ImageView) dd.findViewById(R.id.quit);

        dd.show();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dd.cancel();
            }
        });
    }





}
