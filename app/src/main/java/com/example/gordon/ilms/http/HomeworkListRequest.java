package com.example.gordon.ilms.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Homework;
import com.example.gordon.ilms.model.Material;
import com.example.gordon.ilms.model.Preferences;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gordon on 9/28/15.
 */

public class HomeworkListRequest extends BaseRequest<List<Homework>> {
    final static String URL = "http://lms.nthu.edu.tw/course.php?courseID=%s&f=hwlist";
    final static String LOG_TAG = "HomeworkListRequest";

    public HomeworkListRequest(Long courseId, Response.Listener<List<Homework>> listener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, String.format(URL, courseId), listener, errorListener);
    }

    @Override
    protected Response<List<Homework>> parseNetworkResponse(NetworkResponse response) {
        List<Homework> homeworks = new ArrayList<Homework>();
        String responseHtml = new String(response.data);
        Document document = Jsoup.parse(responseHtml);

        Elements tr = document.select("tr");
        for (int i = 1; i < tr.size(); i++) {
            Elements td = tr.eq(i).select("td");

            String[] tmp = td.eq(1).select("a").eq(0).attr("href").split("=");
            Log.d(LOG_TAG, tmp[tmp.length-1]);
            Log.d(LOG_TAG, td.eq(1).select("a").eq(0).html());
            Log.d(LOG_TAG, td.eq(4).select("span").attr("title"));

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Homework homework = new Homework();
            homework.setTitle(td.eq(1).select("a").eq(0).html());
            homework.setId(Long.parseLong(tmp[tmp.length-1]));
            try {
                homework.setDeadline(df.parse(td.eq(4).select("span").attr("title")));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            homeworks.add(homework);
        }

        return Response.success(homeworks, HttpHeaderParser.parseCacheHeaders(response));
    }

}
