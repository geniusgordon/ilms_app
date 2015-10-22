package com.geniusgordon.ilms.app.schedule;

import android.os.Bundle;
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
import com.geniusgordon.ilms.model.DateWithEvents;
import com.geniusgordon.ilms.model.Event;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyScheduleActivity extends DrawerActivity {
    String LOG_TAG = "MyScheduleActivity";

    EventListAdapter listAdapter;
    @Bind(R.id.title)
    TextView titleTxt;
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;

    Calendar currDate;
    Calendar startDate;
    Calendar endDate;
    boolean beforeRequesting;
    boolean afterRequesting;
    Request request;
    Response.Listener<List<DateWithEvents>> afterListener = new Response.Listener<List<DateWithEvents>>() {
        @Override
        public void onResponse(List<DateWithEvents> response) {
            progressBar.setVisibility(View.INVISIBLE);
            listAdapter.addItems(response);
            afterRequesting = false;
//            Log.d(LOG_TAG, String.valueOf(response.size()));
        }
    };
    Response.Listener<List<DateWithEvents>> beforeListener = new Response.Listener<List<DateWithEvents>>() {
        @Override
        public void onResponse(List<DateWithEvents> response) {
            progressBar.setVisibility(View.INVISIBLE);

            int index = listView.getFirstVisiblePosition();
            View v = listView.getChildAt(0);
            int top = (v == null) ? 0 : v.getTop();
//            Log.d(LOG_TAG, "first visible: " + index);
//            Log.d(LOG_TAG, "top: " + top);
            listAdapter.addItems(0, response);
            listView.setSelectionFromTop(response.size() + index, top);

            beforeRequesting = false;
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

            if (firstVisibleItem <= 1) {
                e = (DateWithEvents) view.getItemAtPosition(0);
                cal = Calendar.getInstance();
                cal.setTime(e.getDate());
                cal.add(Calendar.MONTH, -1);
                eventBeforeRequest(cal);
            }

            if (firstVisibleItem == totalItemCount - visibleItemCount - 1) {
                e = (DateWithEvents) view.getItemAtPosition(totalItemCount-1);
                cal = Calendar.getInstance();
                cal.setTime(e.getDate());
                cal.add(Calendar.MONTH, 1);
                eventAfterRequest(cal);
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
        startDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        getSupportActionBar().setTitle("");
        titleTxt.setText(getDateString(currDate));

        afterRequesting = true;
        beforeRequesting = false;

        listAdapter = new EventListAdapter(currDate, this, new ArrayList<DateWithEvents>());
        listView.setAdapter(listAdapter);
        listView.setOnScrollListener(onScrollListener);

        request = getAfterRequest(currDate);
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    public void eventAfterRequest(Calendar cal) {
        if (!afterRequesting && cal.after(endDate)) {
            Log.d(LOG_TAG, getDateString(cal));
            endDate = cal;
            afterRequesting = true;
            request = getAfterRequest(cal);
            RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
        }
    }

    public void eventBeforeRequest(Calendar cal) {
        if (!beforeRequesting && cal.before(startDate)) {
            Log.d(LOG_TAG, getDateString(cal));
            startDate = cal;
            beforeRequesting = true;
            request = getBeforeRequest(cal);
            RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
        }
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

    public Request getBeforeRequest(Calendar date) {
        return new MyScheduleRequest(date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, beforeListener, errorListener);
    }

    public Request getAfterRequest(Calendar date) {
        return new MyScheduleRequest(date.get(Calendar.YEAR), date.get(Calendar.MONTH)+1, afterListener, errorListener);
    }

}
