package com.netforceinfotech.todo_tobuy.Login;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.DashBoard.Deshboard;
import com.netforceinfotech.todo_tobuy.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    TextView signup_tv;
    RelativeLayout rl_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Window window = getWindow();
        initializeview();


// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            // only for gingerbread and newer versions
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.greentranparent));
        }

    }

    private void initializeview() {
        signup_tv=(TextView)findViewById(R.id.textView5);
        rl_login=(RelativeLayout)findViewById(R.id.rl_login);
        rl_login.setOnClickListener(this);
        clickevent();

    }

    private void clickevent() {
        signup_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.textView5:
                Intent i=new Intent(LoginActivity.this,Signup.class);
                startActivity(i);
                break;
            case R.id.rl_login:
                Intent i2=new Intent(LoginActivity.this,Deshboard.class);
                startActivity(i2);

        }

    }
}
