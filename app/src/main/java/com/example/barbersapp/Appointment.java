package com.example.barbersapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "hour")
    private int hour;
    @ColumnInfo(name = "client_first_name")
    private String clientFirstName;
    @ColumnInfo(name = "barber_first_name")
    private String barberFirstName;
    @ColumnInfo(name = "shop_name")
    private String shopName;
    @ColumnInfo(name = "status")
    private boolean status;

    public Appointment(String date, int hour, String clientFirstName, String barberFirstName, String shopName, boolean status) {
        this.date = date;
        this.hour = hour;
        this.clientFirstName = clientFirstName;
        this.barberFirstName = barberFirstName;
        this.shopName = shopName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getHour() {
        return hour;
    }

    public String getDate() {
        return date;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getBarberFirstName() {
        return barberFirstName;
    }

    public String getShopName() { return shopName; }

    public boolean getStatus() { return status; }

    public void setId(int id) {
        this.id = id;
    }
}
