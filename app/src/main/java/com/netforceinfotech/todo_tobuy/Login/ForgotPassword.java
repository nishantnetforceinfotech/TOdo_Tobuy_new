package com.netforceinfotech.todo_tobuy.Login;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.netforceinfotech.todo_tobuy.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by owner on 9/28/2016.
 */
public class ForgotPassword extends AppCompatActivity {

    @InjectView(R.id.email_id)
    EditText email_id;
    @InjectView(R.id.rel_send)
    RelativeLayout rel_send;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password);
        Window window = getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            // only for gingerbread and newer versions
            window.setStatusBarColor(ContextCompat.getColor(getApplicationContext(), R.color.greentranparent));
        }

        ButterKnife.inject(this);

    }
}
