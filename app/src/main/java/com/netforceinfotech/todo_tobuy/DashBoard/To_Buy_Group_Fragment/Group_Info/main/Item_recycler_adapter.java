package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_recycleview_subfragment;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Keypad_dialog;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by abcd on 8/30/2016.
 */
public class Item_recycler_adapter extends RecyclerView.Adapter<CommonHolder_items> implements View.OnClickListener {
    Context context2;
    Activity context3;
    EditText quantity;
    static public String dialog_data;
    static int position_et;
    ArrayList checkedlist = new ArrayList();
    ArrayList uncheckedlist = new ArrayList();
    // ArrayList<CommomData> commomDatas;
    CommonHolder_items viewHolder;
    Activity activity;
    int itemsize;
    public static final int keypad_fragment = 1;
    ArrayList<GroupData> groupDatas;
    Context context;

    public Item_recycler_adapter(Context context, ArrayList<GroupData> groupDatas) {
        this.context = context;
        checkedlist = new ArrayList();
        uncheckedlist = new ArrayList();
        this.groupDatas = groupDatas;
        activity = (Activity) context;

    }

    @Override
    public CommonHolder_items onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_group_listitem_row, parent, false);
        viewHolder = new CommonHolder_items(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonHolder_items holder, final int position) {
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Group_recycleview_subfragment.selectedGroupData.add(groupDatas.get(position));



                } else {

                    Group_recycleview_subfragment.selectedGroupData.remove(position);

                }

            }
        });
        holder.quantity.setOnClickListener(this);
        //holder.rl_quantity.setOnClickListener(this);
        holder.quantity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager)context.
                        getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                return false;
            }
        });
        holder.button3.setText(groupDatas.get(position).name);

    }

    @Override
    public int getItemCount() {
        return groupDatas.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.editText10:

                //quantity.clearFocus();
               // hideSoftKeyboard(quantity);
               // Toast.makeText(context3, "clicked", Toast.LENGTH_SHORT).show();

                Keypad_dialog kd2 = new Keypad_dialog();


                Bundle args2 = new Bundle();
                args2.putString("et_position", "" + position_et);
                kd2.setArguments(args2);

                // setup link back to use and display
                // kd.setTargetFragment(this, keypad_fragment);
                kd2.show(activity.getFragmentManager(), "Keypad_dialog");


                break;
            case R.id.rl_quantity:
                // quantity.clearFocus();
               // hideSoftKeyboard(quantity);
                //Toast.makeText(context2, "clicked", Toast.LENGTH_SHORT).show();

                Keypad_dialog kd = new Keypad_dialog();


                Bundle args = new Bundle();
                args.putString("et_position", "" + position_et);
                kd.setArguments(args);

                // setup link back to use and display
                // kd.setTargetFragment(this, keypad_fragment);
                kd.show(activity.getFragmentManager(), "Keypad_dialog");

                break;
        }

    }

    public void hideSoftKeyboard(EditText et) {
        et.setInputType(0);
        InputMethodManager imm = (InputMethodManager) context3.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }

}