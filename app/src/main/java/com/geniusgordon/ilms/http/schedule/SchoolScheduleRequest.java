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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

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

    public String getDateString(Calendar date) {
        return date.get(Calendar.YEAR) + " / " + (date.get(Calendar.MONTH) + 1 + " / " + date.get(Calendar.DAY_OF_MONTH));
    }

    @Override
    protected List<DateWithEvents> parseResponseHtml(String responseHtml) {
        Map<Date, List<Event>> dateListMap = new HashMap<>();

        Document document = Jsoup.parse(responseHtml);

        DateFormat dfYMD = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dfMD = new SimpleDateFormat("MM-dd");

        Calendar minCal = null;
        Calendar maxCal = null;

        Elements eventsHtml = document.select("table .event .item");
        for (int i = 0; i < eventsHtml.size(); i++) {
            Calendar cal = Calendar.getInstance();
            try {
                Date date = dfYMD.parse(eventsHtml.eq(i).attr("id").substring(1));
                cal.setTime(date);
            } catch (ParseException e) {
                continue;
            }
            Calendar startCal = Calendar.getInstance();
            Calendar endCal = Calendar.getInstance();

            String durationStr = eventsHtml.eq(i).select("span").eq(0).text();
            durationStr = durationStr.replace("[", "").replace("]", "");
            String[] duration = durationStr.split(" ~ ");
            try {
                if (duration.length == 1) {
                    Date date = dfMD.parse(duration[0]);
                    startCal.setTime(date);
                    startCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                    endCal = (Calendar) startCal.clone();
                } else {
                    if (duration[0].length() == 5) {
                        startCal.setTime(dfMD.parse(duration[0]));
                        startCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                        endCal.setTime(dfMD.parse(duration[1]));
                        endCal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
                    } else {
                        startCal.setTime(dfYMD.parse(duration[0]));
                        endCal.setTime(dfYMD.parse(duration[1]));
                    }

                }
            } catch (ParseException e) {
                continue;
            }

//            Log.d(LOG_TAG, "---");
//            Log.d(LOG_TAG, getDateString(startCal));
//            Log.d(LOG_TAG, getDateString(endCal));
//            Log.d(LOG_TAG, eventsHtml.eq(i).select("a").eq(0).text());

            if (minCal == null || startCal.before(minCal))
                minCal = (Calendar) startCal.clone();
            if (maxCal == null || endCal.after(maxCal))
                maxCal = (Calendar) endCal.clone();

            while (startCal.before(endCal) || startCal.equals(endCal)) {
                if (!dateListMap.containsKey(startCal.getTime())) {
                    dateListMap.put(startCal.getTime(), new ArrayList<Event>());
                }
                Event event = new Event();
                event.setTitle(eventsHtml.eq(i).select("a").eq(0).text());
                event.setDate(startCal.getTime());
                dateListMap.get(startCal.getTime()).add(event);
                startCal.add(Calendar.DAY_OF_MONTH, 1);
            }
        }

        List<DateWithEvents> dateWithEventsList = new ArrayList<>();
        minCal.set(Calendar.DAY_OF_MONTH, 1);
        maxCal.set(Calendar.DAY_OF_MONTH, 1);
        while (minCal.before(maxCal) || minCal.equals(maxCal)) {
            dateWithEventsList.add(new DateWithEvents(true, minCal.getTime()));
            minCal.add(Calendar.MONTH, 1);
        }

        for (Map.Entry<Date, List<Event>> entry: dateListMap.entrySet()) {
            dateWithEventsList.add(new DateWithEvents(false, entry.getKey(), entry.getValue()));
        }
        Collections.sort(dateWithEventsList);

        return dateWithEventsList;
    }

}
