package com.netforceinfotech.todo.task.dashboard.mainfragment;


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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.DashBoard.Main_fragment.Fragment_main_grid_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Fragment_tobuy;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.OnStartDragListener;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.SimpleItemTouchHelperCallback;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by owner on 10/13/2016.
 */
public class SubFragment extends Fragment implements OnStartDragListener {

    RecyclerView rl_view;
    LinearLayoutManager linearLayoutManager;
    Subfragment_listAdapter sub_list_adapter;
    private ItemTouchHelper mItemTouchHelper;
    ArrayList<String> list = new ArrayList<>();
    ArrayList<String> list1 = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_dashboard_sub_fragment, container, false);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        rl_view = (RecyclerView) view.findViewById(R.id.recycleview_items);

        for(int i=1;i<19;i++){

            list.add("Item"+i);
            list1.add(i+"");
        }
        sub_list_adapter = new Subfragment_listAdapter(getActivity(),this,list,list1);

        rl_view.setLayoutManager(linearLayoutManager);
        rl_view.setAdapter(sub_list_adapter);
        // Inflate the layout for this fragment

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(sub_list_adapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(rl_view);

        return view;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        //  mItemTouchHelper.startDrag(viewHolder);
        Log.e("hhh", "test");
    }

}
