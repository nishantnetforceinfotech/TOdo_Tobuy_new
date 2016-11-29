package com.netforceinfotech.todo.task.dashboard.mainfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netforceinfotech.database.Category_pojo;
import com.netforceinfotech.genral.Global_Variable;
import com.netforceinfotech.todo.task.TodoListFolderActivity;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.ItemTouchHelperAdapter;
import com.netforceinfotech.todo_tobuy.DashBoard.grid.helper.OnStartDragListener;
import com.netforceinfotech.todo_tobuy.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by owner on 10/13/2016.
 */
public class Subfragment_listAdapter extends RecyclerView.Adapter<CommomHolder_list> {
    // implements ItemTouchHelperAdapter {

    Context context2;
    ArrayList<Category_pojo> commomDatas, commomDatas1;
    // private final OnStartDragListener mDragStartListener;


    public Subfragment_listAdapter(Context context,
                                   // OnStartDragListener dragStartListener,
                                   ArrayList<Category_pojo> commomDatas) {
        context2 = context;
        //  mDragStartListener = dragStartListener;
        this.commomDatas = commomDatas;
        this.commomDatas1 = commomDatas1;
    }

    @Override
    public CommomHolder_list onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_dashboard_sub_fragment_items, parent, false);
        CommomHolder_list viewHolder = new CommomHolder_list(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CommomHolder_list holder, final int position) {
        if (commomDatas.get(position).getList_name().equals("Urgent")) {
            Picasso.with(context2).load(R.drawable.star_icon).into(holder.img);
        } else if (commomDatas.get(position).getList_name().equals("Today") ||
                commomDatas.get(position).getList_name().equals("Tomorrow") ||
                commomDatas.get(position).getList_name().equals("7 Days") ||
                commomDatas.get(position).getList_name().equals("Calender")) {
            Picasso.with(context2).load(R.drawable.calender_icon).into(holder.img);
        } else {
            Picasso.with(context2).load(R.drawable.list_icon).into(holder.img);

        }


        holder.msg.setText(commomDatas.get(position).getList_name());
        holder.msg_num.setText(commomDatas.get(position).getCount());

        holder.main_rel.setTag(position);

        holder.main_rel.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                int pos = (Integer) v.getTag();
                customDialog(commomDatas.get(pos).getList_name());
                return true;
            }
        });

        holder.main_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Global_Variable.category_name = commomDatas.get(holder.getAdapterPosition()).getCategory_name();
                Global_Variable.listname = commomDatas.get(holder.getAdapterPosition()).getList_name();
                Global_Variable.type = "list";
                Intent in = new Intent(context2, TodoListFolderActivity.class);
                context2.startActivity(in);
            }
        });
    }



    @Override
    public int getItemCount() {
        return commomDatas.size();
    }


    public void customDialog(final String listname) {

        final Dialog dd = new Dialog(context2);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.todo_sub_fragment_dialog);

        TextView list_name = (TextView) dd.findViewById(R.id.list_name);
        list_name.setText(listname);
        RelativeLayout Changelist = (RelativeLayout) dd.findViewById(R.id.rel2);
        RelativeLayout movelist = (RelativeLayout) dd.findViewById(R.id.rel3);
        RelativeLayout duplist = (RelativeLayout) dd.findViewById(R.id.rel4);
        RelativeLayout deletelist = (RelativeLayout) dd.findViewById(R.id.rel5);
        RelativeLayout sendmail = (RelativeLayout) dd.findViewById(R.id.rel6);
        RelativeLayout printlist = (RelativeLayout) dd.findViewById(R.id.rel7);
        RelativeLayout createfolder = (RelativeLayout) dd.findViewById(R.id.rel9);
        dd.show();

        Changelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listChangeDialog(listname);
                dd.cancel();
            }
        });

        deletelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listDeleteDialog(listname);
                dd.cancel();
            }
        });

        movelist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listMoveDialog(listname);
                dd.cancel();
            }
        });

        sendmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sendByMailDialog(listname);
                dd.cancel();
            }
        });


        printlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createPrintDialog(listname);
                dd.cancel();
            }
        });


        createfolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createFolderDialog();
                dd.cancel();
            }
        });
    }


    public void listChangeDialog(String listname) {

        final Dialog dd = new Dialog(context2);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.todo_sub_fragment_changelist);

        TextView list_name = (TextView) dd.findViewById(R.id.listname);
        list_name.setText(listname);
        EditText changelist = (EditText) dd.findViewById(R.id.changelist);
        RelativeLayout change = (RelativeLayout) dd.findViewById(R.id.change);
        TextView quit = (TextView) dd.findViewById(R.id.quit);
        dd.show();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dd.cancel();
            }
        });
    }


    public void listMoveDialog(String listname) {

        final Dialog dd = new Dialog(context2);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.todo_sub_fragment_changelist);

        TextView list_name = (TextView) dd.findViewById(R.id.listname);
        list_name.setText(listname);
        EditText movelist = (EditText) dd.findViewById(R.id.movelist);
        RelativeLayout move = (RelativeLayout) dd.findViewById(R.id.move);
        TextView quit = (TextView) dd.findViewById(R.id.quit);
        dd.show();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dd.cancel();
            }
        });
    }


    public void listDeleteDialog(final String listname) {

        final Dialog dd = new Dialog(context2);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.todo_sub_fragment_delete_list);

        TextView list_name = (TextView) dd.findViewById(R.id.listname);
        list_name.setText(listname);
        EditText email = (EditText) dd.findViewById(R.id.email);
        EditText password = (EditText) dd.findViewById(R.id.password);
        TextView deletemsg = (TextView) dd.findViewById(R.id.deletemsg);
        TextView delete = (TextView) dd.findViewById(R.id.delete);
        TextView quit = (TextView) dd.findViewById(R.id.quit);

        deletemsg.setText(listname + " will be removed permanently.\n You can not go back permanently.");
        dd.show();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dd.cancel();
            }
        });
    }

    public void sendByMailDialog(String listname) {

        final Dialog dd = new Dialog(context2);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.todo_sub_fragment_sendby_mail);

        TextView list_name = (TextView) dd.findViewById(R.id.listname);
        list_name.setText(listname);
        EditText emailid = (EditText) dd.findViewById(R.id.emailid);
        EditText subject = (EditText) dd.findViewById(R.id.subject);
        RelativeLayout send = (RelativeLayout) dd.findViewById(R.id.send);
        TextView quit = (TextView) dd.findViewById(R.id.quit);

        dd.show();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dd.cancel();
            }
        });
    }


    public void createPrintDialog(String listname) {

        final Dialog dd = new Dialog(context2);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.todo_sub_fragment_print_out);

        TextView list_name = (TextView) dd.findViewById(R.id.listname);
        list_name.setText(listname);
        EditText printername = (EditText) dd.findViewById(R.id.printername);
        RelativeLayout print = (RelativeLayout) dd.findViewById(R.id.print);
        TextView quit = (TextView) dd.findViewById(R.id.quit);

        dd.show();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dd.cancel();
            }
        });
    }

    public void createFolderDialog() {

        final Dialog dd = new Dialog(context2);
        dd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dd.setContentView(R.layout.todo_sub_fragment_create_folder);

        EditText create_folder = (EditText) dd.findViewById(R.id.create_folder);
        RelativeLayout save = (RelativeLayout) dd.findViewById(R.id.save);
        TextView quit = (TextView) dd.findViewById(R.id.quit);

        dd.show();

        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dd.cancel();
            }
        });
    }

}
