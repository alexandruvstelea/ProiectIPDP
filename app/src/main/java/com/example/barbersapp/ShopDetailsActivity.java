package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ShopDetailsActivity extends AppCompatActivity {

    private TextView shopName;
    private String currentShopName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_details);

        //CHANGE ACTIVITY BG COLOR
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(0, 0, 0));

        Bundle extras = getIntent().getExtras();
        currentShopName = extras.getString("shopname");

        shopName = findViewById(R.id.shopNameDetails);
        shopName.setText(currentShopName);

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ShopDetailsActivity.this, HomePageActivityManager.class);
        startActivity(intent);
        finish();
    }
}