package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.koushikdutta.ion.ProgressCallback;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.GroupData;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter.Group_recycleview_subfragment;
import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main.Item_recycler_adapter;
import com.netforceinfotech.todo_tobuy.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Group_Fragment_tobuy#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Group_Fragment_tobuy extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Item_recycler_adapter item_recycler_adapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    int searchflag;
    private String mParam2;
    ArrayList get_checked_item, get_unselected_items;
    LinearLayoutManager rl_itemlist_layoutmanager;
    RelativeLayout rl_imagegetter;
    RecyclerView recycle_itemlist;

    ProgressDialog pp;
    ArrayList checked_arraylist;
    ArrayList<GroupData> groupDatas = new ArrayList<>();
    public static ImageView get_cameraorgalley_image;
    public static Bitmap item_image;
    public  static File item_file;
    ImageView search_item,add_item,done,back_to_previous;
    Searchdatas_pojo s_pojo;
    //ArrayList<SearchData> searchdatas;
    ArrayList<Searchdatas_pojo> searchdatas=new ArrayList<Searchdatas_pojo>();
    ArrayList<ToBuyData> toBuyDatas=new ArrayList<>();
   /* ArrayList<String> item_ids=new ArrayList<>();
    ArrayList<String> item_quantity=new ArrayList<>();*/
    EditText enter_search_item,group_name ;

    public Group_Fragment_tobuy() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Group_Fragment_tobuy.
     */
    // TODO: Rename and change types and number of parameters
    public static Group_Fragment_tobuy newInstance(String param1, String param2) {
        Group_Fragment_tobuy fragment = new Group_Fragment_tobuy();
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

        View v = inflater.inflate(R.layout.fragment_group__fragment_tobuy, container, false);
        checked_arraylist = new ArrayList();
        get_unselected_items = new ArrayList();
        searchdatas = new ArrayList();
        pp=new ProgressDialog(getActivity());
        get_cameraorgalley_image = (ImageView) v.findViewById(R.id.imageView18);
        enter_search_item=(EditText)v.findViewById(R.id.editText9);
        group_name=(EditText)v.findViewById(R.id.group_name);
        search_item=(ImageView)v.findViewById(R.id.imageView20);
        add_item=(ImageView)v.findViewById(R.id.imageView21);
        back_to_previous=(ImageView)v.findViewById(R.id.imageView23);

        rl_imagegetter = (RelativeLayout) v.findViewById(R.id.rl_imagegetter);
        done = (ImageView) v.findViewById(R.id.imageView22);
        done.setOnClickListener(this);
        rl_imagegetter.setOnClickListener(this);

        get_checked_item = new ArrayList();

        item_image = null;
        item_file = null;
        back_to_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        // Add items
        add_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (enter_search_item.getText().length() != 0) {
                    if(searchflag==1)
                    { Intializeecycleview();
                        enter_search_item.setText("");
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"Initialy Search Item and try again",Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getActivity(), "please enter item name ", Toast.LENGTH_SHORT).show();
                }

            }
        });


        recycle_itemlist = (RecyclerView) v.findViewById(R.id.recycleview_items);

        rl_itemlist_layoutmanager = new LinearLayoutManager(getActivity());

        recycle_itemlist.setLayoutManager(rl_itemlist_layoutmanager);


         group_name = (EditText) v.findViewById(R.id.group_name);
        //click search item

        search_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(enter_search_item.getText().length()>1)
                {searchflag=1;
                   // item_ids.clear();
                    searchdatas.clear();
                 //  item_quantity.clear();
                    pp=new ProgressDialog(getActivity());
                    pp.show();
                    String search_url="http://netforce.biz/todotobuy/products/buy_items";
                    JsonObject js=new JsonObject();
                    js.addProperty("name",enter_search_item.getText().toString());
                    Ion.with(Group_Fragment_tobuy.this)
                            .load(search_url)
                            .setJsonObjectBody(js)
                            .asJsonObject()
                            .setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    if(result!=null) {


                                        final ArrayList aa = new ArrayList();

                                        String status = result.get("status").getAsString();
                                        if (status.contains("1")) {

                                            JsonArray data = result.getAsJsonArray("data");

                                            for (int i = 0; i < data.size(); i++)

                                            {
                                                JsonObject js2 = data.get(i).getAsJsonObject().getAsJsonObject("BuyItem");

                                                String item_id = js2.get("id").getAsString();
                                                String item_name = js2.get("name").getAsString();
                                                Log.e("result_", item_id + "****" + item_name);
                                                searchdatas.add(new Searchdatas_pojo(item_id, "0", item_name));
                                                //  Log.e("searchdatas",searchdatas.toString());
                                                //   item_ids.add(item_id);
                                                //  item_quantity.add("0");
                                                aa.add(item_name);

                                            }


                                        }


                                        Log.e("result", result.toString());
                                        new MaterialDialog.Builder(getActivity())
                                                .items(aa).listSelector(R.color.colorPrimaryLight).canceledOnTouchOutside(true).title("Select Items").titleColorRes(R.color.colorPrimaryLight).itemsColorRes(R.color.white)
                                                .itemsCallback(new MaterialDialog.ListCallback() {
                                                    @Override
                                                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {

                                                        enter_search_item.setText(aa.get(which).toString());

                                                        ToBuyData toBuyData = new ToBuyData(searchdatas.get(which).id, searchdatas.get(which).itemname, searchdatas.get(which).quantity);

//                                                        if(!toBuyDatas.contains(toBuyData)){
                                                        toBuyDatas.add(toBuyData);
                                                        // }

                                                    }
                                                })
                                                .show();


                                        if (pp != null) {
                                            pp.dismiss();
                                        }
//                                        Intent i2 = new Intent(getActivity(), Deshboard.class);
//                                        startActivity(i2);
//                                        getActivity().overridePendingTransition(R.anim.enter, R.anim.exit);
//
//                                        getActivity().finish();
                                    }
                                    else {
                                       Toast.makeText(getActivity(),"server Error",Toast.LENGTH_SHORT).show();
                                    }

                                }
                            });



                }
                else{

                }

            }
        });



        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        if(item_image != null){

            get_cameraorgalley_image.setImageBitmap(item_image);
        }
    }

    private void setupDummyData() {
//        for (int i = 0; i < 10; i++) {
//            groupDatas.add(new GroupData("", "", false, false,false));
//        }
        groupDatas.add(new GroupData(enter_search_item.getText().toString(), "", false, false,false));

        item_recycler_adapter = new Item_recycler_adapter(getActivity(), groupDatas, toBuyDatas);
        recycle_itemlist.setAdapter(item_recycler_adapter);




        Log.e("enter_search_item", enter_search_item.getText().toString());
    }

    private void Intializeecycleview() {
        setupDummyData();


        group_name.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {

                    Toast.makeText(getActivity(), "press enter", Toast.LENGTH_LONG).show();
                    handled = true;
                }
                return handled;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_imagegetter:
                showdialogmedia();
                break;
            case R.id.imageView22:
                creat_group_webservice();
              //  list_checked_items();
                break;


        }

    }

    private void creat_group_webservice() {



item_recycler_adapter.notifyDataSetChanged();

 for(int i=0;i<toBuyDatas.size();i++)
 {


     Log.e("toBuyDatas.quantity",toBuyDatas.get(i).quantity);
 }
        if(group_name.getText().length()!=0) {
            if (item_file  != null)
            {

                try {
                    PrepareJsondata();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else{
                Toast.makeText(getActivity(),"please select image or capture",Toast.LENGTH_SHORT).show();
            }
        }
        else{
           Toast.makeText(getActivity(),"please enter groupname first",Toast.LENGTH_SHORT).show();
        }







    }

    private void PrepareJsondata() throws JSONException {
        SharedPreferences pref1 = getActivity().getApplicationContext().getSharedPreferences("ToDo-ToBuy", 0);
        //File f=CreateFile(item_image);

        Log.e("imagepath",item_file.getAbsolutePath().toString());
        SharedPreferences.Editor editor1 = pref1.edit();
        String userid=pref1.getString("userId", "demo");
        Gson gson = new GsonBuilder().create();
        JsonArray myCustomArray = gson.toJsonTree(toBuyDatas).getAsJsonArray();
//        JsonObject js=new JsonObject();
//        js.addProperty("user_id", userid);
//        js.add("items", myCustomArray);

       // js.addProperty("groupname",group_name.getText().toString());


//        String nn=myCustomArray.getAsString();
//        JSONArray js=new JSONArray(nn);
//        Log.e("groupname",group_name.getText().toString());
//       Log.e("item_file", item_file.toString());
//        Log.e("user_id", userid);
//        JSONArray jsArray = new JSONArray(toBuyDatas);
//        JSONObject js=new JSONObject();
//        js.put("item",jsArray);
        //Log.e("js+jsonArray",js.toString()+"*******"+jsArray);

        //JsonObject itemdata = new JsonObject();

            //itemdata.addProperty("items", myCustomArray.toString());
        pp=new ProgressDialog(getActivity());
        pp.show();

            String creategroup="http://netforce.biz/todotobuy/products/create_group";
//            JsonObject js=new JsonObject();
//            js.addProperty("name", enter_search_item.getText().toString());

     //   Log.e("jsondata",myCustomArray.toString());
            Ion.with(Group_Fragment_tobuy.this)
                    .load(creategroup)
                    .setMultipartFile("groupimage", "image*//*", item_file)
                    //.setMultipartParameter("items", js.toString())
                    .setMultipartParameter("items", String.valueOf(myCustomArray))
                    .setMultipartParameter("user_id", userid)
                    .setMultipartParameter("groupname", group_name.getText().toString())
                    .asString()
                .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String result) {

                            try {


                                if (result != null) {
                                    Log.e("eeeee", result);

                                JSONObject js2=new JSONObject(result);


                                String status=js2.getString("status");
                                if(status.contains("success"))
                                {
                                    Toast.makeText(getActivity(),"group created sucessfully",Toast.LENGTH_SHORT).show();



                                }
                                } else {
                                    getActivity().getSupportFragmentManager().popBackStack();
                                    Log.e("eeeee2", e.toString());
                                }
                            } catch (JSONException e1) {
                               Log.e("JSONException",e1.toString());
                            }
                            if (pp != null) {
                                pp.dismiss();
                            }
                            //Log.e("eeeee",result);

                        }
                    });


       // Log.e("myCustomArray", myCustomArray.toString());


    }

    private void list_checked_items() {

        get_checked_item.clear();
        get_unselected_items.clear();
//        for (int i = 0; i < Group_recycleview_subfragment_main.recycle_itemlist.getChildCount(); i++) {
//            View v2 = (LinearLayout) Group_recycleview_subfragment_main.recycle_itemlist.getChildAt(i);
//            CheckBox ch_bx = (CheckBox) v2.findViewById(R.id.checkBox3);
//            if (ch_bx.isChecked()) {
//                get_checked_item.add(i);
//
//
//            } else {
//                get_unselected_items.add(i);
////               int k= recycle_itemlist.getChildLayoutPosition(ch_bx);
////                Log.e("get unchecked item",""+k);
//            }
//
//        }
        Group_recycleview_subfragment f = new Group_recycleview_subfragment();
        Bundle b = new Bundle();
        b.putStringArrayList("checked_item", get_checked_item);
        f.setArguments(b);


//        getActivity().getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.fl_groupdata, f,"fl_groupdata")
//                .addToBackStack("fl_groupdata")
//                .commit();
//        Log.e("list of checked item", get_checked_item.toString());
//        Log.e("list of unchecked_item", get_unselected_items.toString());
//        Log.e("child_count", "" + Group_recycleview_subfragment_main.recycle_itemlist.getChildCount());


        // EditText et1=(EditText)v2.findViewById(R.id.editText10);

    }

    private void showdialogmedia() {
        Dialog_media_fragment dialog_fragment = new Dialog_media_fragment();
        dialog_fragment.show(getActivity().getFragmentManager(), "Dialog_media_fragment");


    }



   public  File CreateFile(Bitmap bp)
   {       File f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Camera/", String.valueOf(System.currentTimeMillis())+".jpg");

       try {




       Log.e("filpath+path",Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/Camera/"+"****"+String.valueOf(System.currentTimeMillis())+".jpg");

           f.createNewFile();


//Convert bitmap to byte array
       Bitmap bitmap = bp;
       ByteArrayOutputStream bos = new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
       byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
       FileOutputStream fos = new FileOutputStream(f);
       fos.write(bitmapdata);
       fos.flush();
       fos.close();
       } catch (IOException e) {
         Log.e("IOException",e.toString());
       }
       return f;
   }

}
