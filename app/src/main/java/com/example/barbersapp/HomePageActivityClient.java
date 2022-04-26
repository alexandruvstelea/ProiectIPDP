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
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HomePageActivityClient extends AppCompatActivity {

    private String currentUserName;
    private TextView welcomeMessage;
    private Button logOutButton, createAppointment;
    private AppointmentsListAdapter appointmentsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_client);

        //CHANGE ACTIVITY BG COLOR
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(0, 0, 0));

        Bundle extras = getIntent().getExtras();
        currentUserName = extras.getString("username");

        welcomeMessage = findViewById(R.id.welcomeTextBarber);
        welcomeMessage.setText("Welcome " + currentUserName + " !");

        createAppointment = findViewById(R.id.createAppointmentButton);
        logOutButton = findViewById(R.id.exitButton);

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

        initRV();
        loadAppointmentsList();
    }

    private void initRV() {
        RecyclerView recyclerView = findViewById(R.id.appointementsRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        appointmentsListAdapter = new AppointmentsListAdapter(this);
        recyclerView.setAdapter(appointmentsListAdapter);
    }

    public void loadAppointmentsList() {
        AppointmentsDB appointmentsDB = AppointmentsDB.getDBInstance(this.getApplicationContext());
        UserDB userDB = UserDB.getDBInstance(this.getApplicationContext());
        String clientFirstName = userDB.userDAO().getFirstNameByUserName(currentUserName);
        List<Appointment> appointmentList = appointmentsDB.appointmentsDAO().getClientAppointments(clientFirstName);
        appointmentsListAdapter.setAppointmentsList(appointmentList);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(HomePageActivityClient.this, "Back button disabled!", Toast.LENGTH_SHORT).show();
    }
}