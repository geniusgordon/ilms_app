package com.geniusgordon.ilms.app.course;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.android.volley.Response;
import com.geniusgordon.ilms.HtmlFix;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.ActivityDispatcher;
import com.geniusgordon.ilms.app.AnalyticsApplication;
import com.geniusgordon.ilms.http.detail.HomeworkRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.model.Attachment;
import com.geniusgordon.ilms.model.Course;
import com.geniusgordon.ilms.model.Homework;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

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

        String timeStr = item.getTimeStr(new SimpleDateFormat("yyyy/MM/dd hh:mm"));

        iconImg.setImageResource(R.drawable.ic_assignment_black_48dp);
        titleTxt.setText(item.getTitle());
        authorTxt.setText(getResources().getString(R.string.homework));
        timeTxt.setTextColor(Color.RED);
        timeTxt.setTypeface(null, Typeface.BOLD);
        timeTxt.setText(timeStr);

        request = new HomeworkRequest(course, item,
            new Response.Listener<Homework>() {
                @Override
                public void onResponse(Homework response) {
                    item = response;
                    contentTxt.setText(HtmlFix.correctLinkPaths(Html.fromHtml(response.getDescription())));
                    progressBar.setVisibility(View.INVISIBLE);

                    titleTxt.setText(item.getTitle());
                    timeTxt.setText(item.getTimeStr(new SimpleDateFormat("yyyy/MM/dd hh:mm")));

                    if (item.getAttachments().size() > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        String attachmentStr = getString(R.string.attachment);
                        stringBuilder.append("<br><br><p><strong>" + attachmentStr + "</strong><br><br>");
                        for (Attachment attach : item.getAttachments()) {
                            String a = "<a href='%s'>%s</a>&nbsp;%s<br><br>";
                            stringBuilder.append(String.format(a, attach.getUrl(), attach.getTitle(), attach.getSize()));
                        }
                        stringBuilder.append("</p>");
                        contentTxt.append(Html.fromHtml(stringBuilder.toString()));
                    }
                }
            }, errorListener);
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
            Tracker mTracker = AnalyticsApplication.tracker();
            mTracker.send(new HitBuilders.EventBuilder()
                    .setCategory("Homework")
                    .setAction("Open Calendar")
                    .setLabel(this.getClass().getSimpleName())
                    .build());

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

    @Override
    public Intent isIntentUri(Uri uri, ActivityDispatcher activity) {
        Log.d(LOG_TAG, "isIntentUri");
        Log.d(LOG_TAG, uri.toString());

        Intent intent = new Intent(activity, HomeworkDetailActivity.class);
        Course course = new Course();
        course.setTitle("", "");

        String[] paths = uri.getEncodedPath()==null ? null : uri.getEncodedPath().split("/");
        if (paths == null)
            return null;

        try {
            if (paths[1].startsWith("course.php")) {
                String f = uri.getQueryParameter("f");
                if (f.equals("hw")) {
                    course.setId(Long.parseLong(uri.getQueryParameter("courseID")));
                    item = new Homework();
                    item.setId(Long.parseLong(uri.getQueryParameter("hw")));
                    intent.putExtra("course", course);
                    intent.putExtra("item", item);
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
