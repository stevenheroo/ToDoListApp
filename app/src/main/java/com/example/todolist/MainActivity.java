package com.example.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "mainPage";
    ArrayList<TaskModel> taskModelArrayList;
    TaskAdapter taskAdapter;
    TextView addTextLabel;
    DatabaseHelper databaseHelper;
    RecyclerView recyclerView;
    ImageView drop_downBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addTaskFloatBar = findViewById(R.id.floating_BTN);
        addTaskFloatBar.setOnClickListener(this);

        addTextLabel= findViewById(R.id.add_text_label);
        drop_downBTN = findViewById(R.id.dropdown_BTN);
        drop_downBTN.setOnClickListener(this);

        databaseHelper = new DatabaseHelper(MainActivity.this);
        taskModelArrayList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();

        //display the list of tasks in database
        displayTask();
    }

    public void displayTask() {
        taskModelArrayList = databaseHelper.getAllData();

        taskAdapter = new TaskAdapter(MainActivity.this, taskModelArrayList);
        recyclerView = findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(taskAdapter);
        addTextLabel.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        int itemID = v.getId();

        if (itemID == R.id.floating_BTN) {
            startActivity(new Intent(this, AddTask.class));
        }
        if (itemID == R.id.dropdown_BTN) {
//            perform this function
            optionsBar();
        }
    }

    private void optionsBar() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, drop_downBTN);
        Log.i(TAG, "onMenuItemClick: Populated by sort menu items");
        popupMenu.inflate(R.menu.menu);
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.logOut)
                    logoutUser();
                return false;
            }
        });
    }

    /**
     * Logging out User.
     */
    public void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        Toast.makeText(MainActivity.this, "Logged out", Toast.LENGTH_SHORT).show();
        Log.i("Logging User Out", "Successfully Signed out");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }
}