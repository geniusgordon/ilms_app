package com.example.gordon.ilms.app.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.app.AnnouncementDetailActivity;
import com.example.gordon.ilms.app.DetailActivity;
import com.example.gordon.ilms.app.adapter.AnnouncementListAdapter;
import com.example.gordon.ilms.http.AnnouncementListRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Announcement;
import com.example.gordon.ilms.model.Course;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class AnnouncementFragment extends Fragment {
    final static String LOG_TAG = "AnnounceFragment";
    private Course course;

    private ListView listView;
    private AnnouncementListAdapter listAdapter;

    private ProgressBar progressBar;
    private TextView msgTxt;

    public AnnouncementFragment(Course course) {
        this.course = course;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "on create view");
        AnnouncementListRequest request = new AnnouncementListRequest(course.getId(),
                new Response.Listener<List<Announcement>>() {
                    @Override
                    public void onResponse(List<Announcement> response) {
                        listAdapter.addItems(response);
                        progressBar.setVisibility(View.INVISIBLE);

                        if (listAdapter.getCount() == 0) {
                            msgTxt.setText("目前尚無公告");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "連線問題", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });

        RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(request);
        View view = inflater.inflate(R.layout.course_fragment, container, false);
        listAdapter = new AnnouncementListAdapter(getContext(), new ArrayList<Announcement>());
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Announcement announcement = (Announcement) parent.getItemAtPosition(position);
                Intent intent = new Intent(AnnouncementFragment.this.getActivity(), AnnouncementDetailActivity.class);
                intent.putExtra("item", announcement);
                startActivity(intent);
            }
        });

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        msgTxt = (TextView) view.findViewById(R.id.list_msg);

        return view;
    }
}