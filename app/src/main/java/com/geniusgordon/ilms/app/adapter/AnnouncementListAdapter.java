package com.geniusgordon.ilms.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.model.Announcement;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class AnnouncementListAdapter extends ListAdapter<Announcement> {

    public AnnouncementListAdapter(Context context, List<Announcement> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Announcement announcement = getItem(position);
        View view = mLayoutInflater.inflate(R.layout.list_item, parent, false);

        String timeStr = announcement.getTimeStr(new SimpleDateFormat("MM/dd"));

        ((TextView) view.findViewById(R.id.popularity)).setText(String.valueOf(announcement.getPopularity()));
        ((TextView) view.findViewById(R.id.title)).setText(announcement.getTitle());
        ((TextView) view.findViewById(R.id.author)).setText("");
        ((TextView) view.findViewById(R.id.time)).setText(timeStr);

        return view;
    }
}
