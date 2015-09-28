package com.example.gordon.ilms.app;

import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gordon.ilms.R;
import com.example.gordon.ilms.model.Course;

public class DetailActivity<T> extends AppCompatActivity {
    final static String LOG_TAG = "DetailActivity";

    protected Toolbar toolbar;
    protected T item;
    protected Course course;

    protected TextView titleTxt;
    protected TextView authorTxt;
    protected TextView timeTxt;
    protected TextView contentTxt;
    protected ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        item = (T) getIntent().getSerializableExtra("item");
        course = (Course) getIntent().getSerializableExtra("course");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        titleTxt = (TextView) findViewById(R.id.title);
        authorTxt = (TextView) findViewById(R.id.author);
        timeTxt = (TextView) findViewById(R.id.time);
        contentTxt = (TextView) findViewById(R.id.content);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        contentTxt.setMovementMethod(LinkMovementMethod.getInstance());

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Log.d(LOG_TAG, "setStatusBarColor");
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = getTheme();
            theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
            int color = typedValue.data;
            getWindow().setStatusBarColor(color);
        }
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
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
