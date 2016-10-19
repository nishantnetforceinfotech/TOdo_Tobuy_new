package com.netforceinfotech.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.netforceinfotech.genral.Global_Variable;

import java.util.ArrayList;

/**
 * Created by abcd on 10/18/2016.
 */
public class DBHelper extends SQLiteOpenHelper {


    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "todo.db";
    // Contacts table name
    private static final String TABLE_CATEGORY = "maincategory";
    private static final String TABLE_TASK_DEATILS = "taskdetails";


    // Contacts maincategory Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_Category_NAME = "category_name";
    private static final String KEY_Subcategory_count = "category_count";
    private static final String KEY_List_Name = "list_name";
    private static final String KEY_TYPE = "type_name";

    // Contacts taskdetails Table Columns names
    private static final String KEY_TASK_ID = "task_id";
    private static final String KEY_Category_TASK_NAME = "category_task_name";
    private static final String KEY_LIST_TASK_NAME = "list_task_name";
    private static final String KEY_TASK_NAME = "task_name";
    private static final String KEY_TASK_SELECTED = "task_selected";
    private static final String KEY_TASK_DATE = "task_date";


    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_Category_NAME + " TEXT,"
                + KEY_List_Name + " TEXT,"
                + KEY_Subcategory_count + " TEXT,"
                + KEY_TYPE + " TEXT"+ ")";
        db.execSQL(CREATE_CATEGORY_TABLE);


        String CREATE_TASK_TABLE = "CREATE TABLE " + TABLE_TASK_DEATILS + "("
                + KEY_TASK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_Category_TASK_NAME + " TEXT,"
                + KEY_LIST_TASK_NAME + " TEXT,"
                + KEY_TASK_NAME + " TEXT,"
                + KEY_TASK_SELECTED + " TEXT,"
                + KEY_TASK_DATE + " TEXT" + ")";
        db.execSQL(CREATE_TASK_TABLE);


    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        // Create tables again
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASK_DEATILS);
        // Create tables again
        onCreate(db);
    }

    /**
     * All Operations for TaskDetails Table
     *
     * @param category_name
     * @param taskname
     * @param taskselected
     * @param taskdate
     */

    public void addTask(String category_name, String list_name, String taskname, String taskselected, String taskdate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Category_TASK_NAME, category_name);
        values.put(KEY_LIST_TASK_NAME, list_name);
        values.put(KEY_TASK_NAME, taskname);
        values.put(KEY_TASK_SELECTED, taskselected);
        values.put(KEY_TASK_DATE, taskdate);
        // Inserting Row
        db.insert(TABLE_TASK_DEATILS, null, values);

    }


    public ArrayList<Task_Pojo> getCategoryTask(String categoryname) {

        ArrayList<Task_Pojo> categorydata = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASK_DEATILS + " Where " + KEY_Category_TASK_NAME + " =" +
                "'" + categoryname + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                categorydata.add(new Task_Pojo(cursor.getString(1), cursor.getString(2)
                        , cursor.getString(3), cursor.getString(4), cursor.getString(5),false,false));

            } while (cursor.moveToNext());
        }


        return categorydata;
    }

    public ArrayList<Task_Pojo> getListTask(String listname) {

        ArrayList<Task_Pojo> listdata = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_TASK_DEATILS + " Where " + KEY_LIST_TASK_NAME + " =" +
                "'" + listname + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                listdata.add(new Task_Pojo(cursor.getString(1), cursor.getString(2)
                        , cursor.getString(3), cursor.getString(4), cursor.getString(5),false,false));

            } while (cursor.moveToNext());
        }


        return listdata;
    }


    public void updateCategoryTask(String category_name, String list_name, String taskname, String taskselected, String taskdate) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Category_TASK_NAME, category_name);
        values.put(KEY_LIST_TASK_NAME, list_name);
        values.put(KEY_TASK_NAME, taskname);
        values.put(KEY_TASK_SELECTED, taskselected);
        values.put(KEY_TASK_DATE, taskdate);

        db.update(TABLE_TASK_DEATILS, values, KEY_TASK_NAME + " = ?",
                new String[]{taskname});
    }


    // All for maincategory operations

    public void addCategory(String category_name, String listname, String category_count,String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Category_NAME, category_name); // Contact Name
        values.put(KEY_List_Name, listname); // Contact Phone
        values.put(KEY_Subcategory_count, category_count);
        values.put(KEY_TYPE, type);
        // Inserting Row
        db.insert(TABLE_CATEGORY, null, values);

    }

    // Getting single contact
    void getCategory(String categoryname) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORY, new String[]{
                        KEY_Subcategory_count}, KEY_Category_NAME + "=?",
                new String[]{categoryname}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();


    }


    void getList(String listname) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORY, new String[]{
                        KEY_Subcategory_count}, KEY_List_Name + "=?",
                new String[]{listname}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();


    }

    public ArrayList<Category_pojo> getcategory(String categoryname) {
        ArrayList<Category_pojo> categorydata = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY + " Where " + KEY_Category_NAME + " =" +
                "'" + categoryname + "'";

        Log.e("Query", selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        //looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                categorydata.add(new Category_pojo(cursor.getString(1), cursor.getString(3),cursor.getString(4)));

                Log.e("cursor.getString(2)", cursor.getString(3));

                Global_Variable.check_db_foldername = false;

            } while (cursor.moveToNext());
        } else {
            Log.e("categorydata", "Helll");
            Global_Variable.check_db_foldername = true;
        }


        return categorydata;
    }


    // Getting All Contacts
    public void getAllCategory() {

        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {

            } while (cursor.moveToNext());
        }


    }

    // Updating single contact
    public void updateCategory(String category_name, String list_name, String category_count,String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Category_NAME, category_name);
        values.put(KEY_List_Name, list_name);
        values.put(KEY_Subcategory_count, category_count);
        values.put(KEY_TYPE, type);

        // updating row
//        return
        db.update(TABLE_CATEGORY, values, KEY_Category_NAME + " = ?",
                new String[]{category_name});
    }

    public void updateList(String id, String category_name, String list_name, String category_count,String type) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Category_NAME, category_name);
        values.put(KEY_List_Name, list_name);
        values.put(KEY_Subcategory_count, category_count);
        values.put(KEY_TYPE, type);

        // updating row
//        return
        db.update(TABLE_CATEGORY, values, KEY_List_Name + " = ?",
                new String[]{list_name});
    }

    // Deleting single contact
    public void deleteCategory(String category_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORY, KEY_Category_NAME + " = ?",
                new String[]{category_name});
        //db.close();
    }

    public void deletelist(String list_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORY, KEY_List_Name + " = ?",
                new String[]{list_name});
        // db.close();
    }

    public void delete_all_Category() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CATEGORY, null, null
        );
        //db.ope
    }

    // Getting contacts Count
    public int getCategoryCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CATEGORY;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        // cursor.close();

        // return count
        return cursor.getCount();
    }

    public ArrayList<Category_pojo> getcategory_except_inbox() {

        ArrayList<Category_pojo> categorydata = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY + " Where " + KEY_ID + ">1";


        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        while (cursor.moveToNext()) {
            categorydata.add(new Category_pojo(cursor.getString(1), cursor.getString(3),cursor.getString(4)));
            Log.e("cursordta", cursor.getString(cursor.getColumnIndex("category_name")));
        }
        return categorydata;

    }

}