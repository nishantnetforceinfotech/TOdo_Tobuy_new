package com.netforceinfotech.todo_tobuy.DashBoard.Main_fragment;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Fragment_tobuy;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Searchdatas_pojo;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.OnStartDragListener;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.SimpleItemTouchHelperCallback;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Fragment_main extends Fragment implements OnStartDragListener {

    RecyclerView rl_view;
    GridLayoutManager gridLayoutManager;
    Fragment_main_grid_adapter main_grid_adapter;
    private ItemTouchHelper mItemTouchHelper;
    ImageView More;
    ArrayList<Group_dashboard_datas> list = new ArrayList<Group_dashboard_datas>();
    SharedPreferences sh;

ProgressDialog pd;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        More=(ImageView)view.findViewById(R.id.imageView19);
        pd=new ProgressDialog(getActivity());
        gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        rl_view = (RecyclerView) view.findViewById(R.id.RecyclerView_main);

        get_all_group_webservice(getActivity());

        More.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(getActivity(), More);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.more_menu_fragment_main, popup.getMenu());

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                       // Toast.makeText(getActivity(), "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                popup.show();//showing popup menu
            }
        });

        ((TextView) view.findViewById(R.id.new_group)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Group_Fragment_tobuy f = new Group_Fragment_tobuy();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_main, f, "Group_Fragment_tobuy")
                        .addToBackStack("Group_Fragment_tobuy")
                        .commit();

            }
        });

        ((ImageView)view.findViewById(R.id.new_group_img)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Group_Fragment_tobuy f = new Group_Fragment_tobuy();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_main, f, "Group_Fragment_tobuy")
                        .addToBackStack("Group_Fragment_tobuy")
                        .commit();

            }
        });

        return view;
    }

    private void get_all_group_webservice(final FragmentActivity activity) {
        SharedPreferences pref1 = getActivity().getApplicationContext().getSharedPreferences("ToDo-ToBuy", 0);
        list.clear();
        pd.show();
        String userid=pref1.getString("userId", "demo");
        if(userid!=null)
        {
            String getall_group_webservice_url="http://netforce.biz/todotobuy/products/list_group/"+userid;
            Ion.with(getActivity())
                    .load(getall_group_webservice_url)

                    .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
                @Override
                public void onCompleted(Exception e, JsonObject result) {
                 if(result!=null)
                 {
                     String status = result.get("status").getAsString();
                     if (status.contains("Success")) {

                         JsonArray data = result.getAsJsonArray("groups");

                         for (int i = 0; i < data.size(); i++)

                         {
                             JsonObject js2 = data.get(i).getAsJsonObject();

                             String group_id = js2.get("id").getAsString();
                             String group_name = js2.get("name").getAsString();
                             String group_image_url=js2.get("image").getAsString();
                             String group_item_qty=js2.get("item_qty").getAsString();
                             Log.e("result_getgroup_detail", group_id + "****" + group_name + group_image_url +group_item_qty);
                             //searchdatas.add(new Searchdatas_pojo(item_id, "0", item_name));
                             //  Log.e("searchdatas",searchdatas.toString());
                             //   item_ids.add(item_id);
                             //  item_quantity.add("0");
                            // aa.add(item_name);
                             list.add(new Group_dashboard_datas(group_id,group_name,group_image_url,group_item_qty));

                         }

//                         for(int k=1;k<list.size();k++){
//
//                             list.add(k+"");
//                         }
                         main_grid_adapter = new Fragment_main_grid_adapter(activity,
                                 activity.getSupportFragmentManager(), new OnStartDragListener() {
                             @Override
                             public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

                             }
                         }, list);

                         rl_view.setLayoutManager(gridLayoutManager);
                         rl_view.setAdapter(main_grid_adapter);
                         // Inflate the layout for this fragment

                         ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(main_grid_adapter);
                         mItemTouchHelper = new ItemTouchHelper(callback);
                         mItemTouchHelper.attachToRecyclerView(rl_view);
                     }

if(pd!=null)
{
    pd.dismiss();
}
                     Log.e("get_all_group_response",result.toString());
                 }
                }
            });


        }




    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        //  mItemTouchHelper.startDrag(viewHolder);
        Log.e("hhh", "test");
    }
}
