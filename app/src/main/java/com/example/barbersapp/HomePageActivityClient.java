package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivityClient extends AppCompatActivity {

    private String currentUserName;
    private TextView welcomeMessage;
    private Button logOutButton, createAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_client);

        Bundle extras = getIntent().getExtras();
        currentUserName = extras.getString("username");

        welcomeMessage = findViewById(R.id.welcomeTextBarber);
        welcomeMessage.setText("Welcome " + currentUserName + " !");

        createAppointment = findViewById(R.id.createAppointmentButton);
        logOutButton = findViewById(R.id.logOutButton);

        createAppointment.setOnClickListener(createAppointment ->{
            Intent intent = new Intent(HomePageActivityClient.this, CreateAppointmentActivity.class);
            intent.putExtra("username", currentUserName);
            startActivity(intent);
            finish();
        });

        logOutButton.setOnClickListener(logOut -> {
            Intent intent = new Intent(HomePageActivityClient.this, LogInActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(HomePageActivityClient.this, "Back button disabled!", Toast.LENGTH_SHORT).show();
    }
}