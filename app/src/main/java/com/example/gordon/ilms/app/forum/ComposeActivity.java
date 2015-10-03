package com.example.gordon.ilms.app.forum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.http.forum.ForumPostRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.Preferences;

import java.util.HashMap;
import java.util.Map;

public class ComposeActivity extends AppCompatActivity {
    final static String LOG_TAG = "ComposeActivity";

    private Toolbar toolbar;
    private EditText titleEdit;
    private EditText nameEdit;
    private EditText contentEdit;
    private ProgressBar progressBar;
    private CheckBox checkBox;

    private Course course;
    private String action;
    private String title;
    private String id;
    private boolean sending;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_White);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        course = (Course) getIntent().getSerializableExtra("course");
        action = getIntent().getStringExtra("action");
        title = getIntent().getStringExtra("title");
        id = getIntent().getStringExtra("id");

        titleEdit = (EditText) findViewById(R.id.title);
        nameEdit = (EditText) findViewById(R.id.name);
        contentEdit = (EditText) findViewById(R.id.content);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

        if (action.equals("post"))
            getSupportActionBar().setTitle("發表討論");
        else {
            getSupportActionBar().setTitle("回應");
            titleEdit.setKeyListener(null);
            titleEdit.setText(title);
        }

        String name = Preferences.getInstance(getApplicationContext()).getAccount().getName();
        nameEdit.setText(name);

        progressBar.setVisibility(View.INVISIBLE);
        sending = false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_compose, menu);
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
        } else if (id == R.id.send) {
            alertSend();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void alertSend() {
        if (sending)
            return;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("即將送出");
        builder.setMessage("你確定要送出嗎？");
        builder.setPositiveButton("確定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                send();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    private void send() {
        sending = true;
        progressBar.setVisibility(View.VISIBLE);

        String content = Html.toHtml(contentEdit.getText());
        if (checkBox.isChecked())
            content = content + "<br>--<br><br>Sent from my Android<br><br>";

        Log.d(LOG_TAG, titleEdit.getText().toString());
        Log.d(LOG_TAG, nameEdit.getText().toString());

        Map<String, String> params = new HashMap<String, String>();
        params.put("courseID", String.valueOf(course.getId()));
        params.put("fmTitle", titleEdit.getText().toString());
        params.put("fmNickname", nameEdit.getText().toString());
        params.put("fmEmail", Preferences.getInstance().getAccount().getEmail());
        params.put("fmNote", content);

        ForumPostRequest request = new ForumPostRequest(action, id, params, null,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "已送出", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "發生錯誤，請稍後再試", Toast.LENGTH_SHORT).show();
                        sending = false;
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
