package com.example.gordon.ilms.app;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.http.HomeworkRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Attachment;
import com.example.gordon.ilms.model.Homework;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by gordon on 9/28/15.
 */
public class HomeworkDetailActivity extends DetailActivity<Homework> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");

        Log.d(LOG_TAG, item.getTitle());

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        titleTxt.setText(item.getTitle());
        authorTxt.setText("作業");
        timeTxt.setText(df.format(item.getDeadline()));

        HomeworkRequest request = new HomeworkRequest(course, item,
            new Response.Listener<Homework>() {
                @Override
                public void onResponse(Homework response) {
                    item = response;
                    contentTxt.setText(Html.fromHtml(response.getDescription()));
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

                }
            });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }


}
