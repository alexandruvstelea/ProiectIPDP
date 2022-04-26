package com.example.barbersapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Appointment.class}, version = 1)
public abstract class  AppointmentsDB extends RoomDatabase {

    public abstract AppointmentsDAO appointmentsDAO();

    private static AppointmentsDB INSTANCE;

    public static AppointmentsDB getDBInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppointmentsDB.class, "APPOINTMENTS_DATABASE")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
