package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Keypad_dialog;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by abcd on 9/3/2016.
 */
public class Group_unchecked_adapter extends RecyclerView.Adapter<CommonHolder_selected_items> implements View.OnClickListener {
    Context context2;
    Activity context3;
    EditText quantity;
    static public String dialog_data;
    static int position_et;
    ArrayList checkedlist = new ArrayList();
    ArrayList uncheckedlist = new ArrayList();
    // ArrayList<CommomData> commomDatas;
    CommonHolder_selected_items viewHolder;
    int itemsize;
    ArrayList<GroupData> groupDatas;
    public static final int keypad_fragment = 1;

    public Group_unchecked_adapter(Context context, ArrayList<GroupData> groupDatas) {
        context2 = context;
        context3 = (Activity) context;
        checkedlist = new ArrayList();
        uncheckedlist = new ArrayList();
        this.groupDatas = groupDatas;

    }

    @Override
    public CommonHolder_selected_items onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_group_unchecked_listitem_row, parent, false);
        viewHolder = new CommonHolder_selected_items(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonHolder_selected_items holder, final int position) {
        quantity = holder.quantity;
        quantity.setInputType(InputType.TYPE_NULL);
        quantity.setOnClickListener(this);
        holder.rl_quantity.setOnClickListener(this);
        holder.groupname.setText(groupDatas.get(position).name);
        holder.rl_delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.checkBox.setBackgroundResource(R.drawable.cross_icon_fr_delete);
                Confirm_delete_item confirm_delete_item=new Confirm_delete_item();
                holder.groupname.setText(groupDatas.get(position).toString());


                holder.delete.setBackgroundResource(R.drawable.red_deletebox_icon);


                confirm_delete_item.show(context3.getFragmentManager(), "confirm_delete_item");
            }
        });


        //checkbox listener


        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                groupDatas.get(position).checked = b;
                if(!groupDatas.get(position).checked==b)
                {
                   // holder.groupname.setPaintFlags(holder.groupname.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
                else
                {
                    holder.groupname.setPaintFlags(holder.groupname.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }


            }
        });


        position_et = position;
        //Picasso.with(context2).load(R.drawable.vegetables).into(holder.imageview);
//        holder.cardview.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Intent intent = new Intent(context, CategoryActivity.class);
////                context.startActivity(intent);
//                ((AppCompatActivity)context2).overridePendingTransition(R.anim.enter, R.anim.exit);
//            }
//        });


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
                hideSoftKeyboard(quantity);

                break;
            case R.id.rl_quantity:
                // quantity.clearFocus();
                hideSoftKeyboard(quantity);
                Toast.makeText(context2, "clicked", Toast.LENGTH_SHORT).show();

                Keypad_dialog kd = new Keypad_dialog();


                Bundle args = new Bundle();
                args.putString("et_position", "" + position_et);
                kd.setArguments(args);

                // setup link back to use and display
                // kd.setTargetFragment(this, keypad_fragment);
                kd.show(context3.getFragmentManager(), "Keypad_dialog");

                break;
        }

    }

    public void hideSoftKeyboard(EditText et) {
        et.setInputType(0);
        InputMethodManager imm = (InputMethodManager) context3.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
    }
}