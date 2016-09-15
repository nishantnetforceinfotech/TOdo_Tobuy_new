package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_checked_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_recycleview_subfragment;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_unchecked_adapter;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;


public class Group_recycleview_subfragment_main extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LinearLayoutManager rl_itemlist_layoutmanager;
    public static RecyclerView recycle_itemlist;
    Item_recycler_adapter item_recycler_adapter;
    Group_checked_adapter gp_checked;
    Group_unchecked_adapter gp_unchecked;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    Activity activity ;
    public static ArrayList<GroupData> groupDatas = new ArrayList<>();
    private Group_recycleview_subfragment dashboardFragment;
    private String tagName;


    public Group_recycleview_subfragment_main() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_group_recycleview_subfragment_main, container, false);
        rl_itemlist_layoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        item_recycler_adapter = new Item_recycler_adapter(getActivity(), groupDatas);
        initView(v);
        Intializeecycleview(v);

        setDummyData();
        // Inflate the layout for this fragment
        return v;
    }

    private void initView(View v) {
        v.findViewById(R.id.imageView22).setOnClickListener(this);
        v.findViewById(R.id.imageView23).setOnClickListener(this);
    }

    private void setDummyData() {
        for (int i = 0; i < 10; i++) {
            //GroupData(String name,String quantity,boolean checked,boolean fav){
            groupDatas.add(new GroupData("name", "5", false, false));
        }
        item_recycler_adapter.notifyDataSetChanged();
    }


    private void Intializeecycleview(View v) {
        recycle_itemlist = (RecyclerView) v.findViewById(R.id.recycleview_items);
        recycle_itemlist.setLayoutManager(rl_itemlist_layoutmanager);
        recycle_itemlist.setAdapter(item_recycler_adapter);

    }

    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_groupdata, newFragment, tag);
        transaction.commit();
    }

    private void setUpFragment() {
        dashboardFragment = new Group_recycleview_subfragment();
        tagName = dashboardFragment.getClass().getName();
        replaceFragment(dashboardFragment, tagName);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView22:

                groupDatas.removeAll(Group_recycleview_subfragment.selectedGroupData);
               Log.e("groupDatas.remove",Group_recycleview_subfragment.selectedGroupData.get(0).toString());
                Group_recycleview_subfragment.unselectedGroupData=groupDatas;

                item_recycler_adapter.notifyDataSetChanged();

                setUpFragment();
                break;
        }
    }
}
