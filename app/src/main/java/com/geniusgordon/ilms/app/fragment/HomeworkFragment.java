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
import com.geniusgordon.ilms.app.course.HomeworkDetailActivity;
import com.geniusgordon.ilms.app.adapter.HomeworkListAdapter;
import com.geniusgordon.ilms.http.course.HomeworkListRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.model.Homework;

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
                        makeSceneTransitionAnimation(getActivity(), view, "");
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
                }, this);

        RequestQueueSingleton.getInstance(getContext()).addToRequestQueue(request);
    }
}
