package com.geniusgordon.ilms.app;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.geniusgordon.ilms.R;

import java.util.Calendar;

public class MySchedule extends DrawerActivity {
    String LOG_TAG = "MySchedule";

    Calendar date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);
        initDrawer();

        date = Calendar.getInstance();
        Log.d(LOG_TAG, date.get(Calendar.YEAR) + " / " + date.get(Calendar.MONTH));
        getSupportActionBar().setTitle(date.get(Calendar.YEAR) + " / " + date.get(Calendar.MONTH));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

}
