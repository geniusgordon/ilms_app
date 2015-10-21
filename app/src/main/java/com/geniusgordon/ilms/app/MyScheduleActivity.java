package com.geniusgordon.ilms.app;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.adapter.EventListAdapter;
import com.geniusgordon.ilms.app.adapter.HomeItemListAdapter;
import com.geniusgordon.ilms.http.MyScheduleRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.model.DateWithEvents;
import com.geniusgordon.ilms.model.Event;
import com.geniusgordon.ilms.model.HomeItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyScheduleActivity extends DrawerActivity {
    String LOG_TAG = "MyScheduleActivity";

    private EventListAdapter listAdapter;
    @Bind(R.id.list_view) ListView listView;
    @Bind(R.id.progressBar) ProgressBar progressBar;

    Calendar date;
    Request request;
    Response.Listener listener = new Response.Listener<List<Event>>() {
        @Override
        public void onResponse(List<Event> response) {
            progressBar.setVisibility(View.INVISIBLE);
            listAdapter.addItems(DateWithEvents.fromEventList(response));
        }
    };
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Grey);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);
        initDrawer();

        ButterKnife.bind(this);

        date = Calendar.getInstance();
        Log.d(LOG_TAG, date.get(Calendar.YEAR) + " / " + (date.get(Calendar.MONTH) + 1));
        getSupportActionBar().setTitle(date.get(Calendar.YEAR) + " / " + (date.get(Calendar.MONTH) + 1));

        listAdapter = new EventListAdapter(this, new ArrayList<DateWithEvents>());
        listView.setAdapter(listAdapter);

        request = new MyScheduleRequest(date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, listener, errorListener);
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
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
