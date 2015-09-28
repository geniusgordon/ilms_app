package com.example.gordon.ilms.app.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.app.adapter.AnnouncementListAdapter;
import com.example.gordon.ilms.app.adapter.MaterialListAdapter;
import com.example.gordon.ilms.http.MaterialListRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Announcement;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class MaterialFragment extends Fragment {
    final static String LOG_TAG = "MaterialFragment";
    private Course course;

    private ListView listView;
    private MaterialListAdapter listAdapter;

    public MaterialFragment(Course course) {
        this.course = course;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "on create view");
        MaterialListRequest request = new MaterialListRequest(course.getId(),
                new Response.Listener<List<Material>>() {
                    @Override
                    public void onResponse(List<Material> response) {
                        listAdapter.addItems(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(request);
        View view = inflater.inflate(R.layout.course_fragment, container, false);
        listAdapter = new MaterialListAdapter(getContext(), new ArrayList<Material>());
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(listAdapter);

        return view;
    }
}