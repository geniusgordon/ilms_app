package com.geniusgordon.ilms.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.model.Course;
import com.geniusgordon.ilms.model.HomeItem;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by gordon on 9/30/15.
 */
public class HomeItemListAdapter extends ListAdapter<HomeItem> {

    private boolean showHeader;

    public HomeItemListAdapter(Context context, List<HomeItem> items) {
        super(context, items);
        showHeader = true;
    }

    public void setShowHeader(boolean showHeader) {
        this.showHeader = showHeader;
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
            Locale current = context.getResources().getConfiguration().locale;
            if (current.getLanguage().equals("zh")) {
                ((TextView) view.findViewById(R.id.author)).setText(Course.splitTitle(item.getCourseName())[0]);
            } else {
                ((TextView) view.findViewById(R.id.author)).setText(Course.splitTitle(item.getCourseName())[1]);
            }
            DateFormat df = new SimpleDateFormat("MM/dd");
            String timeStr = item.getDate()==null ? "" :df.format(item.getDate());
            ((TextView) view.findViewById(R.id.time)).setText(timeStr);

            ImageView img = (ImageView) view.findViewById(R.id.icon);
            switch (item.getType()) {
                case HomeItem.FORUM:
                    img.setImageResource(R.drawable.ic_group_black_36dp);
                    break;
                case HomeItem.MATERIAL:
                    img.setImageResource(R.drawable.ic_description_black_36dp);
                    break;
                case HomeItem.ANNOUNCE:
                    img.setImageResource(R.drawable.ic_info_black_36dp);
                    break;
            }
        }
        if (item.isHeader() && !showHeader)
            ((TextView) view.findViewById(R.id.title)).setText("");
        else
            ((TextView) view.findViewById(R.id.title)).setText(item.getTitle());
        return view;
    }
}
