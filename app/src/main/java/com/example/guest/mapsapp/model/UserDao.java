package com.example.guest.mapsapp.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by guest on 10/10/17.
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addLocationData(User user);
}
