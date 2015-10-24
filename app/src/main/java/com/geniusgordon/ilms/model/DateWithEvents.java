package com.geniusgordon.ilms.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by gordon on 10/20/15.
 */
public class DateWithEvents implements Comparable {
    boolean isHeader;
    Date date;
    List<Event> events;

    public DateWithEvents(boolean isHeader, Date date) {
        this(isHeader, date, new ArrayList<Event>());
    }

    public DateWithEvents(boolean isHeader, Date date, List<Event> events) {
        this.isHeader = isHeader;
        this.date = date;
        this.events = events;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public Date getDate() {
        return date;
    }

    public String getDateStr(DateFormat df) {
        return (date == null) ? "" : df.format(date);
    }

    public List<Event> getEvents() {
        return events;
    }

    public static List<DateWithEvents> fromEventList(List<Event> orig_events) {
        List<DateWithEvents> new_events = new ArrayList<DateWithEvents>();

        DateWithEvents last = null;
        for (Event e: orig_events) {
            if (last == null || !e.getDate().equals(last.date)) {
                if (last != null)
                    new_events.add(last);
                last = new DateWithEvents(false, e.getDate());
            }
            last.addEvent(e);
        }
        if (last != null)
            new_events.add(last);

        return new_events;
    }

    @Override
    public int compareTo(Object another) {
        DateWithEvents e = (DateWithEvents) another;
        int ret = date.compareTo(e.getDate());
        if (ret == 0) {
            if (isHeader && !e.isHeader)
                return -1;
            if (!isHeader && e.isHeader)
                return 1;
        }
        return ret;
    }
}
