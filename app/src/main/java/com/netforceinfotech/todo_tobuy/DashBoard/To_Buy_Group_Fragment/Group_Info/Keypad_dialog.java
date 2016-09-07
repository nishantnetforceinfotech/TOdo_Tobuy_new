package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.main.Group_recycleview_subfragment_main;
import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by abcd on 8/31/2016.
 */
public class Keypad_dialog extends DialogFragment implements View.OnClickListener{


    EditText number_displayer;
    Button one,two,three,four,five,six,seven,eight,nine,dot,cancel_one_value;
RelativeLayout rl_clear_all,rl_done;
    ImageView close_dialog;
    String position;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


  position=getArguments().getString("et_position");

        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = RecyclerView.LayoutParams.WRAP_CONTENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        View rootView = inflater.inflate(R.layout.dialog_keypad, container, false);
        initializeview(rootView);






        // Do something else
        return rootView;
    }

    private void initializeview(View rootView) {
        
        
        
        
        number_displayer=(EditText)rootView.findViewById(R.id.editText11);
        one=(Button)rootView.findViewById(R.id.button5);
        two=(Button)rootView.findViewById(R.id.button6);
        three=(Button)rootView.findViewById(R.id.button7);
        four=(Button)rootView.findViewById(R.id.button8);
        five=(Button)rootView.findViewById(R.id.button9);
        six=(Button)rootView.findViewById(R.id.button10);
        seven=(Button)rootView.findViewById(R.id.button11);
        eight=(Button)rootView.findViewById(R.id.button12);
        nine=(Button)rootView.findViewById(R.id.button13);
        dot=(Button)rootView.findViewById(R.id.button14);
        cancel_one_value=(Button)rootView.findViewById(R.id.button15);
        rl_done=(RelativeLayout)rootView.findViewById(R.id.rl_done);
        rl_clear_all=(RelativeLayout)rootView.findViewById(R.id.rl_clear_all);
        close_dialog=(ImageView)rootView.findViewById(R.id.imageView28);
        setonclick_inview();

        


    }

    private void setonclick_inview() {
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        dot.setOnClickListener(this);
        cancel_one_value.setOnClickListener(this);
        rl_done.setOnClickListener(this);
        rl_clear_all.setOnClickListener(this);
        close_dialog.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.button5:

                number_displayer.append("1");
                break;
            case R.id.button6:
                number_displayer.append("2");
                break;

            case R.id.button7:
                number_displayer.append("3");
                break;
            case R.id.button8:
                number_displayer.append("4");
                break;
            case R.id.button9:
                number_displayer.append("5");
                break;
            case R.id.button10:
                number_displayer.append("6");
                break;
            case R.id.button11:
                number_displayer.append("7");
                break;
            case R.id.button12:
                number_displayer.append("8");
                break;
            case R.id.button13:
                number_displayer.append("9");
                break;
            case R.id.button14:
                number_displayer.append(".");
                break;
            case R.id.button15:
                if(number_displayer.getText().length()>=1) {
                    String str = number_displayer.getText().toString();
                    str = str.substring(0, str.length() - 1);
// Now set this Text to your edit text
                    number_displayer.setText(str);
                }
                break;

            case R.id.rl_clear_all:
                number_displayer.setText("");
                break;
            case R.id.imageView28:
               getDialog().dismiss();

                break;
            case R.id.rl_done:
                number_displayer.getText().toString();
                View v2=(LinearLayout) Group_recycleview_subfragment_main.recycle_itemlist.getChildAt(Integer.parseInt(position));
                TextView et1=(TextView)v2.findViewById(R.id.editText10);
                      et1.setText(number_displayer.getText().toString());

                getDialog().dismiss();



                break;

        }
        
        
        

    }
}
