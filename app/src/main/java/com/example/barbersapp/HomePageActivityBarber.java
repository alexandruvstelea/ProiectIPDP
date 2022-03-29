package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomePageActivityBarber extends AppCompatActivity {

    private TextView welcomeText, currentEmployer;
    private Button changeEmployer, logOut;
    private String currentUserName;
    private int employerID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_barber);

        //CHANGE ACTIVITY BG COLOR
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(0, 0, 0));

        Bundle extras = getIntent().getExtras();
        currentUserName = extras.getString("username");

        welcomeText = findViewById(R.id.welcomeTextBarber);
        welcomeText.setText("Welcome " + currentUserName + " !");

        currentEmployer = findViewById(R.id.currentEmployerText);
        UserDB db = UserDB.getDBInstance(this.getApplicationContext());
        employerID = db.userDAO().getEmployerID(currentUserName);

        if (employerID == -1)
            currentEmployer.setText("Not currently employed");
        else {
            ShopDB dB = ShopDB.getDBInstance(this.getApplicationContext());
            currentEmployer.setText(dB.shopDAO().getEmployerName(employerID));
        }

        changeEmployer = findViewById(R.id.changeEmplyerButton);

        changeEmployer.setOnClickListener(changeEmp ->{
            Intent intent = new Intent(HomePageActivityBarber.this, SelectEmployerActivity.class);
            intent.putExtra("username", currentUserName);
            startActivity(intent);
            //finish();
        });

        logOut = findViewById(R.id.logOutButton);
        logOut.setOnClickListener(logO ->{
            Intent intent = new Intent(HomePageActivityBarber.this, LogInActivity.class);
            startActivity(intent);
            finish();
        });

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(HomePageActivityBarber.this, "Back button disabled!", Toast.LENGTH_SHORT).show();
    }
}