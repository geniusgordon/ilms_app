package com.geniusgordon.ilms.http;

import android.util.Log;

import com.android.volley.Response;
import com.geniusgordon.ilms.model.Course;
import com.geniusgordon.ilms.model.Event;

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
 * Created by gordon on 10/19/15.
 */
public class MyScheduleRequest extends BaseRequest<List<Event>> {

    static String URL = "http://lms.nthu.edu.tw/home.php?f=calendar&date=%d-%d&mode=l";
    static String BASE_URL = "http://lms.nthu.edu.tw";
    static String LOG_TAG = "MyScheRequest";
    static String[] types = {
        "Enormal", "Ehomework", "Equiz"
    };

    int year;
    int month;

    public MyScheduleRequest(int year, int month, Response.Listener<List<Event>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, String.format(URL, year, month), listener, errorListener);
        this.year = year;
        this.month = month;
    }

    @Override
    protected List<Event> parseResponseHtml(String responseHtml) {
        List<Event> events = new ArrayList<Event>();
        Document document = Jsoup.parse(responseHtml);
        Elements tr = document.select("tr");

        for (int i = 1; i < tr.size(); i++) {
            Elements td = tr.eq(i).select("td");

            Event event = new Event();
            event.setTitle(td.eq(1).select("a").eq(0).text());

            String timeStr = year + " " + td.eq(0).text();
            DateFormat df = new SimpleDateFormat("yyyy MM-dd");
            try {
                event.setDate(df.parse(timeStr));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            for (int j = 0; j < types.length; j++) {
                if (td.eq(1).select("div").eq(0).hasClass(types[j])) {
//                    Log.d(LOG_TAG, types[j]);
                    event.setType(j);
                }
            }

            String[] hrefSplit = td.eq(2).select("a").attr("href").split("/");
            String id = hrefSplit[2];
            Course course = new Course();
            course.setTitle(td.eq(2).select("a").text());
            course.setId(Long.parseLong(id));
            event.setCourse(course);

            String eventUrl = td.eq(1).select("a").attr("href");
            if (eventUrl.startsWith("javascript"))
                event.setUrl("");
            else
                event.setUrl(BASE_URL + eventUrl);

//            Log.d(LOG_TAG, timeStr);
//            Log.d(LOG_TAG, event.getTitle());
//            Log.d(LOG_TAG, event.getCourse().getChi_title());
//            Log.d(LOG_TAG, event.getUrl());

            events.add(event);
        }

        return events;
    }
}
