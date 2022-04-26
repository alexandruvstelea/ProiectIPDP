package com.example.barbersapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppointmentsDAO {

    @Query("SELECT * FROM appointment WHERE client_first_name=:clientFirstName AND status=1")
    List<Appointment> getClientAppointments(String clientFirstName);

    @Query("SELECT * FROM appointment WHERE barber_first_name=:barberFirstName AND status=1")
    List<Appointment> getBarberAppointments(String barberFirstName);

    @Query("SELECT id FROM appointment WHERE barber_first_name=:barberFirstName AND date=:date AND hour=:hour  AND status=1")
    int checkIfAppointment(String barberFirstName, String date, int hour);

    @Query("UPDATE appointment SET status=:newStatus WHERE barber_first_name=:barberFirstName AND date=:date AND hour=:hour")
    void updateStatus(boolean newStatus, String barberFirstName, String date, int hour);

    @Query("UPDATE appointment SET status=:newStatus WHERE client_first_name=:clientFirstName AND date=:date AND hour=:hour")
    void updateStatus2(boolean newStatus, String clientFirstName, String date, int hour);

    @Insert
    void insertAppointment(Appointment... appointments);
}
