package com.geniusgordon.ilms.app;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

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

    public void setMessage() {
        if (msgTxt != null)
            msgTxt.setText("");
    }

    public void setMessage(int t) {
        if (msgTxt != null)
            msgTxt.setText(getString(t));
    }

}
