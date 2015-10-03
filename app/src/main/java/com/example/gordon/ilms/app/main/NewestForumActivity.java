package com.example.gordon.ilms.app.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.gordon.ilms.R;
import com.example.gordon.ilms.app.ActivityDispatcher;
import com.example.gordon.ilms.http.main.NewestForumRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;

/**
 * Created by gordon on 10/1/15.
 */
public class NewestForumActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Green);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("最新討論");
        drawer.setSelection(newestForum);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        drawer.setSelection(newestForum);
    }

    @Override
    public void getHomeItem() {
        request = new NewestForumRequest(listener, errorListener);
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    public Intent isIntentUri(Uri uri, ActivityDispatcher activity) {
        return null;
    }

}
