package com.netforceinfotech.todo.task.list.folder.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.netforceinfotech.todo_tobuy.R;

import java.util.Calendar;

/**
 * Created by owner on 10/14/2016.
 */
public class ListAddtaskFragment extends Fragment {
    TextView selectdate, noticetext,assigntxt,subtasktxt,notetxt;
    ImageView clear_date, clear_notify, clear_subtask, clear_notes, clear_file_selction;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_sub_fragment_add_task_list, container, false);


        //initialize views
        //images
        clear_date = (ImageView) view.findViewById(R.id.imageView_clear_date);
        clear_notify = (ImageView) view.findViewById(R.id.imageView_clear_notice);
        clear_subtask = (ImageView) view.findViewById(R.id.imageView_clear_subtask);
        clear_notes = (ImageView) view.findViewById(R.id.imageView_clear_note);
        clear_file_selction = (ImageView) view.findViewById(R.id.imageView_clear_file);
        //textsviews
        selectdate = (TextView) view.findViewById(R.id.datetxt);
        noticetext=(TextView) view.findViewById(R.id.noticetxt);
        assigntxt=(TextView)view.findViewById(R.id.assigntxt);
        subtasktxt=(TextView)view.findViewById(R.id.suntasktxt);
        notetxt=(TextView)view.findViewById(R.id.suntasktxt);


        //clear calender selction

        clear_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectdate.setText("Due Date:");
            }
        });


        //clear notify selction


        clear_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        //  date selction
        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar now = Calendar.getInstance();
                com.wdullaer.materialdatetimepicker.date.DatePickerDialog dpd = com.wdullaer.materialdatetimepicker.date.DatePickerDialog.newInstance(
                        new com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(com.wdullaer.materialdatetimepicker.date.DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                                String dayOfTheWeek = (String) android.text.format.DateFormat.format("EEEE", dayOfMonth);//Thursday
                                String stringMonth = (String) android.text.format.DateFormat.format("MMM", dayOfMonth); //Jun
                                String date = "Due on " + dayOfTheWeek + "," + (stringMonth) + "" + monthOfYear;
                                selectdate.setText(date);
                                selectdate.setTextColor(getResources().getColor(R.color.dateformate));
                            }
                        },
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );

                dpd.show(getActivity().getFragmentManager(), "Datepickerdialog");


            }
        });

        return view;
    }


}
