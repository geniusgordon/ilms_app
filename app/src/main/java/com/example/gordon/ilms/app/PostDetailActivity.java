package com.example.gordon.ilms.app;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.http.ReplyListRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.Post;
import com.example.gordon.ilms.model.Reply;
import com.example.gordon.ilms.model.ReplyList;
import com.github.clans.fab.FloatingActionButton;

import java.util.List;

public class PostDetailActivity extends BaseActivity {
    final static String LOG_TAG = "PostDetailActivity";
    final static int REPLY = 2;

    private LinearLayout replyLayout;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private FloatingActionButton btn;

    private Post post;
    private Course course;
    private String title;

    private ReplyListRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());
        }
        setTheme(R.style.AppTheme_White);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        course = (Course) getIntent().getSerializableExtra("course");
        post = (Post) getIntent().getSerializableExtra("post");
        title = post.getTitle();

        replyLayout = (LinearLayout) findViewById(R.id.reply_layout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btn = (FloatingActionButton) findViewById(R.id.reply_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PostDetailActivity.this, ComposeActivity.class);
                intent.putExtra("course", course);
                intent.putExtra("action", "reply");
                intent.putExtra("title", title);
                intent.putExtra("id", String .valueOf(post.getId()));
                startActivityForResult(intent, REPLY);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        getSupportActionBar().setTitle("");

        ((TextView) findViewById(R.id.title)).setText(title);

        getReplies();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_post_detail, menu);
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
            supportFinishAfterTransition();
            return true;
        } else if (id == R.id.open) {
            String url = this.request.getOpenUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
            startActivity(Intent.createChooser(intent, "開啟網頁版iLms"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REPLY) {
            if (resultCode == RESULT_OK) {
                refresh();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void refresh() {
        replyLayout.removeAllViews();
        progressBar.setVisibility(View.VISIBLE);
        getReplies();
    }

    private void getReplies() {
        request = new ReplyListRequest(course, post,
            new Response.Listener<ReplyList>() {
                @Override
                public void onResponse(ReplyList response) {
                    title = response.getTitle();
                    ((TextView) findViewById(R.id.title)).setText(title);

                    LayoutInflater layoutInflater = LayoutInflater.from(PostDetailActivity.this);
                    for (Reply reply: response.getReplies()) {
                        ViewGroup view = (ViewGroup) layoutInflater.inflate(R.layout.reply_item, null);
                        ReplyViewHolder viewHolder = new ReplyViewHolder(view, reply);
                        replyLayout.addView(viewHolder.getView());
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(getApplicationContext(), "無法連線，請稍後再試", Toast.LENGTH_SHORT).show();
                    }
                    progressBar.setVisibility(View.INVISIBLE);                }
            });
        RequestQueueSingleton.getInstance(this).addToRequestQueue(request);
    }


    @Override
    public Intent isIntentUri(Uri uri, ActivityDispatcher activity) {
        Log.d(LOG_TAG, "isIntentUri");
        Log.d(LOG_TAG, uri.toString());

        Intent intent = new Intent(activity,PostDetailActivity.class);
        Course course = new Course();
        course.setTitle("", "");

        String[] paths = uri.getEncodedPath()==null ? null : uri.getEncodedPath().split("/");
        if (paths == null)
            return null;

        try {
            if (paths[1].startsWith("course.php")) {
                String f = uri.getQueryParameter("f");
                if (f.equals("forum")) {
                    course.setId(Long.parseLong(uri.getQueryParameter("courseID")));
                    Post post = new Post();
                    post.setId(Long.parseLong(uri.getQueryParameter("tid")));
                    intent.putExtra("course", course);
                    intent.putExtra("post", post);
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
