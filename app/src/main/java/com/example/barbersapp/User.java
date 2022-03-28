package com.example.barbersapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "user_name")
    private String username;
    @ColumnInfo(name = "first_name")
    private String firstName;
    @ColumnInfo(name = "last_name")
    private String lastName;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "gender")
    private String gender;
    @ColumnInfo(name="account_type")
    private String accountType;
    @ColumnInfo(name="employer_id")
    private int employerID;

    public User(String username, String firstName, String lastName, String password, String gender, String accountType, int employerID) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.gender = gender;
        this.accountType = accountType;
        this.employerID = employerID;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getGender() {
        return gender;
    }

    public String getAccountType() { return accountType; }

    public int getEmployerID() { return employerID; }

    public void setId(int id) {
        this.id = id;
    }
}