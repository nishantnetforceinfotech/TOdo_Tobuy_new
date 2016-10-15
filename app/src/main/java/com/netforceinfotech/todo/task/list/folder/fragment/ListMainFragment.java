package com.netforceinfotech.todo.task.list.folder.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.netforceinfotech.todo_tobuy.R;

/**
 * Created by owner on 10/14/2016.
 */
public class ListMainFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todo_main_inbox_fragment, container, false);

        return view;
    }
}

