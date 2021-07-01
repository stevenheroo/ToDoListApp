package com.example.todolist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONObject;

import java.util.ArrayList;

public class AddTask extends AppCompatActivity implements View.OnClickListener {

    EditText firstname;
    EditText lastname;
    EditText phone;
    MainActivity mainActivity;
    JSONObject jsonObject;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private FloatingActionButton addFloatBTN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        ImageView backBTN = findViewById(R.id.back_img_BTN);
        backBTN.setOnClickListener(this);

        firstname = findViewById(R.id.task_firstname_id);
        lastname = findViewById(R.id.task_lastname_id);
        phone = findViewById(R.id.task_phone_id);

        addFloatBTN = findViewById(R.id.add_floatBTN);
        addFloatBTN.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int itemID = v.getId();

        if (itemID == R.id.add_floatBTN) {
            addTask();
        }
        else if (itemID == R.id.back_img_BTN){
            onBackPressed();
        }
    }

    public void addTask() {
        final String firstName = firstname.getText().toString().trim();
        final String lastName = lastname.getText().toString().trim();
        final String phoneNumber = phone.getText().toString().trim();

        TaskModel taskModel = new TaskModel(
                firstName, lastName, phoneNumber
        );

        if ((!firstname.getText().equals("")) || (!lastname.getText().equals("")) || (!phone.getText().equals("")) ){
            firstname.setText("");
            lastname.setText("");
            phone.setText("");
        }
    }

}
