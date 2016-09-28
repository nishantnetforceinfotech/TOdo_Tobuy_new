package com.netforceinfotech.todo_tobuy.Login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.netforceinfotech.todo_tobuy.DashBoard.Deshboard;
import com.netforceinfotech.todo_tobuy.R;

import javax.xml.validation.Validator;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class LoginActivity extends AppCompatActivity {

    @InjectView(R.id.username)
    EditText username;
    @InjectView(R.id.password)
    EditText password;
    @InjectView(R.id.rl_login)
    RelativeLayout rl_login;
    @InjectView(R.id.cbox_remember)
    CheckBox cbox_remember;
    @InjectView(R.id.forgot_pass)
    TextView forgot_pass;
    @InjectView(R.id.rel_signup)
    RelativeLayout rel_signup;
    @InjectView(R.id.tobuy)
    CheckBox tobuy;
    @InjectView(R.id.todo)
    CheckBox todo;

    Boolean bool = true;
    String chk_to = "tobuy";
    SharedPreferences pref,pref1;
    SharedPreferences.Editor editor,editor1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // only for gingerbread and newer versions
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.greentranparent));
        }

        ButterKnife.inject(this);

        pref = getApplicationContext().getSharedPreferences("RememberMe", MODE_PRIVATE);
        editor = pref.edit();

        pref1 = getApplicationContext().getSharedPreferences("ToDo-ToBuy", MODE_PRIVATE);
        editor1 = pref1.edit();

        clickEvent();

    }

    /**
     * Perform all events
     */
    private void clickEvent() {

        cbox_remember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (cbox_remember.isChecked()) {

                    bool = true;

                } else {

                    bool = false;

                }
            }
        });

        tobuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(todo.isChecked()){

                    todo.setChecked(false);
                    tobuy.setChecked(true);
                    chk_to = "tobuy";


                }else {

                    todo.setChecked(false);
                    tobuy.setChecked(true);
                    chk_to = "tobuy";

                }
            }
        });

        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tobuy.isChecked()){

                    todo.setChecked(true);
                    tobuy.setChecked(false);
                    chk_to = "todo";

                }else {

                    todo.setChecked(true);
                    tobuy.setChecked(false);
                    chk_to = "todo";

                }

            }
        });

        rl_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                editor.putBoolean("remember", bool);
                editor.commit();

                editor1.putString("chkto", chk_to);
                editor1.commit();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

                if (email.matches(emailPattern)) {
                    Toast.makeText(getApplicationContext(), "valid email address", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }
                Intent i2 = new Intent(LoginActivity.this, Deshboard.class);
                startActivity(i2);
                finish();
            }
        });

        forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i3 = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(i3);
            }
        });

        rel_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, Signup.class);
                startActivity(i);
            }
        });
    }

}
