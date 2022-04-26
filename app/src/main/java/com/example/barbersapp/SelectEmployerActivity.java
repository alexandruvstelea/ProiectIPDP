package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.List;

public class SelectEmployerActivity extends AppCompatActivity {

    private EmployersListAdapter employersListAdapter;
    private String currentUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_employer);

        //CHANGE ACTIVITY BG COLOR
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(0, 0, 0));

        Bundle extras = getIntent().getExtras();
        currentUserName = extras.getString("username");

        initRV();
        loadShopList();
    }

    private void initRV() {
        RecyclerView recyclerView = findViewById(R.id.selectEmployerRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        employersListAdapter = new EmployersListAdapter(this);
        recyclerView.setAdapter(employersListAdapter);
    }

    private void loadShopList() {
        ShopDB db = ShopDB.getDBInstance(this.getApplicationContext());
        List<Shop> shopList = db.shopDAO().getAllShops();
        employersListAdapter.setShopList(shopList);
        employersListAdapter.setCurrentUserName(currentUserName);
    }

    public void onBackPressed() {
        Intent intent = new Intent(SelectEmployerActivity.this, HomePageActivityBarber.class);
        intent.putExtra("username", currentUserName);
        startActivity(intent);
        finish();
    }
}