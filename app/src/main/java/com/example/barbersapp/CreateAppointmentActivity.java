package com.example.barbersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class CreateAppointmentActivity extends AppCompatActivity {

    private Spinner shopSpinner, barberSpinner, hourSpinner;
    private Button dateButton;
    private Button scheduleButton;
    private String currentUserName;
    private DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_appointment);

        //CHANGE ACTIVITY BG COLOR
        View view = this.getWindow().getDecorView();
        view.setBackgroundColor(Color.rgb(0, 0, 0));

        Bundle extras = getIntent().getExtras();
        currentUserName = extras.getString("username");

        shopSpinner = findViewById(R.id.shopSpinner);
        barberSpinner = findViewById(R.id.barberSpinner);
        hourSpinner = findViewById(R.id.hourSpinner);
        dateButton = findViewById(R.id.datePickButton);
        scheduleButton = findViewById(R.id.scheduleButton);
        dateButton.setText(getTodaysDate());

        ShopDB shopDB = ShopDB.getDBInstance(this.getApplicationContext());
        List<String> allShopNames = shopDB.shopDAO().getAllExistingShopNames();
        ArrayAdapter<String> shopSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allShopNames);
        shopSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shopSpinner.setAdapter(shopSpinnerAdapter);

        int selectedShopID = shopDB.shopDAO().getShopID(String.valueOf(shopSpinner.getSelectedItem()));

        changeBarbers(selectedShopID);
        shopSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                int selectedShopID = shopDB.shopDAO().getShopID(String.valueOf(shopSpinner.getSelectedItem()));
                changeBarbers(selectedShopID);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                int selectedShopID = shopDB.shopDAO().getShopID(String.valueOf(shopSpinner.getSelectedItem()));
                changeBarbers(selectedShopID);
            }
        });

        initDatePicker();

        scheduleButton.setOnClickListener(schedule -> {
            UserDB userDB = UserDB.getDBInstance(this.getApplicationContext());
            String shopName = String.valueOf(shopSpinner.getSelectedItem());
            String barberFirstName = String.valueOf(barberSpinner.getSelectedItem());
            String date = String.valueOf(dateButton.getText());
            String clientFirstName = userDB.userDAO().getFirstNameByUserName(currentUserName);
            int hour = Integer.parseInt(hourSpinner.getSelectedItem().toString());
            if (createAppointment(date, hour, clientFirstName, barberFirstName, shopName)) {
                Intent intent = new Intent(CreateAppointmentActivity.this, HomePageActivityClient.class);
                intent.putExtra("username", currentUserName);
                startActivity(intent);
                finish();
                Toast.makeText(CreateAppointmentActivity.this, "Appointment created!", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(CreateAppointmentActivity.this, "The barber is not available!", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean createAppointment(String date, int hour, String clientFirstName, String barberFirstName, String shopName) {
        AppointmentsDB appointmentsDB = AppointmentsDB.getDBInstance(this.getApplicationContext());
        int check = appointmentsDB.appointmentsDAO().checkIfAppointment(barberFirstName, date, hour);
        if (check == 0) {
            Appointment appointment = new Appointment(date, hour, clientFirstName, barberFirstName, shopName, true);
            appointmentsDB.appointmentsDAO().insertAppointment(appointment);
            return true;
        } else return false;
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month += 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void changeBarbers(int shopID) {
        UserDB userDB = UserDB.getDBInstance(this.getApplicationContext());
        List<String> allBarbersFirstNames = userDB.userDAO().getAllBarbersFirstNamesAtSelectedShop(shopID);
        ArrayAdapter<String> barberSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, allBarbersFirstNames);
        barberSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        barberSpinner.setAdapter(barberSpinnerAdapter);
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month += 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(this, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    private String makeDateString(int day, int month, int year) {
        return day + "/" + month + "/" + year;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CreateAppointmentActivity.this, HomePageActivityClient.class);
        intent.putExtra("username", currentUserName);
        startActivity(intent);
        finish();
    }

}
