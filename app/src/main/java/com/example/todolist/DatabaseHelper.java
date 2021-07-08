package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "sqliteDataBase";

    // I used sqlite for storing data

    private static final String DATABASE_NAME = "ToDOList.db";
    private static final int DATABASE_VERSION = 1;
    //table name
    private static final String TABLE_NAME = "task_list";
    //columns for database
    private static final String ID = "id";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String PHONE = "phone";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
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

    public void addTaskSchedule(TaskModel taskModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        //we store all data into this content value
        ContentValues cv = new ContentValues();

        cv.put(FIRSTNAME, taskModel.getFirstname());
        cv.put(LASTNAME, taskModel.getLastname());
        cv.put(PHONE, taskModel.getPhone());

        //this will be used in addTask function
        //we store data in results.
        long result = db.insert(TABLE_NAME, null, cv);

        // validation checker
        if (result == -1) {
            Log.i(TAG, "addTaskSchedule: Failed");
        } else {
            Log.i(TAG, "addTaskSchedule: Success");
        }
        // Closing database after using
        db.close();
    }


    /**
     * @return arrayList containing saved data
     */
    public ArrayList<TaskModel> getAllData() {

        // Data model list in which we have to return the data
        ArrayList<TaskModel> taskModelArrayList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME;
        // Accessing database for reading data
        SQLiteDatabase db = this.getReadableDatabase();

        // Cursor for traversing whole data into database
        try (Cursor cursor = db.rawQuery(query, null)) {
            // check if cursor move to first
            if (cursor.moveToFirst()) {

                // looping through all data and adding to arraylist
                do {
                    TaskModel taskModel = new TaskModel(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3));
                    taskModelArrayList.add(taskModel);

                } while (cursor.moveToNext());

            }
        }

        // After using cursor we have to close it

        // Closing database
        db.close();

        // returning list
        return taskModelArrayList;
    }

    // Deleting table from database
    public void deleteTable(String ID) {

        SQLiteDatabase db = this.getWritableDatabase();

        // Deleting table
        db.delete(TABLE_NAME, "ID=" + ID, null);

        // Closing database
        db.close();

    }


}
