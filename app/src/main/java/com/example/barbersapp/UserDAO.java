package com.example.barbersapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDAO {
    @Query("SELECT * FROM user")
    List<User> getAllUsers();

    @Query("SELECT user_name FROM user")
    List<String> getAllExistingUserNames();

    @Query("SELECT password FROM user WHERE user_name =:userName")
    String getPassword(String userName);

    @Query("SELECT account_type FROM user WHERE user_name=:userName")
    String getAccountType(String userName);

    @Insert
    void insertUser(User...users);

    @Delete
    void delete(User user);
}
