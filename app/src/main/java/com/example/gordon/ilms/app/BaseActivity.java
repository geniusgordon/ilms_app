package com.example.gordon.ilms.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by gordon on 9/30/15.
 */
public class BaseActivity extends AppCompatActivity {
    final static String LOG_TAG = "BaseActivity";

    public Intent isIntentUri(Uri uri, ActivityDispatcher activity) {
        Log.d(LOG_TAG, "isIntentUri");
        Log.d(LOG_TAG, uri.toString());
        return null;
    }

}
