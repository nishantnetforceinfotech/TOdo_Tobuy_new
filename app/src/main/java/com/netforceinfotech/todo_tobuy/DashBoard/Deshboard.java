package com.netforceinfotech.todo_tobuy.DashBoard;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.support.v13.app.ActivityCompat;
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
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.netforceinfotech.genral.Get_location;
import com.netforceinfotech.todo.task.TodoListFolderActivity;
import com.netforceinfotech.todo_tobuy.All_group_tobuy.All_group_tobuy_Fragment;
import com.netforceinfotech.todo_tobuy.DashBoard.Main_fragment.Fragment_main;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Adapter.Alternate_item_pojo;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Adapter.Brandproduct_pojo;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Adapter.Storeproduct_pojo;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main.Item_recycler_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.navigation.NavigationFragment;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

public class Deshboard extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    Context context;
    NavigationFragment drawer;
    ImageView downbutton,show_blinking_images;
    RelativeLayout rl_all_group, rl_open_offers;
    int counter;
    Fragment offers_fragments;
    View transparency_on;
    int REQUEST_CHECK_SETTINGS=1;
    Get_location get;
    int PERMISSION_ALL = 1;
    TextView blinking_text;
    String[] PERMISSIONS = {Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_SMS,
            Manifest.permission.CAMERA,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_NETWORK_STATE};


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
        //blinking_text=(TextView)findViewById(R.id.textView25);
        downbutton.setOnClickListener(this);
        offers_fragments = getSupportFragmentManager().findFragmentById(R.id.fragment_offers);
        offers_fragments.getView().setVisibility(View.INVISIBLE);
        show_blinking_images=(ImageView)findViewById(R.id.imageView9);
        displayLocationSettingsRequest(this);
       // showanimation(blinking_text);
        if(!hasPermissions(this, PERMISSIONS)){
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
            get=new Get_location(this);
        }
        else{get=new Get_location(this);}


        // Show All List
        show_blinking_images.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Replace_all_fragment_main();
            }
        });







    }



    private void showanimation(TextView show_blinking_images) {
        show_blinking_images.setText("2");

        ScaleAnimation scale = new ScaleAnimation(0, 1, 0, 1, ScaleAnimation.RELATIVE_TO_SELF, .5f, ScaleAnimation.RELATIVE_TO_SELF, .5f);
        scale.setDuration(300);
        scale.setRepeatMode(ScaleAnimation.INFINITE);
        scale.setRepeatCount(Integer.MAX_VALUE);
        scale.setInterpolator(new OvershootInterpolator());
        show_blinking_images.startAnimation(scale);



    }

    private void Replace_fragment_main() {
        Fragment_main f = new Fragment_main();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_main, f)
                .addToBackStack(null)
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
    private void displayLocationSettingsRequest(Context context) {
        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build();
        googleApiClient.connect();

        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(10000 / 2);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(locationRequest);
        builder.setAlwaysShow(true);

        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        Log.e("location Setting", "All location settings are satisfied.");
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        Log.e("location Setting", "Location settings are not satisfied. Show the user a dialog to upgrade location settings ");

                        try {
                            // Show the dialog by calling startResolutionForResult(), and check the result
                            // in onActivityResult().
                            status.startResolutionForResult(Deshboard.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            Log.e("location Setting", "PendingIntent unable to execute request.");
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.e("location Setting", "are inadequate, and cannot be fixed here.Dialog not created.");
                        break;
                }
            }
        });
    }

    public static boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }
}
