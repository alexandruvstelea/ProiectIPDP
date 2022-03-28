package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class HomePageActivityClient extends AppCompatActivity {

    private String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_client);

        Bundle extras = getIntent().getExtras();
        currentUserName = extras.getString("username");

    }



    public void onBackPressed() {
        Intent intent = new Intent(HomePageActivityClient.this, LogInActivity.class);
        startActivity(intent);
    }
}