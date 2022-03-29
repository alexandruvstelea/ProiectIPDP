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

    @Query("SELECT employer_id FROM user WHERE user_name=:userName")
    int getEmployerID(String userName);

    @Query("UPDATE user SET employer_id=:newEmployerID WHERE user_name=:userName")
    void updateEmployerID(int newEmployerID, String userName);

    @Insert
    void insertUser(User...users);

    @Delete
    void delete(User user);
}
