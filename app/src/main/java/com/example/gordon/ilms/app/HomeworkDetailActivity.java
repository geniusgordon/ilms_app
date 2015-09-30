package com.example.gordon.ilms.app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.HtmlFix;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.http.HomeworkRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Attachment;
import com.example.gordon.ilms.model.Homework;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gordon on 9/28/15.
 */
public class HomeworkDetailActivity extends DetailActivity<Homework> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");

        Log.d(LOG_TAG, item.getTitle());

        String timeStr = item.getTimeStr(new SimpleDateFormat("yyyy/MM/dd"));

        titleTxt.setText(item.getTitle());
        authorTxt.setText("作業");
        timeTxt.setText(timeStr);

        request = new HomeworkRequest(course, item,
            new Response.Listener<Homework>() {
                @Override
                public void onResponse(Homework response) {
                    item = response;
                    contentTxt.setText(HtmlFix.correctLinkPaths(Html.fromHtml(response.getDescription())));
                    progressBar.setVisibility(View.INVISIBLE);

                    if (item.getAttachments().size() > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("<p><strong>附件</strong><br><br>");
                        for (Attachment attach : item.getAttachments()) {
                            String a = "<a href='%s'>%s</a>&nbsp;%s<br><br>";
                            stringBuilder.append(String.format(a, attach.getUrl(), attach.getTitle(), attach.getSize()));
                        }
                        stringBuilder.append("</p>");
                        contentTxt.append(Html.fromHtml(stringBuilder.toString()));
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                        Toast.makeText(getApplicationContext(), "無法連線，請稍後再試", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homework_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.event) {
            Intent intent = new Intent(Intent.ACTION_EDIT);
            intent.setType("vnd.android.cursor.item/event");
            intent.putExtra("allDay", true);

            Date date = HomeworkDetailActivity.this.item.getTime();
            if (date != null) {
                intent.putExtra("beginTime", date.getTime());
                intent.putExtra("endTime", date.getTime());
            }
            intent.putExtra("title", HomeworkDetailActivity.this.item.getTitle());
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
