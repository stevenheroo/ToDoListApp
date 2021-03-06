package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddTask extends AppCompatActivity implements View.OnClickListener {

    EditText firstname;
    EditText lastname;
    EditText phone;
    ImageView backImgBTN;

    DatabaseHelper sqDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        sqDatabase = new DatabaseHelper(getApplicationContext());

        firstname = findViewById(R.id.task_firstname_id);
        lastname = findViewById(R.id.task_lastname_id);
        phone = findViewById(R.id.task_phone_id);

        FloatingActionButton addTask_FloatBTN = findViewById(R.id.addTask_FloatBTN);
        addTask_FloatBTN.setOnClickListener(this);

        backImgBTN = findViewById(R.id.closeBTN);
        backImgBTN.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int itemID = v.getId();

        if (itemID == R.id.addTask_FloatBTN) {
            addTask();
        }
        if (itemID == R.id.closeBTN) {
            onBackPressed();
        }
    }

    public void addTask() {

        String firstName = firstname.getText().toString().trim();
        String lastName = lastname.getText().toString().trim();
        String phoneNumber = phone.getText().toString().trim();
        String id = "";

        if ((!firstName.isEmpty()) || (!lastName.isEmpty()) || (!phoneNumber.isEmpty())) {
            firstname.setText("");
            lastname.setText("");
            phone.setText("");
        }
        if ((firstName.isEmpty()) || (lastName.isEmpty()) || (phoneNumber.isEmpty())) {
            firstname.setError("All fields are required");
            firstname.requestFocus();
        } else {

            // Insert data into database
            sqDatabase.addTaskSchedule(new TaskModel(id, firstName, lastName,
                    phoneNumber));

            // Toast for successfully saved data
            Toast.makeText(AddTask.this, "Data saved successfully.",
                    Toast.LENGTH_SHORT).show();
        }


    }

}
