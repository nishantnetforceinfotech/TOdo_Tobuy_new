package com.netforceinfotech.todo.task;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.netforceinfotech.todo.task.list.folder.fragment.ListMainFragment;
import com.netforceinfotech.todo.task.list.folder.fragment.ListSubFragment;
import com.netforceinfotech.todo_tobuy.DashBoard.navigation.NavigationFragment;
import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by owner on 10/14/2016.
 */
public class TodoListFolderActivity extends AppCompatActivity {

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

        ListMainFragment f = new ListMainFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_frag, f, "list_main_fragment")
                        //  .addToBackStack("main")
                .commit();

        ListSubFragment ff = new ListSubFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sub_frag, ff, "list_sub_fragment")
                        //  .addToBackStack("sub")
                .commit();

    }

  }
