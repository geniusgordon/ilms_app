package com.geniusgordon.ilms.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.model.Post;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by gordon on 9/29/15.
 */
public class PostListAdapter extends ListAdapter<Post> {

    public PostListAdapter(Context context, List<Post> posts) {
        super(context, posts);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Post post = getItem(position);
        View view = mLayoutInflater.inflate(R.layout.list_item, parent, false);

        DateFormat df = new SimpleDateFormat("MM-dd hh:mm");
        String time = post.getLastTime()==null ? "" : df.format(post.getLastTime());

        ((TextView) view.findViewById(R.id.popularity)).setText(String.valueOf(post.getCount()));
        ((TextView) view.findViewById(R.id.title)).setText(post.getTitle());

        String lastPost = context.getResources().getString(R.string.last_post);
        ((TextView) view.findViewById(R.id.author))
                .setText(String.format("%s: %s, %s", lastPost, post.getLastName(), time));
        ((TextView) view.findViewById(R.id.time)).setText("");

        return view;
    }
}
