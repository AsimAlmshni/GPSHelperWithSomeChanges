package com.najah.edu.DatABasE;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by islam on 4/22/2017.
 */

public class DB_helper extends SQLiteOpenHelper {
    public String get_email1 ;
    public String get_pass;
    public String get_fname;
    public String get_lname;
    public String get_phone;
    public String[] arr = new String[5];


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserAccount.db";

    Context context ;
    public DB_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context ;
    }




    @Override
    public void onCreate(SQLiteDatabase db) {
        //create tabels
        db.execSQL(StatmentSql.SQL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(StatmentSql.SQL_DELETE);
        onCreate(db);
    }
    //read data from table and sort array
    public ArrayList<HashMap<String, String>> fromadd() {
        try {
            ArrayList<HashMap<String, String>> arr = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> come;
            SQLiteDatabase db;
            db = this.getReadableDatabase(); // Read Data
            String strSQL = "SELECT  * FROM " + Table.ENTER.TABLE_NAME;
            Cursor cursor = db.rawQuery(strSQL, null);
            if(cursor != null)
            {
                if (cursor.moveToFirst()) {
                    do {
                        come = new HashMap<String, String>();
                        come.put("EM", cursor.getString(0));
                        come.put("PW", cursor.getString(1));
                        come.put("FN", cursor.getString(2));
                        come.put("LN", cursor.getString(3));
                        come.put("PH", cursor.getString(4));
                        arr.add(come);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            db.close();
            return arr;

        } catch (Exception e) {
            return null;
        }

    }


    // insert to database
    public boolean insert_to_table (String t1 , String t2 , String t3 , String t4 , String t5){
        ContentValues val = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        //save in database
        val.put(Table.ENTER.EMAIL, t1);
        val.put(Table.ENTER.PASSWORD, t2);
        val.put(Table.ENTER.FIRST_NAME, t3);
        val.put(Table.ENTER.LAST_NAME, t4);
        val.put(Table.ENTER.PHONE, t5);
        int DoneInsert = (int) db.insert(Table.ENTER.TABLE_NAME,null,val);
        if (DoneInsert == -1){
            return false;
        }
        else{
            val.clear();
            return true;
        }

    }

    // check account
    public boolean check_email_and_pass(String email, String password) {


        // array of columns to fetch
        String[] columns = {Table.ENTER.EMAIL};
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = Table.ENTER.EMAIL + " = ?" + " AND " + Table.ENTER.PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {email, password};
        // query user table with conditions
        Cursor cursor = db.query(Table.ENTER.TABLE_NAME,columns, selection,selectionArgs,null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {

            return true;
        }
        return false;

    }


    // update information

    public boolean update_to_table (String t1 , String t2 , String t3 , String t4 , String t5){
        ContentValues val = new ContentValues();
        SQLiteDatabase db = this.getWritableDatabase();
        //save in database
        val.put(Table.ENTER.EMAIL, t1);
        val.put(Table.ENTER.PASSWORD, t2);
        val.put(Table.ENTER.FIRST_NAME, t3);
        val.put(Table.ENTER.LAST_NAME, t4);
        val.put(Table.ENTER.PHONE, t5);
        //update data in database

        int DoneInsert = (int) db.update(Table.ENTER.TABLE_NAME, val, Table.ENTER.EMAIL + "=" + t1, null);
        if (DoneInsert == -1){
            return false;
        }
        else{
            val.clear();
            db.close();
            return true;
        }

    }

  /*
    // take data from table ()
    public  String[] get_info (String email){
        String arr [] = new String[5];
        SQLiteDatabase db;
        db = this.getReadableDatabase(); // Read Data
        String strSQL = "SELECT  * FROM " + Table.ENTER.TABLE_NAME + " WHERE " + Table.ENTER.EMAIL + " = " + email;
        Cursor cursor = db.rawQuery(strSQL, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0 ) {
            get_email1 =  cursor.getString(0);
            get_pass = cursor.getString(1);
            get_fname =  cursor.getString(2);
            get_lname =  cursor.getString(3);
            get_phone = cursor.getString(4);
            arr = new String[]{get_email1, get_pass, get_fname, get_lname, get_phone};
            
        }
        return arr;
    }
    */

}
