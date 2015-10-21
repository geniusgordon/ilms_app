package com.geniusgordon.ilms.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.model.Course;
import com.geniusgordon.ilms.model.DateWithEvents;
import com.geniusgordon.ilms.model.Event;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by gordon on 10/19/15.
 */
public class EventListAdapter extends ListAdapter<DateWithEvents> {
    final String LOG_TAG = "EventListAdapter";

    public EventListAdapter(Context context, List<DateWithEvents> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DateWithEvents event = getItem(position);
        View view = mLayoutInflater.inflate(R.layout.event_list_item, parent, false);

//        ((TextView) view.findViewById(R.id.title)).setText(event.getTitle());
        Locale current = context.getResources().getConfiguration().locale;
        if (current.getLanguage().equals("zh")) {
//            ((TextView) view.findViewById(R.id.author)).setText(event.getCourse().getChi_title());
        } else {
//            ((TextView) view.findViewById(R.id.author)).setText(event.getCourse().getEng_title());
        }
        String timeStr = event.getDateStr(new SimpleDateFormat("dd"));
        ((TextView) view.findViewById(R.id.date)).setText(timeStr);

        timeStr = event.getDateStr(new SimpleDateFormat("EEE"));
        ((TextView) view.findViewById(R.id.weekday)).setText(timeStr);

        return view;
    }
}
