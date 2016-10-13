package com.netforceinfotech.todo.task;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.netforceinfotech.todo.task.dashboard.mainfragment.MainFragment;
import com.netforceinfotech.todo.task.dashboard.mainfragment.SubFragment;
import com.netforceinfotech.todo_tobuy.DashBoard.navigation.NavigationFragment;
import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by owner on 10/13/2016.
 */
public class TodoDashboardActivity extends AppCompatActivity {

    Toolbar toolbar;
    NavigationFragment drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_dashboard);
        setupToolBar();
        setupNavigationCustom();
        Replace_all_fragment_main();

    }

    private void setupNavigationCustom() {
        drawer = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        drawer.setup(R.id.fragment, (DrawerLayout) findViewById(R.id.drawer), toolbar);
    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void Replace_all_fragment_main() {

        MainFragment f = new MainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frag, f, "main_fragment")
                .addToBackStack("main")
                .commit();

        SubFragment ff = new SubFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sub_frag, ff, "sub_fragment")
                .addToBackStack("sub")
                .commit();

    }


}
