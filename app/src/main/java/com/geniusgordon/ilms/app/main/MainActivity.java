package com.geniusgordon.ilms.app.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.ActivityDispatcher;
import com.geniusgordon.ilms.app.DrawerActivity;
import com.geniusgordon.ilms.app.adapter.HomeItemListAdapter;
import com.geniusgordon.ilms.http.main.HomeItemListRequest;
import com.geniusgordon.ilms.http.main.NewestAnnouncementRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.model.HomeItem;
import com.geniusgordon.ilms.model.Preferences;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends DrawerActivity {

    private HomeItemListAdapter listAdapter;

    @Bind(R.id.list_view) ListView listView;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.swipeRefreshLayout) SwipeRefreshLayout swipeRefreshLayout;

    protected HomeItemListRequest request;
    protected Response.Listener<List<HomeItem>> listener = new Response.Listener<List<HomeItem>>() {
        @Override
        public void onResponse(List<HomeItem> response) {
            listAdapter.addItems(response);
            progressBar.setVisibility(View.INVISIBLE);
            swipeRefreshLayout.setRefreshing(false);

            if (Preferences.getInstance(getApplicationContext()).getAccount() == null)
                setMessage(R.string.not_login);
            else
                setMessage();
        }
    };

    protected Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                setMessage(R.string.timeout);
            } else {
                setMessage(R.string.not_login);
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();

        ButterKnife.bind(this);

        getSupportActionBar().setTitle(getString(R.string.latest_announcement));
        drawer.setSelection(newestAnnouncement);

        msgTxt = (TextView) findViewById(R.id.msgTxt);

        listAdapter = new HomeItemListAdapter(this, new ArrayList<HomeItem>());
        listAdapter.setShowHeader(false);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HomeItem item = (HomeItem) parent.getItemAtPosition(position);
                if (item.isHeader())
                    return;
                String url = item.getUrl();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                listAdapter.clearItems();
                getHomeItem();
            }
        });

        getHomeItem();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        listAdapter.clearItems();
        getHomeItem();
        drawer.setSelection(newestAnnouncement);
    }

    public void getHomeItem() {
        request = new NewestAnnouncementRequest(listener, errorListener);
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN_REQUEST && resultCode == RESULT_OK) {
            listAdapter.clearItems();
            progressBar.setVisibility(View.VISIBLE);
            getHomeItem();
        }
    }

    @Override
    public Intent isIntentUri(Uri uri, ActivityDispatcher activity) {
        Log.d(LOG_TAG, "isIntentUri");
        Log.d(LOG_TAG, uri.toString());

        String path = uri.getEncodedPath()==null ? "" : uri.getEncodedPath();
        if (path.startsWith("/home.php")) {
            Intent intent = new Intent(activity, MainActivity.class);
            return intent;
        }

        return null;
    }
}
