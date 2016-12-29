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
import android.widget.ImageView;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_checked_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_recycleview_subfragment;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_unchecked_adapter;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;


public class Group_recycleview_subfragment_main extends Fragment {

    LinearLayoutManager rl_itemlist_layoutmanager;
    public static RecyclerView recycle_itemlist;
    ItemDetailsAdapter item_recycler_adapter;
    public static ArrayList<GroupData> groupDatas = new ArrayList<>();
    private Group_recycleview_subfragment dashboardFragment;
    private String tagName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_group_recycleview_subfragment_main, container, false);
        rl_itemlist_layoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        item_recycler_adapter = new ItemDetailsAdapter(getActivity(), groupDatas);
        initView(v);
        Intializeecycleview(v);

        setDummyData();

        return v;
    }

    private void initView(View v) {

        groupDatas.clear();

        ((ImageView)v.findViewById(R.id.done)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // groupDatas.removeAll(Group_recycleview_subfragment.selectedGroupData);
                Group_recycleview_subfragment.selectedGroupData.clear();
                Group_recycleview_subfragment.unselectedGroupData.clear();

                for (int i = 0; i < groupDatas.size(); i++) {

                    if (!groupDatas.get(i).getQuantity().equals("0") && groupDatas.get(i).isChecked()){

                        Group_recycleview_subfragment.selectedGroupData.add(groupDatas.get(i));

                    }else {

                        Group_recycleview_subfragment.unselectedGroupData.add(groupDatas.get(i));
                    }
                }

                setUpFragment();

            }
        });
    }


    private void setDummyData() {



        for (int i = 0; i < 10; i++) {

            groupDatas.add(new GroupData("name", " ", false, false,false));
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

}
