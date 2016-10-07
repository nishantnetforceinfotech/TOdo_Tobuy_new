package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * Created by owner on 10/6/2016.
 */
public class ItemDetailsAdapter extends RecyclerView.Adapter<ItemDetailsAdapter.ViewHolder> {

    ArrayList<GroupData> groupDatas;
    Context context;

    public ItemDetailsAdapter(Context context, ArrayList<GroupData> groupDatas) {
        this.context = context;
        this.groupDatas = groupDatas;
    }

    @Override
    public ItemDetailsAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewtype) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fragment_group_listitem_row,
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
                    groupDatas.get(holder.getAdapterPosition()).setText_chk(false);

                }else {

                    groupDatas.get(holder.getAdapterPosition()).setChecked(false);
                    groupDatas.get(holder.getAdapterPosition()).setText_chk(true);
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
