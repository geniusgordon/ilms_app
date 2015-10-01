package com.example.gordon.ilms.app;

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
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.app.adapter.HomeItemListAdapter;
import com.example.gordon.ilms.http.HomeItemListRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.HomeItem;
import com.example.gordon.ilms.model.Material;
import com.example.gordon.ilms.model.Preferences;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends DrawerActivity {

    private ListView listView;
    private HomeItemListAdapter listAdapter;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView msgTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        msgTxt = (TextView) findViewById(R.id.msg);

        listAdapter = new HomeItemListAdapter(this, new ArrayList<HomeItem>());
        listView = (ListView) findViewById(R.id.list_view);
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

    public void getHomeItem() {
        HomeItemListRequest request = new HomeItemListRequest(
            new Response.Listener<List<HomeItem>>() {
                @Override
                public void onResponse(List<HomeItem> response) {
                    listAdapter.addItems(response);
                    progressBar.setVisibility(View.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);

                    if (Preferences.getInstance(getApplicationContext()).getAccount() == null)
                        msgTxt.setText("尚未登入");
                    else
                        msgTxt.setText("");
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        msgTxt.setText("無法連線，請稍後再試");
                        Toast.makeText(getApplicationContext(), "無法連線，請稍後再試", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        );
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == LOGIN && resultCode == RESULT_OK) {
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
