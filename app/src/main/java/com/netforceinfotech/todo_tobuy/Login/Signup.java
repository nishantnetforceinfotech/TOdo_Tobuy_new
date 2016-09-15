package com.netforceinfotech.todo_tobuy.Login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.netforceinfotech.todo_tobuy.R;

public class Signup extends AppCompatActivity {

    Spinner spin_country,spin_state;
    String countrylist[]={"Country"} ;

    String statelist[]={"State"} ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initializeview();
    }

    private void initializeview() {
        spin_country=(Spinner)findViewById(R.id.spinner);
        spin_state=(Spinner)findViewById(R.id.spinner2);
        setspinner();
    }

    private void setspinner() {


        // country spinner
        ArrayAdapter<String> spinnerArrayAdapter_country = new ArrayAdapter<String>(this, R.layout.spinnertext, countrylist); //selected item will look like a spinner set from XML
        spinnerArrayAdapter_country.setDropDownViewResource(R.layout.spinnertext);
        spin_country.setAdapter(spinnerArrayAdapter_country);


        // state spinner

        ArrayAdapter<String> spinnerArrayAdapter_state = new ArrayAdapter<String>(this,R.layout.spinnertext, statelist); //selected item will look like a spinner set from XML
        spinnerArrayAdapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin_state.setAdapter(spinnerArrayAdapter_state);




    }
}
