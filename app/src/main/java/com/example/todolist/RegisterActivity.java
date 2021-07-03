package com.example.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.todolist.Utilities.PasswordUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name, email, password, passwordConfirm;
    Button signUpBTN;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();

        name = findViewById(R.id.userFullName);
        email = findViewById(R.id.userEmail);
        password = findViewById(R.id.password_1);
        passwordConfirm = findViewById(R.id.password_2);

        signUpBTN = findViewById(R.id.registerBTN);
        signUpBTN.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int itemID = v.getId();

        if (itemID == R.id.registerBTN) {
            //Register user info to firebase database
            registerUser();
        }
    }

    /**
     * Register user function
     */
    private void registerUser() {
        final String userName = name.getText().toString().trim();
        final String emailAddress = email.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        String password2 = passwordConfirm.getText().toString().trim();

        //Handle exceptions for user inputs
        //Now we Need If Statements to validate these Inputs..Check validation
        if (userName.isEmpty()) {
            name.setError("Your name is required");
            name.requestFocus();
            return;
        }
        if (emailAddress.isEmpty()) {
            email.setError("Your Email is required");
            email.requestFocus();
            return;
        }
        //Password validation
        if (password1.isEmpty()) {
            password.setError("Provide password");
            password.requestFocus();
            return;
        }
        if (password2.isEmpty()) {
            passwordConfirm.setError("Provide password confirmation");
            passwordConfirm.requestFocus();
            return;
        }
        if (!PasswordUtils.PASSWORD_PATTERN.matcher(password1).matches()) {
            password.setError("Password must contain at least 8 char(uppercase & lowercase, special Char, number)..eg.'@Example1'");
            password.requestFocus();
            return;
        }
        if (!password2.equals(password1)) {
            passwordConfirm.setError("Password did not match! Re-enter");
            passwordConfirm.requestFocus();
            return;
        }

        ProgressBar progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(emailAddress, password1).addOnCompleteListener(task -> {

            if (task.isSuccessful()) {

                //Create Users info with these below
                final Users users = new Users(userName, emailAddress);

                //then get the registered user ID using the below, make id correspond to user when registered
                FirebaseDatabase.getInstance().getReference("Users")
                        .child(auth.getCurrentUser().getUid())
                        //set name,email&password to the current user
                        .setValue(users)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                    Toast.makeText(RegisterActivity.this, "Account registered...VERIFY email Now", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Registration Failed. TRY AGAIN", Toast.LENGTH_LONG).show();
                                }
                                progressBar.setVisibility(View.GONE);
                            }
                        });
            } else {
                Toast.makeText(RegisterActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}