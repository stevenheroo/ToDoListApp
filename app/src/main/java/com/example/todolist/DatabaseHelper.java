package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "ToDOList.db";
    private static final int DATABASE_VERSION = 1 ;

    //table name
    private static final String TABLE_NAME = "task_list";

    //columns for database
    private static final String ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String PHONE = "phone";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //SQL schema created here
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                FIRSTNAME + " TEXT, " +
                LASTNAME + " TEXT, " +
                PHONE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        //when table updated, we call on create method
        onCreate(db);
    }

    public void addTaskSchedule(String firstname, String lastname, String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        //we store all data into this content value
        ContentValues cv = new ContentValues();

        cv.put(FIRSTNAME, firstname);
        cv.put(FIRSTNAME, lastname);
        cv.put(FIRSTNAME, phone);

        //this will be used in addTask function
        //we store data in results.
        long result = db.insert(TABLE_NAME, null, cv);

        // validation checker
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

//        check if database is not null
        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        //cursor will contain all data from table
        return  cursor;
    }


}
