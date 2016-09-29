package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_recycleview_subfragment;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main.Item_recycler_adapter;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Group_Fragment_tobuy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Group_Fragment_tobuy extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Item_recycler_adapter item_recycler_adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ArrayList get_checked_item, get_unselected_items;
    LinearLayoutManager rl_itemlist_layoutmanager;
    RelativeLayout rl_imagegetter;
    RecyclerView recycle_itemlist;
    ImageView done;
    ArrayList checked_arraylist;
    ArrayList<GroupData> groupDatas = new ArrayList<>();
    public static ImageView get_cameraorgalley_image;

    public Group_Fragment_tobuy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Group_Fragment_tobuy.
     */
    // TODO: Rename and change types and number of parameters
    public static Group_Fragment_tobuy newInstance(String param1, String param2) {
        Group_Fragment_tobuy fragment = new Group_Fragment_tobuy();
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
        View v = inflater.inflate(R.layout.fragment_group__fragment_tobuy, container, false);
        checked_arraylist = new ArrayList();
        get_unselected_items = new ArrayList();
        get_cameraorgalley_image = (ImageView) v.findViewById(R.id.imageView18);
        rl_imagegetter = (RelativeLayout) v.findViewById(R.id.rl_imagegetter);
        done = (ImageView) v.findViewById(R.id.imageView22);
        done.setOnClickListener(this);
        rl_imagegetter.setOnClickListener(this);
        rl_itemlist_layoutmanager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        item_recycler_adapter = new Item_recycler_adapter(getActivity(), groupDatas);
        get_checked_item = new ArrayList();
        setupDummyData();
        Intializeecycleview(v);

        // Inflate the layout for this fragment
        return v;
    }

    private void setupDummyData() {
        for (int i = 0; i < 10; i++) {
            groupDatas.add(new GroupData("", "", false, false));
        }
    }

    private void Intializeecycleview(View v) {
        recycle_itemlist = (RecyclerView) v.findViewById(R.id.recycleview_items);
        recycle_itemlist.setLayoutManager(rl_itemlist_layoutmanager);
        recycle_itemlist.setAdapter(item_recycler_adapter);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_imagegetter:
                showdialogmedia();
                break;
            case R.id.imageView22:
                list_checked_items();
                break;


        }

    }

    private void list_checked_items() {

        get_checked_item.clear();
        get_unselected_items.clear();
//        for (int i = 0; i < Group_recycleview_subfragment_main.recycle_itemlist.getChildCount(); i++) {
//            View v2 = (LinearLayout) Group_recycleview_subfragment_main.recycle_itemlist.getChildAt(i);
//            CheckBox ch_bx = (CheckBox) v2.findViewById(R.id.checkBox3);
//            if (ch_bx.isChecked()) {
//                get_checked_item.add(i);
//
//
//            } else {
//                get_unselected_items.add(i);
////               int k= recycle_itemlist.getChildLayoutPosition(ch_bx);
////                Log.e("get unchecked item",""+k);
//            }
//
//        }
        Group_recycleview_subfragment f = new Group_recycleview_subfragment();
        Bundle b = new Bundle();
        b.putStringArrayList("checked_item", get_checked_item);
        f.setArguments(b);


//        getActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fl_groupdata, f,"fl_groupdata")
//                .addToBackStack("fl_groupdata")
//                .commit();
//        Log.e("list of checked item", get_checked_item.toString());
//        Log.e("list of unchecked_item", get_unselected_items.toString());
//        Log.e("child_count", "" + Group_recycleview_subfragment_main.recycle_itemlist.getChildCount());


        // EditText et1=(EditText)v2.findViewById(R.id.editText10);

    }

    private void showdialogmedia() {
        Dialog_media_fragment dialog_fragment = new Dialog_media_fragment();
        dialog_fragment.show(getActivity().getFragmentManager(), "Dialog_media_fragment");


    }
}
