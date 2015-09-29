package com.example.gordon.ilms.app;

import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
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
import com.example.gordon.ilms.model.Post;
import com.example.gordon.ilms.model.Reply;

import java.util.List;

public class PostDetailActivity extends AppCompatActivity {
    final static String LOG_TAG = "PostDetailActivity";

    private LinearLayout replyLayout;
    private Toolbar toolbar;
    private ProgressBar progressBar;
    private Post post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        post = (Post) getIntent().getSerializableExtra("post");

        replyLayout = (LinearLayout) findViewById(R.id.reply_layout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
        getSupportActionBar().setTitle("");

        ((TextView) findViewById(R.id.title)).setText(post.getTitle());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d(LOG_TAG, "setStatusBarColor");
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
            int color = typedValue.data;
            getWindow().setStatusBarColor(color);
        }

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
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }

    private void getReplies() {
        ReplyListRequest request = new ReplyListRequest(post,
            new Response.Listener<List<Reply>>() {
                @Override
                public void onResponse(List<Reply> response) {
                    LayoutInflater layoutInflater = LayoutInflater.from(PostDetailActivity.this);
                    for (Reply reply: response) {
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
}
