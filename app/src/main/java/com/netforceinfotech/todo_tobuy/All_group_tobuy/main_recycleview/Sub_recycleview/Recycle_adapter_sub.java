package com.netforceinfotech.todo_tobuy.All_group_tobuy.main_recycleview.Sub_recycleview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by abcd on 9/8/2016.
 */
public class Recycle_adapter_sub extends RecyclerView.Adapter<CommonHolder_Recycler_sub>  {
    Context context2;
    Activity context3;
    EditText quantity;
    static public String dialog_data;
    static int position_et;
    ArrayList checkedlist = new ArrayList();
    ArrayList uncheckedlist = new ArrayList();
    ImageView deleteitem;
    // ArrayList<CommomData> commomDatas;
    CommonHolder_Recycler_sub viewHolder;
    int itemsize;
    ArrayList<GroupData> groupDatas;
    public static final int keypad_fragment = 1;

    public Recycle_adapter_sub(Context context, ArrayList<GroupData> groupDatas) {
        context2 = context;
        context3 = (Activity) context;
        checkedlist = new ArrayList();
        uncheckedlist = new ArrayList();
        this.groupDatas = groupDatas;

    }

    @Override
    public CommonHolder_Recycler_sub onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_all_group_tobuy_row_sub,parent, false);
        viewHolder = new CommonHolder_Recycler_sub(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonHolder_Recycler_sub holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return groupDatas.size();
    }


}
