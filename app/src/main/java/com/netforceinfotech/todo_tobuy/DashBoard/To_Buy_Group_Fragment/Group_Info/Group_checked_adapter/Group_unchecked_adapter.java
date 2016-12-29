package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.New_group_description.Add_product_info;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Keypad_dialog;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by abcd on 9/3/2016.
 */
public class Group_unchecked_adapter extends RecyclerView.Adapter<Group_unchecked_adapter.ViewHolder> {
    ArrayList<GroupData> groupDatas;
    Context context;

    public Group_unchecked_adapter(Context context, ArrayList<GroupData> groupDatas) {
        this.context = context;
        this.groupDatas = groupDatas;
    }

    @Override
    public Group_unchecked_adapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_group_item_row,
                viewGroup, false);

        ViewHolder holder = new ViewHolder(view, new MyEditTextWatcher());
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.myedit.updatePosition(position);
        holder.quantity.setText(groupDatas.get(position).getQuantity());

        holder.checkBox.setOnCheckedChangeListener(null);

        holder.checkBox.setChecked(groupDatas.get(position).isChecked());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked) {

                    groupDatas.get(holder.getAdapterPosition()).setChecked(true);
                    groupDatas.get(holder.getAdapterPosition()).setText_chk(true);

                }else {

                    groupDatas.get(holder.getAdapterPosition()).setChecked(false);
                    groupDatas.get(holder.getAdapterPosition()).setText_chk(false);
                }
            }
        });

        holder.star.setOnCheckedChangeListener(null);
        if (groupDatas.get(position).isFav()) {

            holder.star.setBackgroundResource(R.drawable.purple_star_item);
        } else {

            holder.star.setBackgroundResource(R.drawable.star_icon_items);
        }

        holder.star.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    groupDatas.get(holder.getAdapterPosition()).setFav(true);
                    holder.star.setBackgroundResource(R.drawable.purple_star_item);

                } else {

                    groupDatas.get(holder.getAdapterPosition()).setFav(false);
                    holder.star.setBackgroundResource(R.drawable.star_icon_items);
                }

            }
        });

        holder.item_info.setTag(position);
        holder.item_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = (int) v.getTag();

                Toast.makeText(context, "item : " + pos, Toast.LENGTH_LONG).show();
            }
        });

        holder.delete_item.setTag(position);
        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pos = (int)v.getTag();

                Toast.makeText(context,"delete item : "+pos,Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return groupDatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        EditText quantity;
        RelativeLayout rl_quantity;
        CheckBox checkBox;
        Button button3;
        CheckBox star;
        ImageView item_info;
        ImageView delete_item;

        public MyEditTextWatcher myedit;

        public ViewHolder(View itemView, MyEditTextWatcher myedit) {
            super(itemView);

            quantity = (EditText) itemView.findViewById(R.id.editText10);
            rl_quantity = (RelativeLayout) itemView.findViewById(R.id.rl_quantity);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkBox3);
            button3 = (Button) itemView.findViewById(R.id.button3);
            star = (CheckBox) itemView.findViewById(R.id.star);
            item_info = (ImageView) itemView.findViewById(R.id.item_info);
            delete_item = (ImageView) itemView.findViewById(R.id.delete_item);

            this.myedit = myedit;
            quantity.addTextChangedListener(myedit);

        }
    }

    public class MyEditTextWatcher implements TextWatcher {

        int postion;

        public void updatePosition(int pos) {

            postion = pos;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            String num = s.toString().replaceAll("\\s", "");
            Log.e("num", num);
            groupDatas.get(postion).setQuantity(s.toString());
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}