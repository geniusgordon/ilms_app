package com.geniusgordon.ilms.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by gordon on 10/20/15.
 */
public class DateWithEvents {
    Date date;
    List<Event> events;

    public DateWithEvents(Date date) {
        this.date = date;
        events = new ArrayList<Event>();
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
                last = new DateWithEvents(e.getDate());
            }
            last.addEvent(e);
        }

        return new_events;
    }
}
