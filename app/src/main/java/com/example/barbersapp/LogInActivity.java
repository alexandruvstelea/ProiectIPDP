package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {

    private EditText userNameBox, passWordBox;
    private Button logInButton, createAccountButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_activity);

        //CHANGE ACTIVITY BG COLOR
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(0, 0, 0));

        userNameBox = findViewById(R.id.userNameBox);
        passWordBox = findViewById(R.id.passwordBox);
        logInButton = findViewById(R.id.loginButton);
        createAccountButton = findViewById(R.id.createAccountButton);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = userNameBox.getText().toString();
                String password = passWordBox.getText().toString();
                if (checkCredentials(userName, password)) {
                    Intent intent = new Intent(LogInActivity.this, HomePageActivity.class);
                    startActivity(intent);
                    Toast.makeText(LogInActivity.this, "LogIn succesfull", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(LogInActivity.this, "Wrong credentials!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(LogInActivity.this, "Back button disabled!", Toast.LENGTH_SHORT).show();
    }

    private boolean checkCredentials(String userName, String password) {
        UserDB db = UserDB.getDBInstance(this.getApplicationContext());
        String realPassword = db.userDAO().getPassword(userName.toUpperCase());
        if (realPassword.equals(password)) {
            finish();
            return true;
        }
        return false;
    }
}