package com.example.todolist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.todolist.Utilities.PasswordUtils;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //please my ubuntu pc is having audio issues, i hope you understand, thank you......
    ProgressBar progressBar;
    private EditText userEmail, userPassword;
    private TextView userRegister, forgetPassword;
    private Button loginBTN;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        userRegister = findViewById(R.id.register_textBTN);
        userRegister.setOnClickListener(this);
        forgetPassword = findViewById(R.id.forget_passwordBTN);

        progressBar = findViewById(R.id.progressBar);
        loginBTN = findViewById(R.id.loginBTN);
        loginBTN.setOnClickListener(this);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        int itemID = v.getId();

        if (itemID == R.id.loginBTN) {
            //when item id equals to login button
            //render this function...
            loginUser();
        } else if (itemID == R.id.register_textBTN) {
            startActivity(new Intent(this, RegisterActivity.class));
            Toast.makeText(LoginActivity.this, "Welcome to homepage", Toast.LENGTH_LONG).show();
        }
    }

    //Function To Authenticate user
    private void loginUser() {
        //Get User Email and Password before logging in
        String emailAddress = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();

        //We Check for Validation and errors ....both email and password
        if (emailAddress.isEmpty()) {
            userEmail.setError("Email is Required");
            userEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            userEmail.setError("Enter a Valid Email");
            userEmail.requestFocus();
            return;
        }
        //For password
        if (password.isEmpty()) {
            userPassword.setError("Please Enter Password");
            userPassword.requestFocus();
            return;
        }
        if (!PasswordUtils.PASSWORD_PATTERN.matcher(password).matches()) {
            userPassword.setError("Wrong Password'");
            userPassword.requestFocus();
            return;
        }

//        Turn progress bar to visible when onclick Login btn..Progress to true when Login button is clicked
        progressBar.setVisibility(View.VISIBLE);
        //Here user will in with his credentials, hat is email and password stored in firebase
        auth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                //Redirect user to Home activity if Login success
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                //Show success message
                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
            }
            progressBar.setVisibility(View.GONE);
        });
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