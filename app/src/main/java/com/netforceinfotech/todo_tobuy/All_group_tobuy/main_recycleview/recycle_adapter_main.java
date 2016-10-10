package com.netforceinfotech.todo_tobuy.All_group_tobuy.main_recycleview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.netforceinfotech.todo_tobuy.All_group_tobuy.main_recycleview.Sub_recycleview.Recycle_adapter_sub;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.CommonHolder_selected_items;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Confirm_delete_item;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Keypad_dialog;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by abcd on 9/8/2016.
 */
public class Recycle_adapter_main extends RecyclerView.Adapter<CommonHolder_Recycler_main>  {
    Context context2;
    Activity context3;
    static public String dialog_data;
    static int position_et;
    ArrayList checkedlist = new ArrayList();
    ArrayList uncheckedlist = new ArrayList();

    ImageView deleteitem;
    // ArrayList<CommomData> commomDatas;
    CommonHolder_Recycler_main viewHolder;
    Recycle_adapter_sub recycle_adapter_sub;
    int itemsize;
    RecyclerView recycleview_allgroup_sub;
    ArrayList<GroupData> groupDatas;
    public static final int keypad_fragment = 1;
    LinearLayoutManager recycle_allgrp_layoutmanager;

    public Recycle_adapter_main(Context context, ArrayList<GroupData> groupDatas) {
        context2 = context;
        context3 = (Activity) context;
        checkedlist = new ArrayList();
        uncheckedlist = new ArrayList();
        this.groupDatas = groupDatas;

    }

    @Override
    public CommonHolder_Recycler_main onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_all_group_tobuy_row,parent, false);
        viewHolder = new CommonHolder_Recycler_main(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonHolder_Recycler_main holder, final int position) {
        initrecycleview(holder);


    }

    private void initrecycleview(CommonHolder_Recycler_main holder) {

        recycle_allgrp_layoutmanager = new LinearLayoutManager(context2, LinearLayoutManager.VERTICAL, false);
        recycle_adapter_sub = new Recycle_adapter_sub(context2, groupDatas);
        holder.recyclerView.setLayoutManager(recycle_allgrp_layoutmanager);
        holder.recyclerView.setAdapter(recycle_adapter_sub);



    }

    @Override
    public int getItemCount() {
        return groupDatas.size();
    }


}
