package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class CreateAppointmentActivity extends AppCompatActivity {

    private Spinner shopSpinner, barberSpinner, hourSpinner;
    private EditText dateSelector;
    private Button scheduleButton;
    private String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        Bundle extras = getIntent().getExtras();
        currentUserName = extras.getString("username");

        shopSpinner = findViewById(R.id.shopSpinner);
        barberSpinner = findViewById(R.id.barberSpinner);
        hourSpinner = findViewById(R.id.hourSpinner);
        dateSelector = findViewById(R.id.dateSelector);
        scheduleButton = findViewById(R.id.scheduleButton);

        ShopDB shopDB = ShopDB.getDBInstance(this.getApplicationContext());
        List<String> allShopNames = shopDB.shopDAO().getAllExistingShopNames();
        ArrayAdapter<String> shopSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, allShopNames);
        shopSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shopSpinner.setAdapter(shopSpinnerAdapter);

        UserDB userDB = UserDB.getDBInstance(this.getApplicationContext());
        List<String> allBarbersFirstNames = userDB.userDAO().getAllBarbersFirstNames();
        ArrayAdapter<String> barberSpinnerAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, allBarbersFirstNames);
        shopSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        barberSpinner.setAdapter(barberSpinnerAdapter);


        scheduleButton.setOnClickListener(schedule ->{

        });


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateAppointmentActivity.this, HomePageActivityClient.class);
        intent.putExtra("username", currentUserName);
        startActivity(intent);
        finish();
    }

}
