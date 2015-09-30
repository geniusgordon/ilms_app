package com.example.gordon.ilms.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gordon.ilms.R;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.HomeItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by gordon on 9/30/15.
 */
public class HomeItemListAdapter extends ListAdapter<HomeItem> {

    public HomeItemListAdapter(Context context, List<HomeItem> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomeItem item = getItem(position);
        View view = null;
        if (item.isHeader()) {
            view = mLayoutInflater.inflate(R.layout.list_header_item, parent, false);
            view.setClickable(false);
        } else {
            view = mLayoutInflater.inflate(R.layout.list_item, parent, false);
            ((TextView) view.findViewById(R.id.author)).setText(Course.splitTitle(item.getCourseName())[0]);

            DateFormat df = new SimpleDateFormat("MM/dd");
            String timeStr = item.getDate()==null ? "" :df.format(item.getDate());
            ((TextView) view.findViewById(R.id.time)).setText(timeStr);
        }
        ((TextView) view.findViewById(R.id.title)).setText(item.getTitle());
        return view;
    }
}
