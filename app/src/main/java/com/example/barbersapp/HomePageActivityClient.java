package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivityClient extends AppCompatActivity {

    private String currentUserName;
    private TextView welcomeMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_client);

        Bundle extras = getIntent().getExtras();
        currentUserName = extras.getString("username");

        welcomeMessage = findViewById(R.id.welcomeTextBarber);
        welcomeMessage.setText("Welcome "+ currentUserName + " !");

    }



    @Override
    public void onBackPressed() {
        Toast.makeText(HomePageActivityClient.this, "Back button disabled!", Toast.LENGTH_SHORT).show();
    }
}