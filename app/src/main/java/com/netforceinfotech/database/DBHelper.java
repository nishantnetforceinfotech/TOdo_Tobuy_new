package com.netforceinfotech.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.netforceinfotech.genral.global_variable;

import java.util.ArrayList;
import java.util.List;

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

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_Category_NAME = "category_name";
    private static final String KEY_Subcategory_count = "category_count";
    private static final String KEY_List_Name = "list_name";

    public DBHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CATEGORY_TABLE = "CREATE TABLE " + TABLE_CATEGORY + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_Category_NAME + " TEXT,"
                + KEY_List_Name + " TEXT,"
                + KEY_Subcategory_count + " TEXT" + ")";
        db.execSQL(CREATE_CATEGORY_TABLE);
        
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
       // db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);

        // Create tables again
        //onCreate(db);
    }




    // All operations


   public void addCategory(String category_name ,String listname ,String category_count) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Category_NAME,category_name); // Contact Name
        values.put(KEY_List_Name,listname); // Contact Phone
        values.put(KEY_Subcategory_count,category_count);
        // Inserting Row
        db.insert(TABLE_CATEGORY, null, values);
       // db.close(); // Closing database connection
    }

    // Getting single contact
    void getCategory(String categoryname) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORY, new String[]{
                        KEY_Subcategory_count}, KEY_Category_NAME + "=?",
                new String[]{categoryname}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        String category_count=cursor.getString(1);



//        String category_count=cursor.getString(2);

//        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2));
        // return contact

    }


    void getList(String listname) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CATEGORY, new String[]{
                       KEY_Subcategory_count}, KEY_List_Name + "=?",
                new String[]{listname}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        String list_count=cursor.getString(1);
      //  String category_count=cursor.getString(2);

//        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2));
        // return contact

    }
    public ArrayList<Category_pojo> getcategory(String categoryname)
    {
        ArrayList<Category_pojo> categorydata=new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY +" Where "+KEY_Category_NAME+" =" +
                "'"+categoryname+"'";

        Log.e("Query",selectQuery);

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);







         //looping through all rows and adding to list
        if (cursor.moveToFirst()) {

            do {
                categorydata.add(new Category_pojo(cursor.getString(1),cursor.getString(3)));

                Log.e("cursor.getString(2)",cursor.getString(3));

                global_variable.check_db_foldername=false;

//                category_id.add(cursor.getString(0));
//                category_name.add(cursor.getString(1));
//                category_count.add(cursor.getString(2));
//                Contact contact = new Contact();
//                contact.setID(Integer.parseInt(cursor.getString(0)));
//                contact.setName(cursor.getString(1));
//                contact.setPhoneNumber(cursor.getString(2));
//                // Adding contact to list
//                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        else{
            Log.e("categorydata","Helll");
            global_variable.check_db_foldername=true;
        }


        return categorydata;
    }


    // Getting All Contacts
    public void getAllCategory() {
        List<String> category_id = new ArrayList<String>();
        List<String> category_name = new ArrayList<String>();
        List<String> category_count = new ArrayList<String>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                category_id.add(cursor.getString(0));
                category_name.add(cursor.getString(1));
                category_count.add(cursor.getString(2));
//                Contact contact = new Contact();
//                contact.setID(Integer.parseInt(cursor.getString(0)));
//                contact.setName(cursor.getString(1));
//                contact.setPhoneNumber(cursor.getString(2));
//                // Adding contact to list
//                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
//        return contactList;
    }

    // Updating single contact
    public void updateCategory(String category_name,String list_name,String category_count) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Category_NAME, category_name);
        values.put(KEY_List_Name, list_name);
        values.put(KEY_Subcategory_count, category_count);

        // updating row
//        return
                db.update(TABLE_CATEGORY, values, KEY_Category_NAME + " = ?",
                new String[] { category_name });
    }
    public void updateList(String id,String category_name,String list_name,String category_count) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_Category_NAME, category_name);
        values.put(KEY_List_Name, list_name);
        values.put(KEY_Subcategory_count, category_count);

        // updating row
//        return
        db.update(TABLE_CATEGORY, values, KEY_List_Name + " = ?",
                new String[] { list_name });
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
        db.delete(TABLE_CATEGORY, null ,null
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

public ArrayList<Category_pojo> getcategory_except_inbox()
{

    ArrayList<Category_pojo> categorydata=new ArrayList<>();

    String selectQuery = "SELECT  * FROM " + TABLE_CATEGORY +" Where "+KEY_ID+">1";


    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);
    while (cursor.moveToNext())
    {
        categorydata.add(new Category_pojo(cursor.getString(1),cursor.getString(3)));
        Log.e("cursordta", cursor.getString(cursor.getColumnIndex("category_name")));
    }
    return categorydata;

}

}