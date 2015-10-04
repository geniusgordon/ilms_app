package com.geniusgordon.ilms.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.model.Material;

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
        View view = mLayoutInflater.inflate(R.layout.list_item, parent, false);

        String timeStr = material.getTimeStr(new SimpleDateFormat("MM/dd"));

        ((TextView) view.findViewById(R.id.popularity)).setText(String.valueOf(material.getPopularity()));
        ((TextView) view.findViewById(R.id.title)).setText(material.getTitle());
        ((TextView) view.findViewById(R.id.author)).setText(material.getAuthor());
        ((TextView) view.findViewById(R.id.time)).setText(timeStr);

        return view;
    }
}
