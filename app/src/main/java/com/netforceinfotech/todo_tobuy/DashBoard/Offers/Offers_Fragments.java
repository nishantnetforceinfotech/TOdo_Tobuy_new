package com.netforceinfotech.todo_tobuy.DashBoard.Offers;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

import okhttp3.internal.http.StreamAllocation;


public class Offers_Fragments extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    RecyclerView rl_store_list;
    LinearLayoutManager rl_itemlist_layoutmanager;
    public static LinearLayout store_container,storemain_container;
    ArrayList stores_name=new ArrayList();
    ArrayList Services=new ArrayList();
    TextView Heading;
    public static  ImageView crossButton;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ImageView store,coupons,service;


    public Offers_Fragments() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Offers_Fragments.
     */
    // TODO: Rename and change types and number of parameters
    public static Offers_Fragments newInstance(String param1, String param2) {
        Offers_Fragments fragment = new Offers_Fragments();
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
        View v=   inflater.inflate(R.layout.fragment_offers, container, false);
        InitArraylist();

        rl_itemlist_layoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        InitRecycleview(v);
        initview(v);
        // Inflate the layout for this fragment
        return v;
    }

    private void InitArraylist() {

        stores_name.add("HEB");
        stores_name.add("MODE");
        stores_name.add("S-MART");
        stores_name.add("Soriana");
        stores_name.add("Wallmart");


        Services.add("Beauty");
        Services.add("Class");
        Services.add("Dance");
        Services.add("Dentist");
        Services.add("Doctor");



    }

    private void initview(View v) {
        Heading=(TextView)v.findViewById(R.id.textView18);
        store=(ImageView)v.findViewById(R.id.imageView36);
        store.setOnClickListener(this);
        coupons=(ImageView)v.findViewById(R.id.imageView37);
        coupons.setOnClickListener(this);
        service=(ImageView)v.findViewById(R.id.imageView38);
        service.setOnClickListener(this);
        store_container=(LinearLayout)v.findViewById(R.id.ll_store_container);
        crossButton=(ImageView)v.findViewById(R.id.imageView47);
        crossButton.setOnClickListener(this);
        storemain_container=(LinearLayout)v.findViewById(R.id.ll_store_main_container);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.imageView36:
                store.setImageResource(R.drawable.green_storeicon);
                coupons.setImageResource(R.drawable.coupan_button);
                service.setImageResource(R.drawable.service_button);
                Heading.setText("Stores");

                Custom_Recycleview_adapter(stores_name,"Stores");
                if(crossButton.getVisibility()==View.INVISIBLE)
                {
                    crossButton.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imageView37:
                Heading.setText("Coupons");
                store.setImageResource(R.drawable.stores2button);

                coupons.setImageResource(R.drawable.green_coupanicon);
                service.setImageResource(R.drawable.service_button);
                Custom_Recycleview_adapter(stores_name,"Coupons");
                if(crossButton.getVisibility()==View.INVISIBLE)
                {
                    crossButton.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.imageView38:
                Heading.setText("Services");
                store.setImageResource(R.drawable.stores2button);
                coupons.setImageResource(R.drawable.coupan_button);
                service.setImageResource(R.drawable.green_service_icon);
                Custom_Recycleview_adapter(Services,"Services");
                if(crossButton.getVisibility()==View.INVISIBLE)
                {
                    crossButton.setVisibility(View.VISIBLE);
                }

                break;

            case R.id.imageView47:
//                if(storemain_container.getVisibility()==View.VISIBLE)
//                {
//                    storemain_container.setVisibility(View.INVISIBLE);
//                }
//                else{
//                    storemain_container.setVisibility(View.VISIBLE);
//                }


                if(store_container.getVisibility()==View.VISIBLE)
                {
                    store_container.setVisibility(View.INVISIBLE);
                    crossButton.setVisibility(View.INVISIBLE);
                    //storemain_container.setVisibility(View.INVISIBLE);
                }


                break;
        }


    }

    private void Custom_Recycleview_adapter(ArrayList data,String a) {


        rl_store_list.setLayoutManager(rl_itemlist_layoutmanager);
        Offer_storelist_adapter offer_storelist_adapter=new Offer_storelist_adapter(getActivity(),data,getActivity().getSupportFragmentManager(),a);
        rl_store_list.setAdapter(offer_storelist_adapter);
        if(store_container.getVisibility()==View.INVISIBLE)
        {
            store_container.setVisibility(View.VISIBLE);
        }

    }

    private void InitRecycleview(View v) {

        rl_store_list = (RecyclerView) v.findViewById(R.id.rl_store_list);



    }

    // TODO: Rename method, update argument and hook method into UI event



}
