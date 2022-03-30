package com.example.barbersapp;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ShopDAO {

    @Query("SELECT * FROM shop")
    List<Shop> getAllShops();

    @Query("SELECT * FROM shop WHERE owner_user_name =:userName")
    List<Shop> getOwnerShops(String userName);

    @Query("SELECT shop_name FROM shop")
    List<String> getAllExistingShopNames();

    @Query("SELECT id FROM shop WHERE shop_name=:shopName")
    int getShopID(String shopName);

    @Query("SELECT shop_name FROM shop WHERE id=:employerID")
    String getEmployerName(int employerID);

    @Query("SELECT owner_user_name FROM shop WHERE shop_name=:shopName")
    String getOwnerUserName(String shopName);

    @Insert
    void insertShop(Shop... shops);
}
