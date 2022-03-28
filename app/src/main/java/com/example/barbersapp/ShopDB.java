package com.example.barbersapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Shop.class}, version = 1)
public abstract class ShopDB extends RoomDatabase {

    public abstract ShopDAO shopDAO();

    private static ShopDB INSTANCE;

    public static ShopDB getDBInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ShopDB.class, "SHOP_DATABASE")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
