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
import com.geniusgordon.ilms.http.detail.MaterialRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.model.Attachment;
import com.geniusgordon.ilms.model.Course;
import com.geniusgordon.ilms.model.Material;

import java.text.SimpleDateFormat;

/**
 * Created by gordon on 9/28/15.
 */
public class MaterialDetailActivity extends DetailActivity<Material> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");

        Log.d(LOG_TAG, item.getTitle());

        final String timeStr = item.getTimeStr(new SimpleDateFormat("yyyy/MM/dd"));

        iconImg.setImageResource(R.drawable.ic_description_black_36dp);
        titleTxt.setText(item.getTitle());
        authorTxt.setText(item.getAuthor());
        timeTxt.setText(timeStr);

        request = new MaterialRequest(course, item,
            new Response.Listener<Material>() {
                @Override
                public void onResponse(Material response) {
                    item = response;
                    titleTxt.setText(item.getTitle());
                    authorTxt.setText(item.getAuthor());
                    timeTxt.setText(item.getTimeStr(new SimpleDateFormat("yyyy/MM/dd")));
                    contentTxt.setText(HtmlFix.correctLinkPaths(Html.fromHtml(response.getContent())));
                    progressBar.setVisibility(View.INVISIBLE);

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
    public Intent isIntentUri(Uri uri, ActivityDispatcher activity) {
        Log.d(LOG_TAG, "isIntentUri");
        Log.d(LOG_TAG, uri.toString());

        Intent intent = new Intent(activity, MaterialDetailActivity.class);
        Course course = new Course();
        course.setTitle("", "");

        String[] paths = uri.getEncodedPath()==null ? null : uri.getEncodedPath().split("/");
        if (paths == null)
            return null;

        try {
            if (paths[1].startsWith("course.php")) {
                String f = uri.getQueryParameter("f");
                if (f.equals("doc")) {
                    course.setId(Long.parseLong(uri.getQueryParameter("courseID")));
                    item = new Material();
                    item.setId(Long.parseLong(uri.getQueryParameter("cid")));
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
