package com.example.barbersapp;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Shop {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "shop_name")
    private String shopName;
    @ColumnInfo(name = "shop_address")
    private String shopAddress;
    @ColumnInfo(name = "owner_user_name")
    private String ownerUserName;

    public Shop(String shopName, String shopAddress, String ownerUserName) {
        this.shopName = shopName;
        this.shopAddress = shopAddress;
        this.ownerUserName = ownerUserName;
    }

    public int getId() {
        return id;
    }

    public String getShopName() {
        return shopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setId(int id) {
        this.id = id;
    }
}
