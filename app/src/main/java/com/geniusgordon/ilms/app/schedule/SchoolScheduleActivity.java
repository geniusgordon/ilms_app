package com.geniusgordon.ilms.app.schedule;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.DrawerActivity;
import com.geniusgordon.ilms.app.adapter.EventListAdapter;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.http.schedule.MyScheduleRequest;
import com.geniusgordon.ilms.http.schedule.SchoolScheduleRequest;
import com.geniusgordon.ilms.model.DateWithEvents;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SchoolScheduleActivity extends DrawerActivity {
    String LOG_TAG = "SchoolSchedActivity";

    EventListAdapter listAdapter;
    @Bind(R.id.title) TextView titleTxt;
    @Bind(R.id.list_view) ListView listView;
    @Bind(R.id.progressBar) ProgressBar progressBar;

    Calendar currDate;
    Request request;
    Response.Listener<List<DateWithEvents>> listener = new Response.Listener<List<DateWithEvents>>() {
        @Override
        public void onResponse(List<DateWithEvents> response) {
            progressBar.setVisibility(View.INVISIBLE);
            listAdapter.addItems(response);
            listView.setSelection(listAdapter.getTodayInd());
        }
    };
    Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {

        }
    };
    AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            if (totalItemCount == 0)
                return;

            DateWithEvents e = (DateWithEvents) view.getItemAtPosition(firstVisibleItem);
            Calendar cal = Calendar.getInstance();
            cal.setTime(e.getDate());
            if (!cal.equals(currDate)) {
                currDate = cal;
                titleTxt.setText(getDateString(cal));
            }
        }
    };

    public String getDateString(Calendar date) {
        return date.get(Calendar.YEAR) + " / " + (date.get(Calendar.MONTH) + 1);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Grey);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_schedule);
        initDrawer();

        ButterKnife.bind(this);

        currDate = Calendar.getInstance();
        getSupportActionBar().setTitle("");
        titleTxt.setText(getDateString(currDate));

        listAdapter = new EventListAdapter(currDate, this, new ArrayList<DateWithEvents>());
        listView.setAdapter(listAdapter);
        listView.setOnScrollListener(onScrollListener);

        request = new SchoolScheduleRequest(listener, errorListener);
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
        if (id == R.id.today) {
            listView.smoothScrollToPositionFromTop(listAdapter.getTodayInd(), 0, 100);
        }
        return super.onOptionsItemSelected(item);
    }
}
