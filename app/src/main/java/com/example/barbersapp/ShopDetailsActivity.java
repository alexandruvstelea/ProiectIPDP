package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class ShopDetailsActivity extends AppCompatActivity {

    private EmployedBarbersAdapter employedBarbersAdapter;
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

        initRV();
        loadEmployeesList();

    }

    private void initRV() {
        RecyclerView recyclerView = findViewById(R.id.employeesRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        employedBarbersAdapter = new EmployedBarbersAdapter(this);
        recyclerView.setAdapter(employedBarbersAdapter);
    }

    public void loadEmployeesList() {
        UserDB userDB = UserDB.getDBInstance(this.getApplicationContext());
        ShopDB shopDB = ShopDB.getDBInstance(this.getApplicationContext());
        int shopID = shopDB.shopDAO().getShopID(shopName.getText().toString());
        List<User> employeesList = userDB.userDAO().getEmployeesByID(shopID);
        employedBarbersAdapter.setEmployeesList(employeesList);
        userDB.close();
        shopDB.close();
    }

    @Override
    public void onBackPressed() {
        ShopDB shopDB = ShopDB.getDBInstance(this.getApplicationContext());
        String ownerUserName = shopDB.shopDAO().getOwnerUserName(currentShopName);
        Intent intent = new Intent(ShopDetailsActivity.this, HomePageActivityManager.class);
        intent.putExtra("username", ownerUserName);
        startActivity(intent);
        finish();
    }
}