package com.example.guest.mapsapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.guest.mapsapp.model.AppDatabase;
import com.example.guest.mapsapp.model.PastRecordsAdapter;
import com.example.guest.mapsapp.model.User;
import com.example.guest.mapsapp.model.onListClick;

import java.util.List;

public class PastRecordsActivity extends AppCompatActivity implements onListClick {

    RecyclerView pastRecordList;
    PastRecordsAdapter pastRecordsAdapter;
    private AppDatabase appDatabase;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pastRecordList = (RecyclerView) findViewById(R.id.past_record_list);
        appDatabase = AppDatabase.getDatabase(this.getApplicationContext());

        pastRecordList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        new PopulateDbAsync(appDatabase).execute();
    }

    @Override
    public void onListItemClick(User user) {

    }

    private class PopulateDbAsync extends AsyncTask<Object, Object, List<User>> {

        private final AppDatabase mDb;

        PopulateDbAsync(AppDatabase db) {
            mDb = db;

        }

        @Override
        protected List<User> doInBackground(final Object... params) {
            Log.d("Async" +"Database:", "count"+mDb.userDao().getAll().size());
            return mDb.userDao().getAll();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            pastRecordsAdapter = new PastRecordsAdapter(users, PastRecordsActivity.this, PastRecordsActivity.this);
            pastRecordList.setAdapter(pastRecordsAdapter);

        }
    }
}
