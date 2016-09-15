package com.netforceinfotech.todo_tobuy.DashBoard.To_Buy_Group_Fragment.Group_Info.Group_checked_adapter;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by abcd on 9/6/2016.
 */
public class Confirm_delete_item extends DialogFragment {


    public Confirm_delete_item() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
        params.width = RecyclerView.LayoutParams.MATCH_PARENT;
        params.height = RelativeLayout.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
        View rootView = inflater.inflate(R.layout.delete_confirm_dialog, container, false);
        ImageView sure=(ImageView)rootView.findViewById(R.id.imageView29);
        ImageView  confirm_delete=(ImageView)rootView.findViewById(R.id.imageView30);
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_Alert_dialog();
                getDialog().dismiss();

            }


        });

        confirm_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });




        // Do something else
        return rootView;
    }

    private void show_Alert_dialog() {
        //Creating the LayoutInflater instance
        LayoutInflater li = getActivity().getLayoutInflater();
        //Getting the View object as defined in the customtoast.xml file
        View layout = li.inflate(R.layout.alert_sure_delete,
                (ViewGroup) getActivity().findViewById(R.id.custom_toast_layout));
        Toast toast = new Toast(getActivity());
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();


        //if use Alert Dialogbox then use it


//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
//// ...Irrelevant code for customizing the buttons and title
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View dialogView = inflater.inflate(R.layout.alert_sure_delete, null);
//        dialogBuilder.setView(dialogView);
//
//
//        AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();


    }


}
