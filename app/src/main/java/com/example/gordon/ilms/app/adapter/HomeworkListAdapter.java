package com.example.gordon.ilms.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gordon.ilms.R;
import com.example.gordon.ilms.model.Homework;
import com.example.gordon.ilms.model.Material;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class HomeworkListAdapter extends ListAdapter<Homework> {
    public HomeworkListAdapter(Context context, List<Homework> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Homework homework = getItem(position);
        View view = mLayoutInflater.inflate(R.layout.list_item, null);

        DateFormat df = new SimpleDateFormat("MM/dd");
        String timeStr = df.format(homework.getDeadline());

        ((TextView) view.findViewById(R.id.popularity)).setText("");
        ((TextView) view.findViewById(R.id.title)).setText(homework.getTitle());
        ((TextView) view.findViewById(R.id.author)).setText("");
        ((TextView) view.findViewById(R.id.time)).setText(timeStr);

        return view;
    }
}
