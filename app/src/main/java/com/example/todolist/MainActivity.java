package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton addTaskFloatBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTaskFloatBar = findViewById(R.id.floating_BTN);
        addTaskFloatBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int itemID = v.getId();

        if (itemID == R.id.floating_BTN) {
            startActivity(new Intent(this, AddTask.class));
        }
    }
}