package com.example.gordon.ilms.http.course;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.http.BaseRequest;
import com.example.gordon.ilms.model.Announcement;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.CourseList;
import com.example.gordon.ilms.model.Preferences;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gordon on 9/28/15.
 */
public class AnnouncementListRequest extends BaseRequest<List<Announcement>> {
    final static String URL = "http://lms.nthu.edu.tw/course.php?courseID=%d&f=activity";
    final static String LOG_TAG = "AncmtListRequest";

    public AnnouncementListRequest(Long courseId, Response.Listener<List<Announcement>> listener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, String.format(URL, courseId), listener, errorListener);
    }

    @Override
    protected List<Announcement> parseResponseHtml(String responseHtml) {
        List<Announcement> announcements = new ArrayList<Announcement>();
        Document document = Jsoup.parse(responseHtml);
        Elements elements = document.select(".BlockItem");

        if (document.select("#main").size() == 0)
            return null;

        for (Element element: elements) {
            String idStr = element.select("a").attr("href").split("\\(")[1].split(",")[0];
            String timeStr = element.select("span").attr("title");
            String popularityStr = element.select("div:nth-child(2)").last().html();

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Announcement announcement = new Announcement();
            announcement.setTitle(element.select("a").html());
            announcement.setId(Long.parseLong(idStr));
            announcement.setPopularity(Long.parseLong(popularityStr));
            try {
                announcement.setTime(df.parse(timeStr));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            announcements.add(announcement);

            Log.d(LOG_TAG, announcement.getTitle());
            Log.d(LOG_TAG, idStr);
            Log.d(LOG_TAG, timeStr);
            Log.d(LOG_TAG, popularityStr);
        }
        return announcements;
    }
}
