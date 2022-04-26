package com.example.barbersapp;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppointmentsDAO {

    @Query("SELECT * from appointment WHERE client_first_name=:clientFirstName")
    List<Appointment> getClientAppointments(String clientFirstName);

    @Query("SELECT * from appointment WHERE barber_first_name=:barberFirstName")
    List<Appointment> getBarberAppointments(String barberFirstName);
}
