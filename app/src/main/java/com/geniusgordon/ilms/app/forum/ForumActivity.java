package com.geniusgordon.ilms.app.forum;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.ActivityDispatcher;
import com.geniusgordon.ilms.app.BaseActivity;
import com.geniusgordon.ilms.app.adapter.PostListAdapter;
import com.geniusgordon.ilms.http.forum.ForumPageRequest;
import com.geniusgordon.ilms.http.forum.PostListRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.http.ResponseMessage;
import com.geniusgordon.ilms.model.Course;
import com.geniusgordon.ilms.model.Post;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ForumActivity extends BaseActivity {
    final static String LOG_TAG = "ForumActivity";
    final static int COMPOSE = 1;

    private Toolbar toolbar;
    private ProgressBar progressBar;
    private FloatingActionButton btn;

    private PostListAdapter listAdapter;
    private ListView listView;

    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean gettingList;

    private Course course;
    private int page;
    private int totalPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setSharedElementEnterTransition(new Fade());
            getWindow().setSharedElementExitTransition(new Fade());
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }
        setTheme(R.style.AppTheme_Green);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        course = (Course) getIntent().getSerializableExtra("course");
        page = 1;
        totalPage = 1;
        gettingList = false;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("討論區");

        msgTxt = (TextView) findViewById(R.id.list_msg);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn = (FloatingActionButton) findViewById(R.id.edit_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumActivity.this, ComposeActivity.class);
                intent.putExtra("course", course);
                intent.putExtra("action", "post");
                startActivityForResult(intent, COMPOSE);
            }
        });

        listAdapter = new PostListAdapter(this, new ArrayList<Post>());
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = (Post) parent.getItemAtPosition(position);
                Intent intent = new Intent(ForumActivity.this, PostDetailActivity.class);
                intent.putExtra("post", post);
                intent.putExtra("course", course);

                Pair<View, String> p1 = Pair.create((View) parent, "open_item");
                Pair<View, String> p2 = Pair.create((View) btn, "fab");
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(ForumActivity.this, p1, p2);
                startActivity(intent, options.toBundle());
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem == totalItemCount - visibleItemCount - 1)
                    getList();
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(LOG_TAG, "onRefresh");
                refreshList();
            }
        });

        refreshList();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == COMPOSE) {
            if (resultCode == RESULT_OK) {
                refreshList();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forum, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void refreshList() {
        msgTxt.setText("");
        listAdapter.clearItems();
        page = 1;
        getList();
        getPage();
    }

    public void getList() {
        if (page > totalPage)
            return;
        if (gettingList)
            return;
        gettingList = true;

        if (!swipeRefreshLayout.isRefreshing()) {
            progressBar.setVisibility(View.VISIBLE);
        }
        Log.d(LOG_TAG, String.format("page %d of %d", page, totalPage));
        PostListRequest request = new PostListRequest(course, page,
                new Response.Listener<List<Post>>() {
                    @Override
                    public void onResponse(List<Post> response) {
                        listAdapter.addItems(response);
                        progressBar.setVisibility(View.INVISIBLE);
                        swipeRefreshLayout.setRefreshing(false);
                        page++;
                        gettingList = false;

                        if (listAdapter.getCount() == 0) {
                            msgTxt.setText("目前尚無討論");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            setMessage(ResponseMessage.TIMEOUT);
                        } else {
                            setMessage(ResponseMessage.NO_PERMISSION);
                            btn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(getApplicationContext(),
                                            ResponseMessage.getMessage(ResponseMessage.NO_PERMISSION),
                                            Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                        swipeRefreshLayout.setRefreshing(false);
                        gettingList = false;
                    }
                });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    public void getPage() {
        ForumPageRequest request = new ForumPageRequest(course,
            new Response.Listener<Integer>() {
                @Override
                public void onResponse(Integer response) {
                    totalPage = response;
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    totalPage = 1;
                }
            });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


    @Override
    public Intent isIntentUri(Uri uri, ActivityDispatcher activity) {
        Log.d(LOG_TAG, "isIntentUri");
        Log.d(LOG_TAG, uri.toString());

        Intent intent = new Intent(activity, ForumActivity.class);
        Course course = new Course();
        course.setTitle("", "");

        String[] paths = uri.getEncodedPath()==null ? null : uri.getEncodedPath().split("/");
        if (paths == null)
            return null;

        try {
            if (paths[1].startsWith("course.php")) {
                String f = uri.getQueryParameter("f");
                if (f.equals("forumlist")) {
                    course.setId(Long.parseLong(uri.getQueryParameter("courseID")));
                    intent.putExtra("course", course);
                    return intent;
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        } catch (NumberFormatException e) {
            return null;
        }

        return null;
    }

}
