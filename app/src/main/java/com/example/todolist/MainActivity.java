 package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> first_name,last_name, phone_number;
    TaskAdapter taskAdapter;
    TextView addTextView;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addTaskFloatBar = findViewById(R.id.floating_BTN);
        addTaskFloatBar.setOnClickListener(this);

        addTextView = findViewById(R.id.add_text_label);

        databaseHelper = new DatabaseHelper(MainActivity.this);
        first_name = new ArrayList<>();
        last_name = new ArrayList<>();
        phone_number = new ArrayList<>();


        taskAdapter = new TaskAdapter(MainActivity.this, first_name, last_name, phone_number);


        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(taskAdapter);

    }

    public void displayTask(){
        Cursor cursor = databaseHelper.getAllData();
        //check if counter the database is not long
        if (cursor.getCount() == 0){
            Toast.makeText(this,"No Data.", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()){
                first_name.add(cursor.getString(0));
                last_name.add(cursor.getString(1));
                phone_number.add(cursor.getString(2));
            }
        }
    }

    @Override
    public void onClick(View v) {
        int itemID = v.getId();

        if (itemID == R.id.floating_BTN) {
            startActivity(new Intent(this, AddTask.class));
        }
    }

}