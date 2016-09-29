package com.netforceinfotech.todo_tobuy.DashBoard.Offers.Strores;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Services.Beauty.Beauty_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Services.Brands.Service_brands_adpater;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Services.Food.Food_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Services.Movies.Movies_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Strores.Brands_adapter.Store_Brands_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Strores.News_adpater.News_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Strores.Offer_adapter.Offer_adaper;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Store_productlist#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Store_productlist extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String class_type;
    ArrayList data=new ArrayList();
    ArrayList <Integer>stores_reference;
    ImageView Store_offers_type1,Store_offers_type2,Store_offers_type3;
    RecyclerView recyclerView_store,recyclerView_combo,recyclerView_news,recyclerView_brands;


    LinearLayoutManager rl_stores_layoutmanager,rl_combo_layoutmanager,rl_news_layoutmanager,rl_brands_layoutmanager;
    public Store_productlist() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Store_productlist.
     */
    // TODO: Rename and change types and number of parameters
    public static Store_productlist newInstance(String param1, String param2) {
        Store_productlist fragment = new Store_productlist();
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
        View v=inflater.inflate(R.layout.fragment_store_productlist, container, false);
        stores_reference= getArguments().getIntegerArrayList("stores_reference");
        class_type=getArguments().getString("Store_type");
        Store_offers_type1=(ImageView)v.findViewById(R.id.imageView41);
        Store_offers_type2=(ImageView)v.findViewById(R.id.imageView42);
        Store_offers_type3=(ImageView)v.findViewById(R.id.imageView43);


        if(stores_reference.size()!=0)
        {
            Store_offers_type1.setImageResource(stores_reference.get(0));
            Store_offers_type2.setImageResource(stores_reference.get(1));
            Store_offers_type3.setImageResource(stores_reference.get(2));

        }






        data.add("HEB");
        data.add("MODE");
        data.add("S-MART");
        data.add("Soriana");
        data.add("Wallmart");
        rl_stores_layoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rl_combo_layoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rl_news_layoutmanager=new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rl_brands_layoutmanager=new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        initrecycle_offer(v);
        initrecyclecombo(v);
        initrecyclenews(v);
        initrecycle_brands(v);
        // Inflate the layout for this fragment
        return v;
    }

    private void initrecyclecombo(View v) {

        //Offer_storelist_adapter offer_storelist_adapter=new Offer_storelist_adapter(getActivity(),data,getActivity().getSupportFragmentManager());

        if(class_type!=null)
        {
            if(class_type.contains("Stores"))
            { recyclerView_store=(RecyclerView)v.findViewById(R.id.recycleview_offers_combo);
                recyclerView_store.setLayoutManager(rl_stores_layoutmanager);
                Offer_adaper offer_storelist_adapter=new Offer_adaper(getActivity(),data,getActivity().getSupportFragmentManager());


                recyclerView_store.setAdapter(offer_storelist_adapter);
            }
            else if(class_type.contains("Services"))
            { recyclerView_store=(RecyclerView)v.findViewById(R.id.recycleview_offers_combo);
                recyclerView_store.setLayoutManager(rl_stores_layoutmanager);
                Food_adapter food_adapter=new Food_adapter(getActivity(),data,getActivity().getSupportFragmentManager());


                recyclerView_store.setAdapter(food_adapter);

            }

        }




    }

    private void initrecycle_offer(View v) {


        recyclerView_combo=(RecyclerView)v.findViewById(R.id.recycleview_offers);
        recyclerView_combo.setLayoutManager(rl_combo_layoutmanager);

        if(class_type!=null)
        {
            if(class_type.contains("Stores"))
            {
                Offer_adaper offer_storelist_adapter=new Offer_adaper(getActivity(),data,getActivity().getSupportFragmentManager());
                recyclerView_combo.setAdapter(offer_storelist_adapter);
            }
            else if(class_type.contains("Services"))

            {
                Beauty_adapter beauty_adapter=new Beauty_adapter(getActivity(),data,getActivity().getSupportFragmentManager());


                recyclerView_combo.setAdapter(beauty_adapter);

            }

        }





    }

    private void initrecycle_brands(View v) {

recyclerView_brands=(RecyclerView)v.findViewById(R.id.recycleview_brands);
        recyclerView_brands.setLayoutManager(rl_brands_layoutmanager);


        if(class_type!=null)
        {
            if(class_type.contains("Stores"))
            {
                Store_Brands_adapter offer_storelist_adapter=new Store_Brands_adapter(getActivity(),data,getActivity().getSupportFragmentManager());
                //Offer_storelist_adapter offer_storelist_adapter=new Offer_storelist_adapter(getActivity(),data,getActivity().getSupportFragmentManager());
                recyclerView_brands.setAdapter(offer_storelist_adapter);
            }
            else if(class_type.contains("Services"))
            {Service_brands_adpater service_brands_adpater=new Service_brands_adpater(getActivity(),data,getActivity().getSupportFragmentManager());


                recyclerView_brands.setAdapter(service_brands_adpater);

            }

        }






    }

    private void initrecyclenews(View v) {

recyclerView_news=(RecyclerView)v.findViewById(R.id.recycleview_news);
        recyclerView_news.setLayoutManager(rl_news_layoutmanager);


        if(class_type!=null)
        {
            if(class_type.contains("Stores"))
            {
                News_adapter offer_storelist_adapter=new News_adapter(getActivity(),data,getActivity().getSupportFragmentManager());

                // Offer_storelist_adapter offer_storelist_adapter=new Offer_storelist_adapter(getActivity(),data,getActivity().getSupportFragmentManager());
                recyclerView_news.setAdapter(offer_storelist_adapter);
            }
            else if(class_type.contains("Services"))
            {Movies_adapter movies_adapter=new Movies_adapter(getActivity(),data,getActivity().getSupportFragmentManager());


                recyclerView_news.setAdapter(movies_adapter);

            }

        }





    }

}
