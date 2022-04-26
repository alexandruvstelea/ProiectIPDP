package com.example.barbersapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Appointment {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="date")
    private Date date;
    @ColumnInfo(name="hour")
    private int hour;
    @ColumnInfo(name="client_first_name")
    private String clientFirstName;
    @ColumnInfo(name="barber_first_name")
    private String barberFirstName;

    public Appointment(Date date, int hour, String clientFirstName, String barberFirstName) {
        this.date = date;
        this.hour = hour;
        this.clientFirstName = clientFirstName;
        this.barberFirstName = barberFirstName;
    }

    public int getId() {
        return id;
    }

    public int getHour(){
        return hour;
    }

    public Date getDate() {
        return date;
    }

    public String getClientFirstName() {
        return clientFirstName;
    }

    public String getBarberFirstName() {
        return barberFirstName;
    }

    public void setId(int id) {
        this.id = id;
    }
}
