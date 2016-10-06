package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main.Item_recycler_adapter;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

public class Group_recycleview_subfragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayoutManager rl_itemlist_layoutmanager, rl_itemlist_layoutmanager2;
    public static RecyclerView recycle_selected_itemlist, recycle_unselected_itemlist;
    public static Group_unchecked_adapter grp_uncheck_adapter;
    public static Group_checked_adapter grp_checked_adapter;
    public static ArrayList<GroupData> selectedGroupData = new ArrayList<>();
    public static ArrayList<GroupData> unselectedGroupData = new ArrayList<>();

    public Group_recycleview_subfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_group_recycleview_subfragment, container, false);
        rl_itemlist_layoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rl_itemlist_layoutmanager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        //item_recycler_adapter = new Item_recycler_adapter(getActivity(), unselectedGroupData);
        grp_uncheck_adapter=new Group_unchecked_adapter(getActivity(),unselectedGroupData);
        grp_checked_adapter = new Group_checked_adapter(getActivity(), selectedGroupData);
        initView(v);
        Intializeecycleview(v);
        // Inflate the layout for this fragment

        Log.e("hhh",selectedGroupData.size()+"");
        Log.e("ddd",unselectedGroupData.size()+"");
        return v;
    }

    private void initView(View v) {
       // v.findViewById(R.id.done).setOnClickListener(this);
       // v.findViewById(R.id.clearlist).setOnClickListener(this);

    }


    private void Intializeecycleview(View v) {
        recycle_selected_itemlist = (RecyclerView) v.findViewById(R.id.recycleview_seleted_items);
        recycle_selected_itemlist.setLayoutManager(rl_itemlist_layoutmanager);
        recycle_selected_itemlist.setAdapter(grp_checked_adapter);
        //recycle_unselected_itemlist
        recycle_unselected_itemlist = (RecyclerView) v.findViewById(R.id.recycleview_unseleted_items);
        recycle_unselected_itemlist.setLayoutManager(rl_itemlist_layoutmanager2);
        recycle_unselected_itemlist.setAdapter(grp_uncheck_adapter);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
/*
            case R.id.imageView22:
               break;
            case R.id.imageView23:

                for(int i=0;i<selectedGroupData.size();i++){
                    if(!selectedGroupData.get(i).checked){

                        unselectedGroupData.add(selectedGroupData.get(i));
                        selectedGroupData.remove(i);
                    }
                }
                grp_checked_adapter.notifyDataSetChanged();
                grp_uncheck_adapter.notifyDataSetChanged();
//                for(int i=0;i<unselectedGroupData.size();i++){
//                    if(!unselectedGroupData.get(i).checked){
//                        unselectedGroupData.remove(i);
//                    }
//                }
//                grp_uncheck_adapter.notifyDataSetChanged();

                break;
*/
        }
    }


}
