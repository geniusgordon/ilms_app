package com.geniusgordon.ilms.http.course;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.geniusgordon.ilms.http.BaseRequest;
import com.geniusgordon.ilms.model.Announcement;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class AnnouncementListRequest extends BaseRequest<List<Announcement>> {
    final static String URL = "http://lms.nthu.edu.tw/course.php?courseID=%d&f=news";
    final static String LOG_TAG = "AncmtListRequest";

    public AnnouncementListRequest(Long courseId, Response.Listener<List<Announcement>> listener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, String.format(URL, courseId), listener, errorListener);
    }

    @Override
    protected List<Announcement> parseResponseHtml(String responseHtml) {
        List<Announcement> announcements = new ArrayList<Announcement>();
        Document document = Jsoup.parse(responseHtml);

        if (document.select("#main").size() == 0)
            return null;

        Elements tr = document.select("tr");
        for (int i = 1; i < tr.size(); i++) {
            if (i%2 == 0)
                continue;

            Elements td = tr.eq(i).select("td");

            if (tr.size() == 2 && td.size() == 1)
                return announcements;

            Announcement announcement = new Announcement();
            announcement.setId(Long.parseLong(td.eq(0).text()));
            announcement.setTitle(td.eq(1).select("a").eq(0).text().trim());
            announcement.setPopularity(Long.parseLong(td.eq(2).text()));

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                announcement.setTime(df.parse(td.eq(3).select("span").attr("title")));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Log.d(LOG_TAG, String.valueOf(announcement.getId()));
            Log.d(LOG_TAG, announcement.getTitle());
            Log.d(LOG_TAG, String.valueOf(announcement.getPopularity()));

            announcements.add(announcement);
        }

        return announcements;
    }
}
