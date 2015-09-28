package com.example.gordon.ilms.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.gordon.ilms.model.Announcement;

import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public abstract class ListAdapter<T> extends BaseAdapter {
    LayoutInflater mLayoutInflater;
    List<T> items;

    public ListAdapter(Context context, List<T> items) {
        mLayoutInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.indexOf(getItem(position));
    }

    public void addItems(List<T> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
