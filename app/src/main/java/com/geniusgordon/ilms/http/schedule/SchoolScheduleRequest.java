package com.geniusgordon.ilms.http.schedule;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.geniusgordon.ilms.http.BaseRequest;
import com.geniusgordon.ilms.model.DateWithEvents;
import com.geniusgordon.ilms.model.Event;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gordon on 10/22/15.
 */
public class SchoolScheduleRequest extends BaseRequest<List<DateWithEvents>> {

    static String LOG_TAG = "SchoolScheRequest";
    static String URL = "http://lms.nthu.edu.tw/course/index.php?nav=calendar";
    static String BASE_URL = "http://lms.nthu.edu.tw";

    public SchoolScheduleRequest(Response.Listener<List<DateWithEvents>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, URL, listener, errorListener);
    }

    @Override
    protected List<DateWithEvents> parseResponseHtml(String responseHtml) {
        Document document = Jsoup.parse(responseHtml);

        Elements tr = document.select("tr");
        for (int i = 2; i < tr.size(); i++) {
            Elements td = tr.eq(i).select(".event");
            Log.d(LOG_TAG, td.eq(0).text());
        }

        return new ArrayList<DateWithEvents>();
    }

}
