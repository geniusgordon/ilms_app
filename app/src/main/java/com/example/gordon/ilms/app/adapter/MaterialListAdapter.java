package com.example.gordon.ilms.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gordon.ilms.R;
import com.example.gordon.ilms.model.Announcement;
import com.example.gordon.ilms.model.Material;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class MaterialListAdapter extends ListAdapter<Material> {
    public MaterialListAdapter(Context context, List<Material> items) {
        super(context, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Material material = getItem(position);
        View view = mLayoutInflater.inflate(R.layout.list_item, null);

        DateFormat df = new SimpleDateFormat("MM/dd");
        String timeStr = material.getTime() == null ? "" : df.format(material.getTime());

        ((TextView) view.findViewById(R.id.popularity)).setText(String.valueOf(material.getPopularity()));
        ((TextView) view.findViewById(R.id.title)).setText(material.getTitle());
        ((TextView) view.findViewById(R.id.author)).setText(material.getAuthor());
        ((TextView) view.findViewById(R.id.time)).setText(timeStr);

        return view;
    }
}
