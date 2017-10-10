package com.example.guest.mapsapp.model;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by guest on 10/10/17.
 */

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase APPDATABASEINSTANCE;

    public abstract UserDao userDao();


    public static AppDatabase getDatabase(Context context) {
        if (APPDATABASEINSTANCE == null) {
            APPDATABASEINSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "user-location")
                            .build();
        }
        return APPDATABASEINSTANCE;
    }

}
