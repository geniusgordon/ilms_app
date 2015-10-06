package com.geniusgordon.ilms.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.android.volley.Response;
import com.geniusgordon.ilms.app.course.AnnouncementDetailActivity;
import com.geniusgordon.ilms.app.adapter.AnnouncementListAdapter;
import com.geniusgordon.ilms.http.course.AnnouncementListRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.model.Announcement;

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
        final View fragmentView = super.onCreateView(inflater, container, savedInstanceState);

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
                        makeSceneTransitionAnimation(getActivity(), fragmentView, "open_item");
                getActivity().startActivity(intent, options.toBundle());
            }
        });

        refreshList();

        return fragmentView;
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
                }, this);
        RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(request);
    }
}