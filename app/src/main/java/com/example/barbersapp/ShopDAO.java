package com.example.barbersapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShopDAO {

    @Query("SELECT * FROM shop")
    List<Shop> getAllShops();

    @Query("SELECT shop_name FROM shop")
    List<String> getAllExistingShopNames();

    @Insert
    void insertShop(Shop...shops);
}
