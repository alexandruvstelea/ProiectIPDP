package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

public class HomePageActivityManager extends AppCompatActivity {

    private ShopListAdapter shopListAdapter;
    private String currentUserName;
    private Button addShop, logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_manager);

        //CHANGE ACTIVITY BG COLOR
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(0, 0, 0));

        addShop = findViewById(R.id.addNewShopButton);

        Bundle extras = getIntent().getExtras();
        currentUserName = extras.getString("username");

        addShop.setOnClickListener(addShop -> {
            Intent intent = new Intent(HomePageActivityManager.this, CreateShopActivity.class);
            intent.putExtra("username", currentUserName);
            startActivity(intent);
            finish();
        });

        logOut = findViewById(R.id.exitButton2);
        logOut.setOnClickListener(logO -> {
            Intent intent = new Intent(HomePageActivityManager.this, LogInActivity.class);
            startActivity(intent);
            finish();
        });

        initRV();
        loadShopList();
    }

    private void initRV() {
        RecyclerView recyclerView = findViewById(R.id.shopsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        shopListAdapter = new ShopListAdapter(this);
        recyclerView.setAdapter(shopListAdapter);
    }

    private void loadShopList() {
        ShopDB db = ShopDB.getDBInstance(this.getApplicationContext());
        List<Shop> shopList = db.shopDAO().getOwnerShops(currentUserName);
        shopListAdapter.setShopList(shopList);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(HomePageActivityManager.this, "Back button disabled!", Toast.LENGTH_SHORT).show();
    }
}