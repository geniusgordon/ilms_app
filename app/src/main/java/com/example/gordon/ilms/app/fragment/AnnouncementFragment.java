package com.example.gordon.ilms.app.fragment;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.Pair;
import android.transition.Explode;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.app.AnnouncementDetailActivity;
import com.example.gordon.ilms.app.DetailActivity;
import com.example.gordon.ilms.app.adapter.AnnouncementListAdapter;
import com.example.gordon.ilms.app.adapter.HomeworkListAdapter;
import com.example.gordon.ilms.http.AnnouncementListRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Announcement;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.Homework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class AnnouncementFragment extends CoursePageFragment<Announcement> {
    final static String LOG_TAG = "AnnounceFragment";

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        listAdapter = new AnnouncementListAdapter(getContext(), new ArrayList<Announcement>());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Announcement announcement = (Announcement) parent.getItemAtPosition(position);
                Intent intent = new Intent(AnnouncementFragment.this.getActivity(), AnnouncementDetailActivity.class);
                intent.putExtra("item", announcement);
                intent.putExtra("course", course);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), view, "title");
                getActivity().startActivity(intent, options.toBundle());
            }
        });

        refreshList();

        return view;
    }

    public void refreshList() {
        super.refreshList();
        AnnouncementListRequest request = new AnnouncementListRequest(course.getId(),
                new Response.Listener<List<Announcement>>() {
                    @Override
                    public void onResponse(List<Announcement> response) {
                        listAdapter.addItems(response);
                        progressBar.setVisibility(View.INVISIBLE);
                        swipeRefreshLayout.setRefreshing(false);

                        if (listAdapter.getCount() == 0) {
                            msgTxt.setText("目前尚無公告");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getContext(), "網路不穩，請稍後再試", Toast.LENGTH_SHORT).show();
                        }
                        progressBar.setVisibility(View.INVISIBLE);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
        RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(request);
    }
}