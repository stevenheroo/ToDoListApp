package com.example.todolist;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddTask extends AppCompatActivity implements View.OnClickListener {

    EditText firstname;
    EditText lastname;
    EditText phone;
    ImageView backImgBTN;

    DatabaseHelper sqDatabase;

    private FloatingActionButton addTask_FloatBTN;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);


        firstname = findViewById(R.id.task_firstname_id);
        lastname = findViewById(R.id.task_lastname_id);
        phone = findViewById(R.id.task_phone_id);

        addTask_FloatBTN = findViewById(R.id.addTask_FloatBTN);
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
        sqDatabase = new DatabaseHelper(this);
        sqDatabase.addTaskSchedule(firstname.getText().toString().trim(),
                lastname.getText().toString().trim(),
                phone.getText().toString().trim());

        if ((!firstname.getText().equals("")) || (!lastname.getText().equals("")) || (!phone.getText().equals(""))) {
            firstname.setText("");
            lastname.setText("");
            phone.setText("");
        }
    }

}
