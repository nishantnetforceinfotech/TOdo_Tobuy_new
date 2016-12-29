package com.netforceinfotech.todo_tobuy.Login;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.netforceinfotech.genral.Validation;
import com.netforceinfotech.todo_tobuy.R;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class Signup extends AppCompatActivity {

    @InjectView(R.id.f_name)
    EditText f_name;
    @InjectView(R.id.l_name)
    EditText l_name;
    @InjectView(R.id.spin_country)
    Spinner spin_country;
    @InjectView(R.id.spin_state)
    Spinner spin_loc_dis;
    @InjectView(R.id.spin_loc_dis)
    Spinner spin_state;
    @InjectView(R.id.email_id)
    EditText email_id;
    @InjectView(R.id.username)
    EditText username;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.conf_password)
    EditText conf_password;
    @InjectView(R.id.signup)
    ImageView signup;
    @InjectView(R.id.already_login)
    RelativeLayout already_login;
    ArrayList<String> Country_ids=new ArrayList<>();
    ArrayList<String> Country_names=new ArrayList<>();
    ArrayList<String> State_ids=new ArrayList<>();
    ArrayList<String> State_names=new ArrayList<>();

    String countrylist[] = {"Country"};

    String statelist[] = {"State"};
String locationlist[]={"Select Distance","5 KM","10 KM","15 KM","20 KM","25 KM","30 KM","35 KM","40 KM","45 KM","50 KM","55 KM","60 KM","More then 60 Km"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.inject(this);
//click signup
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(Validation.hasText(f_name)||Validation.hasText(l_name)||Validation.hasText(email_id)||Validation.hasText(username)
                        ||Validation.hasText(password)||Validation.hasText(conf_password))
                {
                    if(Validation.isEmailAddress(email_id,true))
                    {
                        if (password.getText().toString().trim().equals(conf_password.getText().toString().trim())) {
                            JsonObject js=new JsonObject();
                            js.addProperty("fname",f_name.getText().toString());
                            js.addProperty("lname", l_name.getText().toString());
                            js.addProperty("email", email_id.getText().toString());
                            js.addProperty("password", password.getText().toString());
                            js.addProperty("country", Country_ids.get(spin_country.getSelectedItemPosition()));
                            js.addProperty("state", State_ids.get(spin_state.getSelectedItemPosition()));
                            js.addProperty("email", email_id.getText().toString());


                         String url="http://netforce.biz/todotobuy/users/signup";
                            Ion.with(Signup.this)
                                    .load(url)
                                    .setJsonObjectBody(js)
                                    .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
                                @Override
                                public void onCompleted(Exception e, JsonObject result) {
                                    String aa = result.get("success").getAsString();
                                    if(aa.contains("1"))
                                    {
                                        Toast.makeText(Signup.this,"Sucessfully Signup",Toast.LENGTH_SHORT).show();
                                        Intent i =new Intent(Signup.this,LoginActivity.class);
                                        startActivity(i);
                                        finish();

                                    }
                                    else{
                                        Toast.makeText(Signup.this,"Error in Signup",Toast.LENGTH_SHORT).show();
                                        Log.e("result",result.toString());
                                    }



                                }
                            });





                        }
                        else{  Toast.makeText(Signup.this,"please enter same password and confirm password",Toast.LENGTH_SHORT).show();}

                    }
                    else{

                        Toast.makeText(Signup.this,"Email address not valid",Toast.LENGTH_SHORT).show();
                    }





                }
                else{
                    Log.e("password",password.getText().toString().trim()+"***"+conf_password.getText().toString());
                    Toast.makeText(Signup.this,"fill  all require fields",Toast.LENGTH_SHORT).show();
                }
            }
        });









        allClickEvents();
        setlocation_spinner();
        callwebservice();
       spin_country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               State_ids.clear();
               State_names.clear();
               Ion.with(Signup.this)
                       .load("http://netforce.biz/todotobuy/users/states/" + Country_ids.get(i).toString())

                       .asJsonObject().setCallback(new FutureCallback<JsonObject>() {
                   @Override
                   public void onCompleted(Exception e, JsonObject result) {
                       if (result != null) {

                           JsonArray re = result.getAsJsonArray("data");


                           for (int i = 0; i < re.size(); i++) {

                               JsonObject jsonObject = (JsonObject) re.get(i);
                               JsonObject vo = jsonObject.getAsJsonObject("States");
                               String stete_id = vo.get("id").getAsString();
                               String state_name = vo.get("name").getAsString();
                               State_ids.add(stete_id);
                               State_names.add(state_name);

                           }

                           setStatespinner(State_ids, State_names);


                       }

                   }
               });
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });
        }

    private void setlocation_spinner() {


        ArrayAdapter<String> spinnerArrayAdapter_loc = new ArrayAdapter<String>(this, R.layout.spinnertext, locationlist); //selected item will look like a spinner set from XML
        spinnerArrayAdapter_loc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_loc_dis.setAdapter(spinnerArrayAdapter_loc);



    }

    private void setStatespinner(ArrayList<String> state_ids, ArrayList<String> state_names) {

        ArrayAdapter<String> spinnerArrayAdapter_state = new ArrayAdapter<String>(this, R.layout.spinnertext, state_names); //selected item will look like a spinner set from XML
        spinnerArrayAdapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_state.setAdapter(spinnerArrayAdapter_state);
    }

    private void callwebservice() {
        Country_ids.clear();
        Country_names.clear();
        Ion.with(this)
                .load("http://netforce.biz/todotobuy/users/countries")

                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        if (result != null) {

                            JsonArray re = result.getAsJsonArray("data");


                            for (int i = 0; i < re.size(); i++) {

                                JsonObject jsonObject = (JsonObject) re.get(i);
                                JsonObject vo = jsonObject.getAsJsonObject("Country");
                                String country_id = vo.get("id").getAsString();
                                String country_name = vo.get("country_name").getAsString();
                                Country_ids.add(country_id);
                                Country_names.add(country_name);

                            }

                            setCountryspinner(Country_ids,Country_names);


                        }

                    }
                });



    }

    private void allClickEvents(){

         already_login.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 finish();
             }
         });
     }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }

    private void setCountryspinner(ArrayList<String> country_ids, ArrayList<String> Country_names) {


        // country spinner
        ArrayAdapter<String> spinnerArrayAdapter_country = new ArrayAdapter<String>(this, R.layout.spinnertext, Country_names); //selected item will look like a spinner set from XML
        spinnerArrayAdapter_country.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_country.setAdapter(spinnerArrayAdapter_country);


        // state spinner




    }
}
