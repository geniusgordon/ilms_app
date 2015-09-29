package com.example.gordon.ilms.app.fragment;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
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

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.app.AnnouncementDetailActivity;
import com.example.gordon.ilms.app.HomeworkDetailActivity;
import com.example.gordon.ilms.app.adapter.HomeworkListAdapter;
import com.example.gordon.ilms.app.adapter.HomeworkListAdapter;
import com.example.gordon.ilms.http.HomeworkListRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.Homework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class HomeworkFragment extends CoursePageFragment<Homework> {
    final static String LOG_TAG = "HomeworkFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        listAdapter = new HomeworkListAdapter(getContext(), new ArrayList<Homework>());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Homework homework = (Homework) parent.getItemAtPosition(position);
                Intent intent = new Intent(HomeworkFragment.this.getActivity(), HomeworkDetailActivity.class);
                intent.putExtra("item", homework);
                intent.putExtra("course", course);
                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation(getActivity(), view, "open_item");
                getActivity().startActivity(intent, options.toBundle());
            }
        });

        refreshList();

        return view;
    }

    @Override
    public void refreshList() {
        super.refreshList();
        HomeworkListRequest request = new HomeworkListRequest(course.getId(),
                new Response.Listener<List<Homework>>() {
                    @Override
                    public void onResponse(List<Homework> response) {
                        listAdapter.addItems(response);
                        progressBar.setVisibility(View.INVISIBLE);
                        swipeRefreshLayout.setRefreshing(false);

                        if (listAdapter.getCount() == 0) {
                            msgTxt.setText("目前尚無資料");
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
