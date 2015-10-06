package com.geniusgordon.ilms.app.course;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.BaseActivity;
import com.geniusgordon.ilms.http.BaseRequest;
import com.geniusgordon.ilms.http.ResponseMessage;
import com.geniusgordon.ilms.model.Course;
import com.geniusgordon.ilms.model.CourseItem;

public class DetailActivity<T extends CourseItem> extends BaseActivity {
    final static String LOG_TAG = "DetailActivity";

    protected Toolbar toolbar;
    protected T item;
    protected Course course;

    protected TextView titleTxt;
    protected TextView authorTxt;
    protected TextView timeTxt;
    protected TextView contentTxt;
    protected ProgressBar progressBar;

    protected BaseRequest<T> request;
    protected Response.ErrorListener errorListener = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                setMessage(ResponseMessage.TIMEOUT);
            } else {
                setMessage(ResponseMessage.NO_PERMISSION);
            }
            progressBar.setVisibility(View.INVISIBLE);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Fade());
            getWindow().setExitTransition(new Fade());

        }
        setTheme(R.style.AppTheme_White);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        item = (T) getIntent().getSerializableExtra("item");
        course = (Course) getIntent().getSerializableExtra("course");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        msgTxt = (TextView) findViewById(R.id.msgTxt);

        titleTxt = (TextView) findViewById(R.id.title);
        authorTxt = (TextView) findViewById(R.id.author);
        timeTxt = (TextView) findViewById(R.id.time);
        contentTxt = (TextView) findViewById(R.id.content);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        contentTxt.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
    
}
