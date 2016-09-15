package com.netforceinfotech.todo_tobuy.DashBoard.Offers.Coupons;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.netforceinfotech.todo_tobuy.R;

public class Coupons_activity extends AppCompatActivity {
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView_coupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupons_activity);
        gridLayoutManager =  new GridLayoutManager(this,3);
        initRecycleview();

    }

    private void initRecycleview() {




    }
}
