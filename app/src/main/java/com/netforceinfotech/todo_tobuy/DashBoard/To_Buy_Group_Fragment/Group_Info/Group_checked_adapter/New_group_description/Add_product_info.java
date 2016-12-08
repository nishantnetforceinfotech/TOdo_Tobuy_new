package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.New_group_description;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main.Item_recycler_adapter;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Add_product_info#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Add_product_info extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner alter_brands_spinner,alter_store_spinner,alter_item_spinner;


    public Add_product_info() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Add_product_info.
     */
    // TODO: Rename and change types and number of parameters
    public static Add_product_info newInstance(String param1, String param2) {
        Add_product_info fragment = new Add_product_info();
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
        View  v=inflater.inflate(R.layout.fragment_add_product_info, container, false);
        InitView(v);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            int myInt = bundle.getInt("status");
            if(myInt!=1)
            {
                setspinner_data();
            }
            else{
Log.e("myInt",""+myInt);
                setspinner_data();
            }
        }







        // Inflate the layout for this fragment
        return v;
    }

    private void setspinner_data() {

for(int i=0;i<Item_recycler_adapter.alterdata.size();i++) {
    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, Item_recycler_adapter.alterdata); //selected item will look like a spinner set from XML
    spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    alter_item_spinner.setAdapter(spinnerArrayAdapter);
}
for (int k=0;k<Item_recycler_adapter.branddata.size();k++){
            ArrayAdapter<String> spinnerArrayAdapter2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, Item_recycler_adapter.branddata); //selected item will look like a spinner set from XML
            spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            alter_brands_spinner.setAdapter(spinnerArrayAdapter2);
        }


       for (int l=0;l<Item_recycler_adapter.storedata.size();l++) {
            ArrayAdapter<String> spinnerArrayAdapter3 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, Item_recycler_adapter.storedata); //selected item will look like a spinner set from XML
            spinnerArrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            alter_store_spinner.setAdapter(spinnerArrayAdapter3);
        }





    }

    private void InitView(View v) {

        alter_brands_spinner=(Spinner)v.findViewById(R.id.spinner_alter_brands);
        alter_item_spinner=(Spinner)v.findViewById(R.id.spinner_alter_item);
        alter_store_spinner=(Spinner)v.findViewById(R.id.spinner_alter_store);

    }

}
