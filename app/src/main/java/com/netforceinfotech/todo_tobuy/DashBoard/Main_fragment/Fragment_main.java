package com.netforceinfotech.todo_tobuy.DashBoard.Main_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_main#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_main extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView rl_view;
GridLayoutManager gridLayoutManager;
    Fragment_main_grid_adapter main_grid_adapter;

    public Fragment_main() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_main.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_main newInstance(String param1, String param2) {
        Fragment_main fragment = new Fragment_main();
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
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        gridLayoutManager =  new GridLayoutManager(getActivity(),3);
        rl_view=(RecyclerView)view.findViewById(R.id.RecyclerView_main);
        main_grid_adapter=new Fragment_main_grid_adapter(getActivity(),getActivity().getSupportFragmentManager());

        rl_view.setLayoutManager(gridLayoutManager);
        rl_view.setAdapter(main_grid_adapter);
        // Inflate the layout for this fragment
        return view;
    }


}
