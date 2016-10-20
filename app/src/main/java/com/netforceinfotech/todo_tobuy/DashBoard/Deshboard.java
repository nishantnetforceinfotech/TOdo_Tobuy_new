package com.netforceinfotech.todo_tobuy.DashBoard;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.netforceinfotech.todo.task.TodoListFolderActivity;
import com.netforceinfotech.todo_tobuy.All_group_tobuy.All_group_tobuy_Fragment;
import com.netforceinfotech.todo_tobuy.DashBoard.Main_fragment.Fragment_main;
import com.netforceinfotech.todo_tobuy.DashBoard.navigation.NavigationFragment;
import com.netforceinfotech.todo_tobuy.R;

public class Deshboard extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Context context;
    NavigationFragment drawer;
    ImageView downbutton;
    RelativeLayout rl_all_group, rl_open_offers;
    int counter;
    Fragment offers_fragments;
    View transparency_on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshboard);
        setupToolBar();
        setupNavigationCustom();
        initview();
        Replace_fragment_main();

    }

    public void initview() {
        rl_all_group = (RelativeLayout) findViewById(R.id.rl_All_group);
        rl_all_group.setOnClickListener(this);
        transparency_on = (View) findViewById(R.id.show_transparency);
        downbutton = (ImageView) findViewById(R.id.imageView13);
        downbutton.setOnClickListener(this);
        offers_fragments = getSupportFragmentManager().findFragmentById(R.id.fragment_offers);
        offers_fragments.getView().setVisibility(View.INVISIBLE);


    }

    private void Replace_fragment_main() {
        Fragment_main f = new Fragment_main();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, f, "Fragment_main")
                .addToBackStack("Fragment_main")
                .commit();


    }

    private void setupNavigationCustom() {
        drawer = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        drawer.setup(R.id.fragment, (DrawerLayout) findViewById(R.id.drawer), toolbar);
    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        setSupportActionBar(toolbar);
        context = this;
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }


    @Override
    public void onBackPressed() {

        if (getSupportFragmentManager().getBackStackEntryCount() > 1) { // and then you define a method allowBackPressed with the logic to allow back pressed or not
            super.onBackPressed();
            if (offers_fragments.getView().getVisibility() == View.VISIBLE) {
                offers_fragments.getView().setVisibility(View.INVISIBLE);
                transparency_on.setVisibility(View.INVISIBLE);
                downbutton.setImageResource(R.drawable.arrowicon_left);
            }


        } else {
            if (offers_fragments.getView().getVisibility() == View.VISIBLE) {
                offers_fragments.getView().setVisibility(View.INVISIBLE);
                transparency_on.setVisibility(View.INVISIBLE);
                downbutton.setImageResource(R.drawable.arrowicon_left);
            } else {
                finish();
            }
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_All_group:
                Replace_all_fragment_main();
                break;
            case R.id.imageView13:

                if (counter == 0) {
                    downbutton.setImageResource(R.drawable.down_arrow);
                    offers_fragments.getView().setVisibility(View.VISIBLE);
                    transparency_on.setVisibility(View.VISIBLE);
                    counter = 1;
                    Log.e("visible", "visible");
                } else {
                    downbutton.setImageResource(R.drawable.arrowicon_left);
                    offers_fragments.getView().setVisibility(View.INVISIBLE);
                    counter = 0;
                    transparency_on.setVisibility(View.INVISIBLE);
                    Log.e("visible", "invisible");
                }

                break;


        }

    }

    private void Replace_all_fragment_main() {
        All_group_tobuy_Fragment f = new All_group_tobuy_Fragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, f, "All_group_tobuy_Fragment")
                .addToBackStack("All_group_tobuy_Fragment")
                .commit();


    }


}
