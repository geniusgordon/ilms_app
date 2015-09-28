package com.example.gordon.ilms.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gordon.ilms.R;
import com.example.gordon.ilms.model.Announcement;

import java.text.DateFormat;
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
        View view = mLayoutInflater.inflate(R.layout.list_item, null);

        DateFormat df = new SimpleDateFormat("MM/dd");
        String timeStr = df.format(announcement.getTime());

        ((TextView) view.findViewById(R.id.popularity)).setText(String.valueOf(announcement.getPopularity()));
        ((TextView) view.findViewById(R.id.title)).setText(announcement.getTitle());
        ((TextView) view.findViewById(R.id.author)).setText("");
        ((TextView) view.findViewById(R.id.time)).setText(timeStr);

        return view;
    }
}
