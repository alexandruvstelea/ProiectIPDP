package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class CreateShopActivity extends AppCompatActivity {

    private EditText shopNameInput, shopStreetInput, shopNumberInput, shopCityInput;
    private Button addShopButton;
    private String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shop);

        //CHANGE ACTIVITY BG COLOR
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(0, 0, 0));

        shopNameInput = findViewById(R.id.shopNameInput);
        shopStreetInput = findViewById(R.id.shopStreetInput);
        shopNumberInput = findViewById(R.id.shopNumberInput);
        shopCityInput = findViewById(R.id.shopCityInput);
        addShopButton = findViewById(R.id.addShopButton);

        Bundle extras = getIntent().getExtras();
        currentUserName = extras.getString("username");

        addShopButton.setOnClickListener(addShop -> {
            if (!(shopNameInput.getText().toString().isEmpty() || shopStreetInput.getText().toString().isEmpty() || shopNumberInput.getText().toString().isEmpty() || shopCityInput.getText().toString().isEmpty())) {
                String shopName = shopNameInput.getText().toString();
                String shopAddress = "Str. " + shopStreetInput.getText().toString().toUpperCase() + ", Nr. " + shopNumberInput.getText().toString().toUpperCase() + ", " + shopCityInput.getText().toString().toUpperCase();
                String ownerUserName = currentUserName;
                createShop(shopName, shopAddress, ownerUserName);
            }
        });

    }

    public void onBackPressed() {
        Intent intent = new Intent(CreateShopActivity.this, HomePageActivityManager.class);
        startActivity(intent);
        finish();
    }

    private void createShop(String shopName, String shopAddress, String ownerUserName) {
        ShopDB db = ShopDB.getDBInstance(this.getApplicationContext());
        List<String> currentShopNames = db.shopDAO().getAllExistingShopNames();
        if (checkUniqueShopName(currentShopNames, shopName.toUpperCase())) {
            Shop shop = new Shop(shopName, shopAddress, ownerUserName);
            db.shopDAO().insertShop(shop);
            Toast.makeText(CreateShopActivity.this, "Shop created", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(CreateShopActivity.this, HomePageActivityManager.class);
            intent.putExtra("username", currentUserName);
            startActivity(intent);
            finish();
        } else
            Toast.makeText(CreateShopActivity.this, "Please enter all the data!", Toast.LENGTH_SHORT).show();
    }

    private boolean checkUniqueShopName(List<String> shopNamesList, String newName) {
        for (String name : shopNamesList)
            if (name.equals(newName))
                return false;
        return true;
    }
}