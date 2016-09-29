package com.netforceinfotech.todo_tobuy.DashBoard.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment implements View.OnClickListener {

    public static final String preFile = "textFile";
    public static final String userKey = "key";
    private static final String TAG = "gcm_tag";
    private static int POSITION = 0;
    public static ActionBarDrawerToggle mDrawerToggle;
    public static DrawerLayout mDrawerLayout;
    boolean mUserLearnedDrawer;
    boolean mFromSavedInstance;
    View view;
    public static final String PREFS_NAME = "call_recorder";
    private SharedPreferences loginPreferences;
    public static SharedPreferences.Editor loginPrefsEditor;
    private Context context;
    TextView footer;
    RelativeLayout header;
   // CircleImageView circleImageViewProfilePic;
    TextView textViewName;
    private ImageView imageViewGB;
    private ExpandableListView expListView;
    private ImageView edit_profile_imgview;
   // private ExpandableListAdapter listAdapter;
    private ArrayList<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    RecyclerView navigation_recycle;
    LinearLayoutManager rl_navigation_layoutmanager;
    Navigation_data_adapter navigation_data_adapter;

    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_navigation, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        //sharedprefrance
        loginPreferences = getActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        //expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        navigation_data_adapter=new Navigation_data_adapter(getActivity());
       // edit_profile_imgview=(ImageView)view.findViewById(R.id.img_edit_profile);
        rl_navigation_layoutmanager =  new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
       // edit_profile_imgview.setOnClickListener(this);

        // preparing list data
        prepareListData();
        initrecycleview(view);
//        listAdapter = new ExpandableListAdapter(context, listDataHeader, listDataChild);
//        // setting list adapter
//        expListView.setAdapter(listAdapter);
//        listAdapter.setClickListner(this);
    }

    private void initrecycleview(View view) {
        navigation_recycle = (RecyclerView)view.findViewById(R.id.recycler_navigatin_drawer);
        navigation_recycle.setLayoutManager(rl_navigation_layoutmanager);
        navigation_recycle.setAdapter(navigation_data_adapter);
    }


    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Category");
        listDataHeader.add("Account");
        listDataHeader.add("Settings");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("Woman's Clothings");
        top250.add("Man's Clothings");
        top250.add("Electronics");
        top250.add("Home and Garden");
        top250.add("Jwellery and Health");
        top250.add("Automotive");
        top250.add("Beauty and Health");
        top250.add("Toys, Kids and Baby");
        top250.add("Bags and Shoes");
        top250.add("Sports and Outdoor");
        top250.add("Phone and Accessories");
        top250.add("Computer and Networking");
        top250.add("VIEW ALL CATEGORIES");
        List<String> nowShowing = new ArrayList<String>();

        List<String> comingSoon = new ArrayList<String>();
        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        mUserLearnedDrawer = Boolean.valueOf(readFromPreferences(getActivity(), userKey, "false"));
        if (savedInstanceState != null) {
            mFromSavedInstance = true;
        }
    }

    public void setup(int id, final DrawerLayout drawer, Toolbar toolbar) {
        view = getActivity().findViewById(id);

        mDrawerLayout = drawer;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.drawer_open, R
                .string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                hideSoftKeyboard(getActivity());

                super.onDrawerOpened(drawerView);
                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    savedInstances(getActivity(), userKey, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }

        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });


    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
      //  mDrawerLayout.closeDrawers();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public static void savedInstances(Context context, String preferenceName, String preferenceValue) {
        SharedPreferences sharePreference = context.getSharedPreferences(preFile, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharePreference.edit();
        editor.putString(preferenceName, preferenceValue);
        editor.apply();
    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {
        SharedPreferences sharePreference = context.getSharedPreferences(preFile, Context.MODE_PRIVATE);
        return sharePreference.getString(preferenceName, defaultValue);
    }


    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
           // case R.id.img_edit_profile:
//                Intent i=new Intent(getActivity(), Edit_profile_activity.class);
//                startActivity(i);
                //break;
        }
    }

    private void showMessage(String clicked) {
        Toast.makeText(context, clicked, Toast.LENGTH_SHORT).show();
    }

//    private void replaceFragment(Fragment newFragment, String tag) {
//
//        FragmentTransaction transaction = ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.drawer_layout, newFragment, tag);
//        transaction.commit();
//    }


}

