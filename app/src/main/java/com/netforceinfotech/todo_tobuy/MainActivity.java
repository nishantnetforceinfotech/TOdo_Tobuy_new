package com.netforceinfotech.todo_tobuy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.netforceinfotech.todo.task.TodoDashboardActivity;
import com.netforceinfotech.todo_tobuy.DashBoard.Deshboard;
import com.netforceinfotech.todo_tobuy.Login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    RelativeLayout rl;
   public static String userid;
    int a;
    protected static final int REQUEST_CHECK_SETTINGS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rl=(RelativeLayout)findViewById(R.id.rl_spalsh);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {



                // do something
                SharedPreferences pref = getApplicationContext().getSharedPreferences("RememberMe", MODE_PRIVATE);
                boolean bool = pref.getBoolean("remember", false);
                SharedPreferences pref1 = getApplicationContext().getSharedPreferences("ToDo-ToBuy", 0);
                String chkto = pref1.getString("chkto", "tobuy");
            userid=pref1.getString("userId","demo");


                if(bool == false||(userid.contains("demo"))) {

    startActivity(new Intent(MainActivity.this, LoginActivity.class));
    overridePendingTransition(R.anim.enter, R.anim.exit);
    finish();

                }else {

                    if(chkto.equals("tobuy")){
Log.e("userid",userid);
                        startActivity(new Intent(MainActivity.this, Deshboard.class));
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        finish();


                    }else {

                        startActivity(new Intent(MainActivity.this, TodoDashboardActivity.class));
                        overridePendingTransition(R.anim.enter, R.anim.exit);
                        finish();

                    }

                }
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                overridePendingTransition(R.anim.enter, R.anim.exit);
//                finish();
            }
        }, 2000);
    }
    }

