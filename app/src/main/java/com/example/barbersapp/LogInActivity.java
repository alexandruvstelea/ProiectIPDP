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

        createAccountButton.setOnClickListener(createAccount -> {
            Intent intent = new Intent(LogInActivity.this, CreateAccountActivity.class);
            startActivity(intent);
        });

        logInButton.setOnClickListener(login -> {
            String userName = userNameBox.getText().toString().toUpperCase();
            String password = passWordBox.getText().toString();
            String accountType = getAccountType(userName);
            if (checkCredentials(userName, password)) {
                switch (accountType) {
                    case "CLIENT":
                        Intent intent = new Intent(LogInActivity.this, HomePageActivityClient.class);
                        intent.putExtra("username", userName);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LogInActivity.this, "LogIn successful", Toast.LENGTH_SHORT).show();
                        break;
                    case "BARBER":
                        Toast.makeText(LogInActivity.this, "BARBER GUI NOT YET IMPLEMENTED", Toast.LENGTH_SHORT).show();
                        break;
                    case "SHOP MANAGER":
                        intent = new Intent(LogInActivity.this, HomePageActivityManager.class);
                        intent.putExtra("username", userName);
                        startActivity(intent);
                        finish();
                        Toast.makeText(LogInActivity.this, "LogIn successful", Toast.LENGTH_SHORT).show();
                        break;
                }
            } else
                Toast.makeText(LogInActivity.this, "Wrong credentials!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(LogInActivity.this, "Back button disabled!", Toast.LENGTH_SHORT).show();
    }

    private String getAccountType(String userName) {
        UserDB db = UserDB.getDBInstance(this.getApplicationContext());
        return db.userDAO().getAccountType(userName);
    }

    private boolean checkCredentials(String userName, String password) {
        UserDB db = UserDB.getDBInstance(this.getApplicationContext());
        String realPassword = db.userDAO().getPassword(userName);
        if (realPassword != null)
            if (realPassword.equals(password)) return true;
        return false;
    }
}