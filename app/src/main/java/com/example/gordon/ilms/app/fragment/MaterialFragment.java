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

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.app.MaterialDetailActivity;
import com.example.gordon.ilms.app.adapter.AnnouncementListAdapter;
import com.example.gordon.ilms.app.adapter.MaterialListAdapter;
import com.example.gordon.ilms.http.MaterialListRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Announcement;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.Material;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class MaterialFragment extends CoursePageFragment<Material> {
    final static String LOG_TAG = "MaterialFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        listAdapter = new MaterialListAdapter(getContext(), new ArrayList<Material>());
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Material material = (Material) parent.getItemAtPosition(position);
                Intent intent = new Intent(MaterialFragment.this.getActivity(), MaterialDetailActivity.class);
                intent.putExtra("item", material);
                intent.putExtra("course", course);
                startActivity(intent);
            }
        });

        refreshList();

        return view;
    }

    public void refreshList() {
        super.refreshList();
        MaterialListRequest request = new MaterialListRequest(course.getId(),
                new Response.Listener<List<Material>>() {
                    @Override
                    public void onResponse(List<Material> response) {
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
                    }
                });
        RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(request);
    }
}