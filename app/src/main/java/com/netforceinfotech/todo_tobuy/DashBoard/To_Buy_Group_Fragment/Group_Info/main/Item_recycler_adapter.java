package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Adapter.Alternate_item_pojo;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Adapter.Brandproduct_pojo;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Adapter.Storeproduct_pojo;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_recycleview_subfragment;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.New_group_description.Add_product_info;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Keypad_dialog;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.ToBuyData;
import com.netforceinfotech.todo_tobuy.R;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by abcd on 8/30/2016.
 */
public class Item_recycler_adapter extends RecyclerView.Adapter<CommonHolder_items> implements View.OnClickListener {
    Context context2;
    Activity context3;
    EditText quantity;
    public static String dialog_data;
    static int position_et;
    ArrayList checkedlist = new ArrayList();
    ArrayList uncheckedlist = new ArrayList();
    Button itemname;
    // SenddataListener ss;
    // ArrayList<CommomData> commomDatas;
    CommonHolder_items viewHolder;
    Activity activity;
    int itemsize;
    public static final int keypad_fragment = 1;
    ArrayList<GroupData> groupDatas;
    public static ArrayList<Storeproduct_pojo> storedata;
    public static ArrayList<Brandproduct_pojo> branddata;
    public static ArrayList<Alternate_item_pojo> alterdata;

    Context context;
    /*ArrayList<String> item_ids = new ArrayList();
    ArrayList<String> item_quantity = new ArrayList();*/
    ArrayList<ToBuyData> toBuyDatas;

    public Item_recycler_adapter(Context context, ArrayList<GroupData> groupDatas, ArrayList<ToBuyData> toBuyDatas) {
        this.context = context;
        checkedlist = new ArrayList();
        uncheckedlist = new ArrayList();
        this.groupDatas = groupDatas;
        activity = (Activity) context;
       /* this.item_ids = item_ids;
        this.item_quantity = item_quantity;*/
        this.toBuyDatas = toBuyDatas;
        branddata = new ArrayList<Brandproduct_pojo>();
        storedata = new ArrayList<Storeproduct_pojo>();
        alterdata = new ArrayList<Alternate_item_pojo>();


    }

    @Override
    public CommonHolder_items onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_group_listitem_row,
                parent, false);
        viewHolder = new CommonHolder_items(view);
        viewHolder.quantity.setText("0");

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommonHolder_items holder, final int position) {

        GroupData data = groupDatas.get(position);
            holder.quantity.setTag(position);
        holder.button3.setText(groupDatas.get(position).getName().toString());
        holder.delete_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (groupDatas.size() != 0) {
                    groupDatas.remove(position);
                    notifyDataSetChanged();
                }
            }
        });

        toBuyDatas.get(position).quantity = holder.quantity.getText().toString();
       /* holder.quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String ans = holder.quantity.getText().toString();
                Log.e("ans",ans);
            }
        });*/


//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                if (b) {
//                    Group_recycleview_subfragment.selectedGroupData.add(groupDatas.get(position));
//
//                } else {
//
//                    Group_recycleview_subfragment.selectedGroupData.remove(position);
//
//                }
//
//            }
//        });
//
//
//        holder.Item_description.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//
//                call_description_webservice(position);
//
//
//            }
//        });


        // holder.quantity.setOnClickListener(this);
        //holder.rl_quantity.setOnClickListener(this);
/*
        holder.quantity.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                InputMethodManager imm = (InputMethodManager)context.
                        getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                return false;
            }
        });
*/
        //  holder.button3.setText(groupDatas.get(position).name);
        Log.e("itemlistquantity", toBuyDatas.get(position).quantity);


    }

    private void call_description_webservice(int position) {

        String desription_webservice_url = "http://netforce.biz/todotobuy/products/item_detail/" + toBuyDatas.get(position).id;
        Ion.with(context)
                .load(desription_webservice_url)

                .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
            @Override
            public void onCompleted(Exception e, JsonObject result) {
                String Status = result.get("status").getAsString();
                if (Status.contains("1")) {
                    JsonObject data = result.getAsJsonObject("data");
                    JsonArray Item_data = data.getAsJsonArray("item").getAsJsonArray();
                    JsonArray Alternative_data = data.getAsJsonArray("alternative").getAsJsonArray();
                    Log.e("Item_data", Item_data.toString());
                    // extract _item_data


                    for (int i = 0; i < Item_data.size(); i++)

                    {
                        JsonObject js_buyItem = Item_data.get(i).getAsJsonObject().getAsJsonObject("BuyItem");

                        String item_id = js_buyItem.get("id").getAsString();
                        String item_name = js_buyItem.get("name").getAsString();
                        Log.e("item_id_", item_id + "****" + item_name);


                        JsonObject product_image_js = Item_data.get(i).getAsJsonObject().getAsJsonObject("0");
                        String product_image_url = product_image_js.get("images").getAsString();


                        // StoreProduct

                        JsonArray StoreProduct_jaa = Item_data.get(i).getAsJsonObject().getAsJsonArray("StoreProduct");
                        for (int k = 0; k < StoreProduct_jaa.size(); k++)

                        {
                            JsonObject js_StoreProduct = StoreProduct_jaa.get(i).getAsJsonObject().getAsJsonObject("Store");
                            String store_id = js_StoreProduct.get("id").getAsString();
                            String store_name = js_StoreProduct.get("name").getAsString();
                            storedata.add(new Storeproduct_pojo(store_id, store_name));


                        }
                        // BrandProduct
                        JsonArray BrandProduct_js = Item_data.get(i).getAsJsonObject().getAsJsonArray("BrandProduct");

                        for (int l = 0; l < StoreProduct_jaa.size(); l++)

                        {
                            JsonObject js_Brand = BrandProduct_js.get(i).getAsJsonObject().getAsJsonObject("Brand");
                            String brand_id = js_Brand.get("id").getAsString();
                            String brand_name = js_Brand.get("name").getAsString();
                            branddata.add(new Brandproduct_pojo(brand_id, brand_name));


                        }


                    }


                    // Alternate_items

                    for (int i = 0; i < Alternative_data.size(); i++)

                    {
                        JsonObject js_buyItem = Alternative_data.get(i).getAsJsonObject().getAsJsonObject("BuyItem");

                        String item_id = js_buyItem.get("id").getAsString();
                        String item_name = js_buyItem.get("name").getAsString();
                        alterdata.add(new Alternate_item_pojo(item_id, item_name));
                        Log.e("item_id_", item_id + "****" + item_name);
                    }


//ss.senddata(storedata,branddata,alterdata);

                    Replace_fragment_main();


                    Log.e("Alternative_data", Alternative_data.toString());


                } else {
                    Toast.makeText(context, "Error in fetching data by server", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return groupDatas.size();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


            case R.id.editText10:
                int pos = (int) viewHolder.quantity.getTag();
                Keypad_dialog kd2 = new Keypad_dialog();
                Bundle args2 = new Bundle();
                args2.putString("et_position", "" + pos);
                kd2.setArguments(args2);

                // setup link back to use and display
                // kd.setTargetFragment(this, keypad_fragment);
                kd2.show(activity.getFragmentManager(), "Keypad_dialog");


                break;
            case R.id.rl_quantity:
                // quantity.clearFocus();
                // hideSoftKeyboard(quantity);
                //Toast.makeText(context2, "clicked", Toast.LENGTH_SHORT).show();
                int pos1 = (int) viewHolder.quantity.getTag();
                Keypad_dialog kd = new Keypad_dialog();
                Bundle args = new Bundle();
                args.putString("et_position", "" + pos1);
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

    private void Replace_fragment_main() {
        Bundle bundle = new Bundle();
        bundle.putInt("status", 1);

        Add_product_info f = new Add_product_info();
        f.setArguments(bundle);

        ((AppCompatActivity) context).getSupportFragmentManager().
                beginTransaction()
                .replace(R.id.container_main, f)
                .addToBackStack(null)
                .commit();


    }


//    public interface SenddataListener {
//       // public void senddata(ArrayList<Storeproduct_pojo> storedata,ArrayList<Brandproduct_pojo> branddata,ArrayList<Alternate_item_pojo> alterdata);
//    }

}