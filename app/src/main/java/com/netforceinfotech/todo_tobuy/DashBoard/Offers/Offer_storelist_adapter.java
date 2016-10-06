package com.netforceinfotech.todo_tobuy.DashBoard.Offers;

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Strores.Store_productlist;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Strores.Stores_activity;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by abcd on 9/8/2016.
 */
public class Offer_storelist_adapter extends RecyclerView.Adapter<CommonHolder_Recycler_offerlist> {
    Context context2;
    Activity context3;
    EditText quantity;
    static public String dialog_data;
    static int position_et;
    FragmentManager fragmentManager;
    ArrayList Services;
    String tagName;
    Store_productlist store_productlist;
    ImageView deleteitem;
    // ArrayList<CommomData> commomDatas;
    CommonHolder_Recycler_offerlist viewHolder;
    int itemsize;
    ArrayList Storelist;
    public static final int keypad_fragment = 1;
    String s;

    public Offer_storelist_adapter(Context context, ArrayList Storelist, FragmentManager fm, String s) {
        context2 = context;
        context3 = (AppCompatActivity) context;

        fragmentManager = fm;
        this.Storelist = Storelist;
        this.s = s;

    }

    @Override
    public CommonHolder_Recycler_offerlist onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.offers_storelist_row, parent, false);
        viewHolder = new CommonHolder_Recycler_offerlist(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonHolder_Recycler_offerlist holder, final int position) {
        holder.store_name.setText(Storelist.get(position).toString());
        holder.ll_store_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s.contains("Stores"))

                {
                    ArrayList<Integer> aa = new ArrayList();
                    aa.add(R.drawable.offers_green);
                    aa.add(R.drawable.combo_green);
                    aa.add(R.drawable.news_green);

                    Bundle b = new Bundle();
                    b.putIntegerArrayList("stores_reference", aa);
                    b.putString("Store_type", "Stores");
                    SetupStoreActivty("Stores");
                    //setUpFragment(b);
                    if (Offers_Fragments.store_container.getVisibility() == View.VISIBLE) {
                        Offers_Fragments.store_container.setVisibility(View.INVISIBLE);
                        Offers_Fragments.crossButton.setVisibility(View.INVISIBLE);
                        //storemain_container.setVisibility(View.INVISIBLE);
                    }
                } else if (s.contains("Coupons")) {
                    ArrayList<Integer> aa = new ArrayList();
                    aa.add(R.drawable.offers_green);
                    aa.add(R.drawable.combo_green);
                    aa.add(R.drawable.news_green);

                    Bundle b = new Bundle();
                    b.putIntegerArrayList("stores_reference", aa);
                    b.putString("Store_type", "Coupons");


                    SetupStoreActivty("Coupons");

//                    setUpFragment(b);
                    if (Offers_Fragments.store_container.getVisibility() == View.VISIBLE) {
                        Offers_Fragments.store_container.setVisibility(View.INVISIBLE);
                        Offers_Fragments.crossButton.setVisibility(View.INVISIBLE);
                        //storemain_container.setVisibility(View.INVISIBLE);
                    }

                } else if (s.contains("Services")) {


                    ArrayList<Integer> aa = new ArrayList();
                    aa.add(R.drawable.beauty_demo);
                    aa.add(R.drawable.food_demo);
                    aa.add(R.drawable.movies_demo);
                    Bundle b = new Bundle();
                    b.putIntegerArrayList("stores_reference", aa);
                    b.putString("Store_type", "Services");
                    SetupStoreActivty("Services");
//                    setUpFragment(b);
                    if (Offers_Fragments.store_container.getVisibility() == View.VISIBLE) {
                        Offers_Fragments.store_container.setVisibility(View.INVISIBLE);
                        Offers_Fragments.crossButton.setVisibility(View.INVISIBLE);
                        //storemain_container.setVisibility(View.INVISIBLE);
                    }

                }

            }
        });


    }

    private void SetupStoreActivty(String stores) {
        Intent i = new Intent(context2, Stores_activity.class);
        i.putExtra("class_type", stores);
        context2.startActivity(i);

    }

    @Override
    public int getItemCount() {
        return Storelist.size();
    }


    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container_main, newFragment, tag);
        transaction.commit();
    }

    private void setUpFragment(Bundle b) {
        store_productlist = new Store_productlist();
        store_productlist.setArguments(b);
        tagName = store_productlist.getClass().getName();
        replaceFragment(store_productlist, tagName);
    }


}

