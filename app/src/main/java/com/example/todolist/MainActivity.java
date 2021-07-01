package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<TaskModel> taskModelArrayList;
    TaskAdapter taskAdapter;
    AddTask addTask;
    TextView addTextView;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addTaskFloatBar = findViewById(R.id.floating_BTN);
        addTaskFloatBar.setOnClickListener(this);

        addTextView = findViewById(R.id.add_text_label);
        lottieAnimationView = findViewById(R.id.animationView);

        taskModelArrayList = new ArrayList<>();
        taskAdapter = new TaskAdapter(MainActivity.this, taskModelArrayList);

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(taskAdapter);
        addTextView.setVisibility(View.GONE);
        lottieAnimationView.setVisibility(View.GONE);
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        int itemID = v.getId();

        if (itemID == R.id.floating_BTN) {
            startActivity(new Intent(this, AddTask.class));
        }
    }
}