package com.geniusgordon.ilms.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
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
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by gordon on 10/19/15.
 */
public class EventListAdapter extends ListAdapter<DateWithEvents> {
    final String LOG_TAG = "EventListAdapter";
    private Calendar today;
    private int todayInd;

    public EventListAdapter(Calendar today, Context context, List<DateWithEvents> items) {
        super(context, items);
        this.today = today;
    }

    public int getTodayInd() {
        return todayInd;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DateWithEvents event = getItem(position);

        if (event.isHeader()) {
            View view = mLayoutInflater.inflate(R.layout.list_header_item, parent, false);
            String timeStr = event.getDateStr(new SimpleDateFormat("MMM"));
            ((TextView) view.findViewById(R.id.title)).setText(timeStr);
            return view;
        }

        View view = mLayoutInflater.inflate(R.layout.event_list_item, parent, false);

        String timeStr = event.getDateStr(new SimpleDateFormat("dd"));
        ((TextView) view.findViewById(R.id.date)).setText(timeStr);

        timeStr = event.getDateStr(new SimpleDateFormat("EEE"));
        ((TextView) view.findViewById(R.id.weekday)).setText(timeStr);

        LinearLayout ll = (LinearLayout) view.findViewById(R.id.list_item);

        Locale current = context.getResources().getConfiguration().locale;
        for (final Event e: event.getEvents()) {
            View v = mLayoutInflater.inflate(R.layout.date_event, null);
            LinearLayout vbg = (LinearLayout) v.findViewById(R.id.background);
            TextView title = (TextView) v.findViewById(R.id.title);
            TextView course = (TextView) v.findViewById(R.id.course);
            title.setText(e.getTitle());

            if (e.getUrl().length() != 0) {
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(e.getUrl()));
                        v.getContext().startActivity(intent);
                    }
                });
            }

            if (e.getCourse() != null) {
                if (current.getLanguage().equals("zh")) {
                    course.setText(e.getCourse().getChi_title());
                } else {
                    course.setText(e.getCourse().getEng_title());
                }
            } else {
                course.setVisibility(View.GONE);
            }

//            int[] attrs = new int[]{R.attr.selectableItemBackground};
//            TypedArray typedArray = context.obtainStyledAttributes(attrs);
//            int backgroundResource = typedArray.getResourceId(0, 0);
//            view.setBackgroundResource(backgroundResource);
//            typedArray.recycle();

            switch (e.getType()) {
                case Event.EVENT:
                    vbg.setBackgroundColor(0xff4CAF50);
//                    vbg.setBackgroundResource(R.drawable.green_ripple);
//                    vbg.setBackgroundResource(backgroundResource);
                    break;
                case Event.HOMEWORK:
                    vbg.setBackgroundColor(0xff2196F3);
//                    vbg.setBackgroundResource(backgroundResource);
//                    vbg.setBackgroundResource(R.drawable.blue_ripple);
                    break;
                case Event.TEST:
                    vbg.setBackgroundColor(0xffFFC107);
//                    vbg.setBackgroundResource(backgroundResource);
                    break;
                default:
                    break;
            }
            ll.addView(v);
        }
        return view;
    }

    public String getDateString(Calendar date) {
        return date.get(Calendar.YEAR) + " / " + (date.get(Calendar.MONTH) + 1);
    }

    public int findTodayInd() {
        for (int i = items.size()-1; i >= 0; i--) {
            DateWithEvents e = items.get(i);
            Calendar cal = Calendar.getInstance();
            cal.setTime(e.getDate());
            if (today.equals(cal) || today.after(cal)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void addItems(int location, List<DateWithEvents> items) {
        super.addItems(location, items);
        todayInd = findTodayInd();
    }

    @Override
    public void addItems(List<DateWithEvents> items) {
        super.addItems(items);
        todayInd = findTodayInd();
    }
}
