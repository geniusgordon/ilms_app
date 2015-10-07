package com.geniusgordon.ilms.app.course;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.geniusgordon.ilms.HtmlFix;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.ActivityDispatcher;
import com.geniusgordon.ilms.http.detail.AnnouncementRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.model.Announcement;
import com.geniusgordon.ilms.model.Course;

import java.text.SimpleDateFormat;

/**
 * Created by gordon on 9/28/15.
 */
public class AnnouncementDetailActivity extends DetailActivity<Announcement> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");

        Log.d(LOG_TAG, item.getTitle());

        String title = getIntent().getStringExtra("title");
        String timeStr = item.getTimeStr(new SimpleDateFormat("yyyy/MM/dd"));

        iconImg.setImageResource(R.drawable.ic_info_black_36dp);
        titleTxt.setText(item.getTitle());
        authorTxt.setText(getString(R.string.announcement));
        timeTxt.setText(timeStr);

        title = title==null ? "" : title;
        if (!title.equals(""))
            titleTxt.setText(title);

        request = new AnnouncementRequest(course, item,
                new Response.Listener<Announcement>() {
                    @Override
                    public void onResponse(Announcement response) {
                        AnnouncementDetailActivity.this.item = response;
                        contentTxt.setText(HtmlFix.correctLinkPaths(Html.fromHtml(response.getContent())));
                        contentTxt.append("\n\n");
                        contentTxt.append(HtmlFix.correctLinkPaths(Html.fromHtml(response.getAttachment())));
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }, errorListener);
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    public Intent isIntentUri(Uri uri, ActivityDispatcher activity) {
        Log.d(LOG_TAG, "isIntentUri");
        Log.d(LOG_TAG, uri.toString());

        if (!uri.getScheme().equals("ilms"))
            return null;

        try {
            String[] info = uri.getEncodedPath().split("/");
            String courseId = info[1];
            String announcementId = info[2];

            Course course = new Course();
            course.setId(Long.parseLong(courseId));

            Announcement announcement = new Announcement();
            announcement.setId(Long.parseLong(announcementId));

            String title = uri.getQueryParameter("title");
            title = title==null ? "" : title;

            Intent intent = new Intent(activity, AnnouncementDetailActivity.class);
            intent.putExtra("course", course);
            intent.putExtra("item", announcement);
            intent.putExtra("title", title);
            return intent;

        } catch (NumberFormatException e) {
            e.printStackTrace();
            return null;
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            return null;
        }
    }

}
