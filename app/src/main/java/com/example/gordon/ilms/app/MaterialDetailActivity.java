package com.example.gordon.ilms.app;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.http.MaterialRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Attachment;
import com.example.gordon.ilms.model.Material;

import java.text.DateFormat;
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

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        String timeStr = item.getTime()==null ? "" : df.format(item.getTime());

        titleTxt.setText(item.getTitle());
        authorTxt.setText(item.getAuthor());
        timeTxt.setText(timeStr);

        MaterialRequest request = new MaterialRequest(course, item,
            new Response.Listener<Material>() {
                @Override
                public void onResponse(Material response) {
                    item = response;
                    contentTxt.setText(Html.fromHtml(response.getContent()));
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
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

}
