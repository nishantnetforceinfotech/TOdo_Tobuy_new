package com.netforceinfotech.todo_tobuy.DashBoard.Offers.Strores;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Services.Beauty.Beauty_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Services.Brands.Service_brands_adpater;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Services.Food.Food_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Services.Movies.Movies_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Strores.Brands_adapter.Store_Brands_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Strores.News_adpater.News_adapter;
import com.netforceinfotech.todo_tobuy.DashBoard.Offers.Strores.Offer_adapter.Offer_adaper;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

public class Stores_activity extends AppCompatActivity {
    String class_type;
    ArrayList data=new ArrayList();
    ArrayList <Integer>stores_reference=new ArrayList<Integer>();
    ImageView Store_offers_type1,Store_offers_type2,Store_offers_type3;
    RecyclerView recyclerView_store,recyclerView_combo,recyclerView_news,recyclerView_brands;


    LinearLayoutManager rl_stores_layoutmanager,rl_combo_layoutmanager,rl_news_layoutmanager,rl_brands_layoutmanager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stores_activity);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            class_type =(String) b.get("class_type");

        }


//        if (savedInstanceState == null) {
//            Bundle extras = getIntent().getExtras();
//
//            if (extras == null) {
//                class_type = null;
//            } else {
//                class_type = extras.getString("Stores");
//            }
//        }

            stores_reference.add(R.drawable.offers_green);
            stores_reference.add(R.drawable.combo_green);
            stores_reference.add(R.drawable.news_green);
            Store_offers_type1 = (ImageView) findViewById(R.id.imageView41);
            Store_offers_type2 = (ImageView) findViewById(R.id.imageView42);
            Store_offers_type3 = (ImageView) findViewById(R.id.imageView43);


            if (stores_reference.size() != 0) {
                Store_offers_type1.setImageResource(stores_reference.get(0));
                Store_offers_type2.setImageResource(stores_reference.get(1));
                Store_offers_type3.setImageResource(stores_reference.get(2));

            }


            data.add("HEB");
            data.add("MODE");
            data.add("S-MART");
            data.add("Soriana");
            data.add("Wallmart");
            rl_stores_layoutmanager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            rl_combo_layoutmanager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            rl_news_layoutmanager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            rl_brands_layoutmanager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            initrecycle_offer();
            initrecyclecombo();
            initrecyclenews();
            initrecycle_brands();
            // Inflate the layout for this fragment


    }
    private void initrecyclecombo() {

        //Offer_storelist_adapter offer_storelist_adapter=new Offer_storelist_adapter(this,data,this.getSupportFragmentManager());

        if(class_type!=null)
        {
            if(class_type.contains("Stores"))
            { recyclerView_store=(RecyclerView)findViewById(R.id.recycleview_offers_combo);
                recyclerView_store.setLayoutManager(rl_stores_layoutmanager);
                Offer_adaper offer_storelist_adapter=new Offer_adaper(this,data,getSupportFragmentManager());


                recyclerView_store.setAdapter(offer_storelist_adapter);
            }
            else if(class_type.contains("Services"))
            { recyclerView_store=(RecyclerView)findViewById(R.id.recycleview_offers_combo);
                recyclerView_store.setLayoutManager(rl_stores_layoutmanager);
                Food_adapter food_adapter=new Food_adapter(this,data,getSupportFragmentManager());


                recyclerView_store.setAdapter(food_adapter);

            }


        }
        else {
            recyclerView_store=(RecyclerView)findViewById(R.id.recycleview_offers_combo);
            recyclerView_store.setLayoutManager(rl_stores_layoutmanager);
            Offer_adaper offer_storelist_adapter=new Offer_adaper(this,data,getSupportFragmentManager());


            recyclerView_store.setAdapter(offer_storelist_adapter);
        }



    }

    private void initrecycle_offer() {


        recyclerView_combo=(RecyclerView)findViewById(R.id.recycleview_offers);
        recyclerView_combo.setLayoutManager(rl_combo_layoutmanager);

        if(class_type!=null)
        {
            if(class_type.contains("Stores"))
            {
                Offer_adaper offer_storelist_adapter=new Offer_adaper(this,data,this.getSupportFragmentManager());
                recyclerView_combo.setAdapter(offer_storelist_adapter);
            }
            else if(class_type.contains("Services"))

            {
                Beauty_adapter beauty_adapter=new Beauty_adapter(this,data,this.getSupportFragmentManager());


                recyclerView_combo.setAdapter(beauty_adapter);

            }


        }
        else{
            Offer_adaper offer_storelist_adapter=new Offer_adaper(this,data,this.getSupportFragmentManager());
            recyclerView_combo.setAdapter(offer_storelist_adapter);
        }





    }

    private void initrecycle_brands() {

        recyclerView_brands=(RecyclerView)findViewById(R.id.recycleview_brands);
        recyclerView_brands.setLayoutManager(rl_brands_layoutmanager);


        if(class_type!=null)
        {
            if(class_type.contains("Stores"))
            {
                Store_Brands_adapter offer_storelist_adapter=new Store_Brands_adapter(this,data,this.getSupportFragmentManager());
                //Offer_storelist_adapter offer_storelist_adapter=new Offer_storelist_adapter(this,data,this.getSupportFragmentManager());
                recyclerView_brands.setAdapter(offer_storelist_adapter);
            }
            else if(class_type.contains("Services"))
            {Service_brands_adpater service_brands_adpater=new Service_brands_adpater(this,data,this.getSupportFragmentManager());


                recyclerView_brands.setAdapter(service_brands_adpater);

            }


        }
        else
        {
            Store_Brands_adapter offer_storelist_adapter=new Store_Brands_adapter(this,data,this.getSupportFragmentManager());
            //Offer_storelist_adapter offer_storelist_adapter=new Offer_storelist_adapter(this,data,this.getSupportFragmentManager());
            recyclerView_brands.setAdapter(offer_storelist_adapter);
        }






    }

    private void initrecyclenews() {

        recyclerView_news=(RecyclerView)findViewById(R.id.recycleview_news);
        recyclerView_news.setLayoutManager(rl_news_layoutmanager);


        if(class_type!=null)
        {
            if(class_type.contains("Stores"))
            {
                News_adapter offer_storelist_adapter=new News_adapter(this,data,this.getSupportFragmentManager());

                // Offer_storelist_adapter offer_storelist_adapter=new Offer_storelist_adapter(this,data,this.getSupportFragmentManager());
                recyclerView_news.setAdapter(offer_storelist_adapter);
            }
            else if(class_type.contains("Services"))
            {Movies_adapter movies_adapter=new Movies_adapter(this,data,this.getSupportFragmentManager());


                recyclerView_news.setAdapter(movies_adapter);

            }


        }
        else{
            News_adapter offer_storelist_adapter=new News_adapter(this,data,this.getSupportFragmentManager());

            // Offer_storelist_adapter offer_storelist_adapter=new Offer_storelist_adapter(this,data,this.getSupportFragmentManager());
            recyclerView_news.setAdapter(offer_storelist_adapter);
        }





    }


    }

