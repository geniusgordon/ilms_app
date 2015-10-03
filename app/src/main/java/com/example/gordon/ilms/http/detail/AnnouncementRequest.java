package com.example.gordon.ilms.http.detail;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.http.BaseRequest;
import com.example.gordon.ilms.model.Announcement;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.Preferences;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
public class AnnouncementRequest extends BaseRequest<Announcement> {
    final static String URL = "http://lms.nthu.edu.tw/home/http_event_select.php";
    final static String ANNOUNCE_URL = "http://lms.nthu.edu.tw/course.php?courseID=%s&f=activity";
    final static String LOG_TAG = "AnnouncementRequest";

    private Announcement announcement;
    private Course course;

    public AnnouncementRequest(Course course, Announcement announcement, Response.Listener<Announcement> listener, Response.ErrorListener errorListener) {
        super(Request.Method.POST, URL, listener, errorListener);
        this.announcement = announcement;
        this.course = course;
    }

    @Override
    protected Announcement parseResponseHtml(String responseHtml) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        try {
            JSONObject news = new JSONObject(responseHtml).getJSONObject("news");
            announcement.setContent(news.getString("note"));
            try {
                String attach = news.getString("attach");
                attach = attach.equals("null") ? "" : attach;
                announcement.setAttachment(attach);
            } catch (JSONException e) {
                e.printStackTrace();
                announcement.setAttachment("");
            }
            try {
                announcement.setTime(df.parse(news.getString("createTime")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            return null;
        }

        return announcement;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(announcement.getId()));
        return params;
    }

    @Override
    public String getOpenUrl() {
        return String.format(ANNOUNCE_URL, course.getId());
    }
}
