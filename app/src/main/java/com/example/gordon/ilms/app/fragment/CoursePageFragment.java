package com.example.gordon.ilms.app.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.gordon.ilms.R;
import com.example.gordon.ilms.app.adapter.AnnouncementListAdapter;
import com.example.gordon.ilms.app.adapter.HomeworkListAdapter;
import com.example.gordon.ilms.app.adapter.ListAdapter;
import com.example.gordon.ilms.model.Announcement;
import com.example.gordon.ilms.model.Course;

import java.util.ArrayList;

/**
 * Created by gordon on 9/26/15.
 */
public abstract class CoursePageFragment<T> extends Fragment {
    final static String LOG_TAG = "CoursePageFragment";
    protected Course course;

    protected ListView listView;
    protected ListAdapter<T> listAdapter;

    protected ProgressBar progressBar;
    protected TextView msgTxt;

    protected SwipeRefreshLayout swipeRefreshLayout;

    public CoursePageFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.course_fragment, container, false);
        this.course = (Course) getArguments().getSerializable("course");
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        msgTxt = (TextView) view.findViewById(R.id.list_msg);
        listView = (ListView) view.findViewById(R.id.list_view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(LOG_TAG, "onRefresh");
                refreshList();
            }
        });

        return view;
    }

    public void refreshList() {
        msgTxt.setText("");
        listAdapter.clearItems();
    }
}
