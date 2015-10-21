package com.geniusgordon.ilms.app.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

        String timeStr = event.getDateStr(new SimpleDateFormat("dd"));
        ((TextView) view.findViewById(R.id.date)).setText(timeStr);

        timeStr = event.getDateStr(new SimpleDateFormat("EEE"));
        ((TextView) view.findViewById(R.id.weekday)).setText(timeStr);

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.list_item);

        Locale current = context.getResources().getConfiguration().locale;
        for (Event e: event.getEvents()) {
            View v = mLayoutInflater.inflate(R.layout.date_event, parent, false);
            TextView title = (TextView) v.findViewById(R.id.title);
            TextView course = (TextView) v.findViewById(R.id.course);
            title.setText(e.getTitle());

            if (current.getLanguage().equals("zh")) {
                course.setText(e.getCourse().getChi_title());
            } else {
                course.setText(e.getCourse().getEng_title());
            }

            switch (e.getType()) {
                case Event.EVENT:
                    v.setBackgroundColor(0x4CAF50);
//                    v.setBackgroundResource(R.drawable.blue_ripple);
                    break;
                case Event.HOMEWORK:
                    v.setBackgroundColor(0x2196F3);
//                    v.setBackgroundResource(R.drawable.blue_ripple);
                    break;
                case Event.TEST:
                    v.setBackgroundColor(0xFFC107);
//                    v.setBackgroundResource(R.drawable.blue_ripple);
                    break;
                default:
//                    v.setBackgroundResource(R.drawable.blue_ripple);
            }
            ll.addView(v);
        }
        return view;
    }
}
