package com.netforceinfotech.todo.task.dashboard.mainfragment;


import android.app.Dialog;
import android.app.DialogFragment;
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

import com.netforceinfotech.database.Category_pojo;
import com.netforceinfotech.database.DBHelper;
import com.netforceinfotech.genral.global_variable;
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
    RelativeLayout rl_inbox;
    ArrayList<Category_pojo> category;
    ArrayList<Category_pojo> category_all;

    DBHelper db;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.todo_dashboard_sub_fragment, container, false);
        category=new ArrayList<>();
        category.clear();
        category_all = new ArrayList<>();
        category_all.clear();
        db=new DBHelper(getActivity());
        db.getcategory_except_inbox();

        init_inbox_data();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rl_view = (RecyclerView) view.findViewById(R.id.recycleview_items);
        rl_inbox=(RelativeLayout)view.findViewById(R.id.rl_inbox);

        rl_inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(), TodoListFolderActivity.class);
                global_variable.category_name = "Inbox";
                getActivity().startActivity(in);
            }
        });


        ((TextView)view.findViewById(R.id.newlist)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createNewListDialog();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        category = db.getcategory("Inbox");

        try {
            category = db.getcategory("Inbox");

            String categoryname = category.get(0).getCategory_name();
            String category_count = category.get(0).getCount();
            TextView inbox_count=(TextView)view.findViewById(R.id.textView23);
            inbox_count.setText(category_count);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        category_all = db.getcategory_except_inbox();
        sub_list_adapter = new Subfragment_listAdapter(getActivity(),category_all);

        rl_view.setLayoutManager(linearLayoutManager);
        rl_view.setAdapter(sub_list_adapter);


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


    private void init_inbox_data() {

        int a=db.getCategoryCount();
        if(a==0)
        {
            db.addCategory("Inbox", null, "0");
        }
        Log.e("count", "" + a);


    }


}
