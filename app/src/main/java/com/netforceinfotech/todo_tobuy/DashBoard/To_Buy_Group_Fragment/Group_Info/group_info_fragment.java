package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_recycleview_subfragment;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main.Group_recycleview_subfragment_main;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main.Item_recycler_adapter;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link group_info_fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class group_info_fragment extends Fragment  implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    LinearLayoutManager rl_itemlist_layoutmanager;
    Item_recycler_adapter item_recycler_adapter;
    public static RecyclerView recycle_itemlist;
    ArrayList get_checked_item, get_unselected_items;
    ImageView done;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Group_recycleview_subfragment_main dashboardFragment;
    private String tagName;
    EditText et_add_item;
    ImageView img_add_items;


    public group_info_fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment group_info_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static group_info_fragment newInstance(String param1, String param2) {
        group_info_fragment fragment = new group_info_fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.fragment_group_info_fragment, container, false);

        Initview(v);




        setUpFragment();

        // Inflate the layout for this fragment
        return v;
    }

    private void Initview(View v) {


         et_add_item=(EditText)v.findViewById(R.id.editText9);
        RelativeLayout rl_addItem=(RelativeLayout)v.findViewById(R.id.rl_add_items);
        rl_addItem.setOnClickListener(this);
        img_add_items=(ImageView)v.findViewById(R.id.imageView21);
        img_add_items.setOnClickListener(this);





    }


    private void replaceFragment(Fragment newFragment, String tag) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_groupdata, newFragment, tag);
        transaction.commit();
    }

    private void setUpFragment() {
        dashboardFragment = new Group_recycleview_subfragment_main();
        tagName = dashboardFragment.getClass().getName();
        replaceFragment(dashboardFragment, tagName);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.rl_add_items:
                //Log.e(" Group_recycleview_subfragment.unselectedGroupData 1",Group_recycleview_subfragment.unselectedGroupData.size()+"");
              GroupData gp =new GroupData(et_add_item.getText().toString(),"",false,false);
                Group_recycleview_subfragment.unselectedGroupData.add(gp);
                Group_recycleview_subfragment.grp_uncheck_adapter.notifyDataSetChanged();
               // Log.e("add Sucessfully", "clicked 1");
               // Log.e(" Group_recycleview_subfragment.unselectedGroupData 2",Group_recycleview_subfragment.unselectedGroupData.size()+"");

                break;
            case R.id.imageView21:
               // Log.e(" Group_recycleview_subfragment.unselectedGroupData 3",Group_recycleview_subfragment.unselectedGroupData.size()+"");

                GroupData gp2 =new GroupData(et_add_item.getText().toString(),"",false,false);

                Group_recycleview_subfragment.unselectedGroupData.add(gp2);
                Group_recycleview_subfragment.grp_uncheck_adapter.notifyDataSetChanged();
                //Log.e("add Sucessfully", "clicked 2");
                //Log.e(" Group_recycleview_subfragment.unselectedGroupData 4",Group_recycleview_subfragment.unselectedGroupData.size()+"");

                break;

        }

    }
}
