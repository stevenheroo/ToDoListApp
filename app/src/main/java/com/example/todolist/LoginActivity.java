package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText userEmail, userPassword;
    private TextView userRegister, forgetPassword;
    private Button loginBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        userRegister = findViewById(R.id.register_textBTN);
        userRegister.setOnClickListener(this);
        forgetPassword =findViewById(R.id.forget_passwordBTN);

        loginBTN = findViewById(R.id.loginBTN);
        loginBTN.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int itemID = v.getId();

        if (itemID == R.id.loginBTN) {
            startActivity(new Intent(this, MainActivity.class));
            Toast.makeText(LoginActivity.this, "Welcome to homepage", Toast.LENGTH_LONG).show();
        }
        else if (itemID == R.id.register_textBTN) {
            startActivity(new Intent(this, RegisterActivity.class));
            Toast.makeText(LoginActivity.this, "Welcome to homepage", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                .setTitle("Quit Application")
                .setCancelable(false)
                .setMessage("Are your sure to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.finishAffinity(LoginActivity.this);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                });
        builder.show();
    }
}