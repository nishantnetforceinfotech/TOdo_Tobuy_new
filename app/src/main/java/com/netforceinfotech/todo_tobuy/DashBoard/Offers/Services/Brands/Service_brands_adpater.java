package com.netforceinfotech.todo_tobuy.DashBoard.Offers.Services.Brands;

import android.app.Activity;
import android.content.Context;
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
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by abcd on 9/14/2016.
 */
public class Service_brands_adpater extends RecyclerView.Adapter<CommonHolder_Recycler_Service_brands_adapter>  {
    Context context2;
    Activity context3;
    EditText quantity;
    static public String dialog_data;
    static int position_et;
    FragmentManager fragmentManager;
    String tagName;
    Store_productlist store_productlist;
    ImageView deleteitem;
    // ArrayList<CommomData> commomDatas;
    CommonHolder_Recycler_Service_brands_adapter viewHolder;
    int itemsize;
    ArrayList Storelist;
    public static final int keypad_fragment = 1;

    public Service_brands_adpater(Context context, ArrayList Storelist,FragmentManager fm) {
        context2 = context;
        context3 = (AppCompatActivity)context;

        fragmentManager = fm;
        this.Storelist = Storelist;

    }

    @Override
    public CommonHolder_Recycler_Service_brands_adapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_services_single_row,parent, false);
        viewHolder = new CommonHolder_Recycler_Service_brands_adapter(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonHolder_Recycler_Service_brands_adapter holder, final int position) {
//        holder.store_name.setText(Storelist.get(position).toString());
//        holder.ll_store_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                setUpFragment();
//
//            }
//        });


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

    private void setUpFragment() {
        store_productlist = new Store_productlist();
        tagName = store_productlist.getClass().getName();
        replaceFragment(store_productlist, tagName);
    }


}