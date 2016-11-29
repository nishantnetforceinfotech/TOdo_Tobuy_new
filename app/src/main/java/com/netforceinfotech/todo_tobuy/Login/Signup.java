package com.netforceinfotech.todo_tobuy.Login;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.netforceinfotech.todo_tobuy.R;

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

    String countrylist[] = {"Country"};

    String statelist[] = {"State"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        ButterKnife.inject(this);
        allClickEvents();
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

    private void setspinner() {


        // country spinner
        ArrayAdapter<String> spinnerArrayAdapter_country = new ArrayAdapter<String>(this, R.layout.spinnertext, countrylist); //selected item will look like a spinner set from XML
        spinnerArrayAdapter_country.setDropDownViewResource(R.layout.spinnertext);
        spin_country.setAdapter(spinnerArrayAdapter_country);


        // state spinner

        ArrayAdapter<String> spinnerArrayAdapter_state = new ArrayAdapter<String>(this, R.layout.spinnertext, statelist); //selected item will look like a spinner set from XML
        spinnerArrayAdapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_state.setAdapter(spinnerArrayAdapter_state);


    }
}
