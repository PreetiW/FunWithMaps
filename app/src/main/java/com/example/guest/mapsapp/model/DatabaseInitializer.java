package com.example.guest.mapsapp.model;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

/**
 * Created by guest on 10/10/17.
 */

public class DatabaseInitializer {

    public static void insertAsync(@NonNull final AppDatabase db, User user) {
        InsertDbAsync task = new InsertDbAsync(db, user);
        task.execute();
    }

    public static void populateAsync(@NonNull final AppDatabase db) {
        PopulateDbAsync populateTask = new PopulateDbAsync(db);
        populateTask.execute();
    }


    private static class InsertDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;
        private final User mUser;

        InsertDbAsync(AppDatabase db, User user) {
            mDb = db;
            mUser = user;
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDb.userDao().addLocationData(mUser);
            return null;
        }

    }

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;

        }

        @Override
        protected Void doInBackground(final Void... params) {
            Log.d("Async" +"Database:", "count"+mDb.userDao().getAll().size());
            return null;
        }

    }
}
