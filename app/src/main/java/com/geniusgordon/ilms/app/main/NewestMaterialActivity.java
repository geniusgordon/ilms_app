package com.geniusgordon.ilms.app.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.ActivityDispatcher;
import com.geniusgordon.ilms.http.main.NewestMaterialRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;

/**
 * Created by gordon on 10/1/15.
 */
public class NewestMaterialActivity extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Blue);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("最新文件");
        drawer.setSelection(newestMaterial);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        drawer.setSelection(newestMaterial);
    }

    @Override
    public void getHomeItem() {
        request = new NewestMaterialRequest(listener, errorListener);
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    public Intent isIntentUri(Uri uri, ActivityDispatcher activity) {
        return null;
    }
}
