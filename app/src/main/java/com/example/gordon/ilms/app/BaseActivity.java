package com.example.gordon.ilms.app;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.http.ResponseMessage;

/**
 * Created by gordon on 9/30/15.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public final static String LOG_TAG = "BaseActivity";

    protected TextView msgTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public Intent isIntentUri(Uri uri, ActivityDispatcher activity) {
        Log.d(LOG_TAG, "isIntentUri");
        Log.d(LOG_TAG, uri.toString());
        return null;
    }

    public void setMessage(int t) {
        if (msgTxt != null)
            msgTxt.setText(ResponseMessage.getMessage(t));
    }

}
