package com.netforceinfotech.todo_tobuy.DashBoard;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.netforceinfotech.todo_tobuy.DashBoard.Main_fragment.Fragment_main;
import com.netforceinfotech.todo_tobuy.DashBoard.navigation.NavigationFragment;
import com.netforceinfotech.todo_tobuy.R;

public class Deshboard extends AppCompatActivity {
    Toolbar toolbar;
    Context context;
    NavigationFragment drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deshboard);
        setupToolBar();
        setupNavigationCustom();
        Replace_fragment_main();

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            // only for gingerbread and newer versions
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.greentranparent));
        }

    }

    private void Replace_fragment_main() {
        Fragment_main f=new Fragment_main();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, f,"Fragment_main")
                .addToBackStack("Fragment_main")
                .commit();





    }

    private void setupNavigationCustom() {
        drawer = (NavigationFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        drawer.setup(R.id.fragment, (DrawerLayout) findViewById(R.id.drawer), toolbar);
    }

    private void setupToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setContentInsetsAbsolute(0,0);
        setSupportActionBar(toolbar);
        context=this;
        getSupportActionBar().setHomeButtonEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        String teams = "Home";
//        getSupportActionBar().setTitle(teams);

        getSupportActionBar().setDisplayShowTitleEnabled(false);



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashboard, menu);
//        ActionItemBadge.update(((AppCompatActivity) context), menu.findItem(R.id.add_to_cart), ContextCompat.getDrawable(context, R.drawable.ic_cart_black)
//                , ActionItemBadge.BadgeStyles.GREY, 3);
        return true;
    }
    @Override
    public void onBackPressed() {
        //final Fragment_main fragment = (Fragment_main) getSupportFragmentManager().findFragmentByTag("Fragment_main");

        if (getSupportFragmentManager().getBackStackEntryCount()>1) { // and then you define a method allowBackPressed with the logic to allow back pressed or not
            super.onBackPressed();
        }
        else{
            Toast.makeText(Deshboard.this,""+getSupportFragmentManager().getBackStackEntryCount(),Toast.LENGTH_SHORT).show();
        }
    }

}
