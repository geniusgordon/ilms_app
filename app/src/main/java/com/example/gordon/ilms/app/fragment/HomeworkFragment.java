package com.example.gordon.ilms.app.fragment;

import android.app.FragmentManager;
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
public class HomeworkFragment extends Fragment {
    final static String LOG_TAG = "HomeworkFragment";
    private Course course;

    private ListView listView;
    private HomeworkListAdapter listAdapter;

    private ProgressBar progressBar;
    private TextView msgTxt;

    public HomeworkFragment(Course course) {
        this.course = course;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d(LOG_TAG, "on create view");
        HomeworkListRequest request = new HomeworkListRequest(course.getId(),
                new Response.Listener<List<Homework>>() {
                    @Override
                    public void onResponse(List<Homework> response) {
                        listAdapter.addItems(response);
                        progressBar.setVisibility(View.INVISIBLE);

                        if (listAdapter.getCount() == 0) {
                            msgTxt.setText("目前尚無資料");
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
        listAdapter = new HomeworkListAdapter(getContext(), new ArrayList<Homework>());
        listView = (ListView) view.findViewById(R.id.list_view);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Homework homework = (Homework) parent.getItemAtPosition(position);
                Intent intent = new Intent(HomeworkFragment.this.getActivity(), HomeworkDetailActivity.class);
                intent.putExtra("item", homework);
                intent.putExtra("course", course);
                startActivity(intent);
            }
        });

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        msgTxt = (TextView) view.findViewById(R.id.list_msg);

        return view;
    }
}
