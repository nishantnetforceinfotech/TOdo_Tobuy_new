package com.netforceinfotech.todo_tobuy.All_group_tobuy;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.todo_tobuy.All_group_tobuy.main_recycleview.Recycle_adapter_main;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link All_group_tobuy_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class All_group_tobuy_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    //Recycle_adapter_main recycle_adapter_main;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static ArrayList groups_name = new ArrayList<>();
    LinearLayoutManager recycle_allgrp_layoutmanager;
    RecyclerView recyclerView_all_group_main;


    public All_group_tobuy_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment All_group_tobuy_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static All_group_tobuy_Fragment newInstance(String param1, String param2) {
        All_group_tobuy_Fragment fragment = new All_group_tobuy_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_all_group_tobuy_, container, false);
        temp_arraylistdata();

        Initview(v);
        InitRecycleview(v);
        // Inflate the layout for this fragment
        return v;
    }

    private void temp_arraylistdata() {
        groups_name.add("Vegetables");
        groups_name.add("Fruits");
        groups_name.add("Vegetables");
        groups_name.add("Fruits");
        groups_name.add("Vegetables");
        groups_name.add("Fruits");

    }

    private void InitRecycleview(View v) {

        recyclerView_all_group_main = (RecyclerView) v.findViewById(R.id.recycleview_allgroup);
        recycle_allgrp_layoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView_all_group_main.setLayoutManager(recycle_allgrp_layoutmanager);
        // recycle_adapter_main = new Recycle_adapter_main(getActivity(), groups_name);
        //recyclerView_all_group_main.setAdapter(recycle_adapter_main);


    }

    private void Initview(View v) {


    }

}
