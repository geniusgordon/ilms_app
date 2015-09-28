package com.example.gordon.ilms.app;

import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.http.AnnouncementRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Announcement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by gordon on 9/28/15.
 */
public class AnnouncementDetailActivity extends DetailActivity<Announcement> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");

        Log.d(LOG_TAG, item.getTitle());

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        titleTxt.setText(item.getTitle());
        authorTxt.setText("課程公告");
        timeTxt.setText(df.format(item.getTime()));

        AnnouncementRequest request = new AnnouncementRequest(item,
            new Response.Listener<Announcement>() {
                @Override
                public void onResponse(Announcement response) {
                    AnnouncementDetailActivity.this.item = response;
                    contentTxt.setText(Html.fromHtml(response.getContent()));
                    progressBar.setVisibility(View.INVISIBLE);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
