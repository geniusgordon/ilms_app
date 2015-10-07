package com.geniusgordon.ilms.app.fragment;

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

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.adapter.ListAdapter;
import com.geniusgordon.ilms.model.Course;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gordon on 9/26/15.
 */
public class CoursePageFragment<T> extends Fragment implements Response.ErrorListener {
    final static String LOG_TAG = "CoursePageFragment";
    protected Course course;

    protected ListAdapter<T> listAdapter;
    protected View viewHolder;

    @Bind(R.id.list_view) ListView listView;
    @Bind(R.id.progressBar) ProgressBar progressBar;
    @Bind(R.id.list_msg) TextView msgTxt;

    protected SwipeRefreshLayout swipeRefreshLayout;

    public CoursePageFragment() {
        super();
        viewHolder = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (viewHolder != null)
            return viewHolder;

        View view = inflater.inflate(R.layout.course_fragment, container, false);
        ButterKnife.bind(this, view);

        this.course = (Course) getArguments().getSerializable("course");
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.d(LOG_TAG, "onRefresh");
                refreshList();
            }
        });

        viewHolder = view;

        return view;
    }

    public void refreshList() {
        msgTxt.setText("");
        listAdapter.clearItems();
    }

    public void setMsg(int t) {
        String msg = getString(t);
        msgTxt.setText(msg);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
            setMsg(R.string.timeout);
        } else {
            setMsg(R.string.no_permission);
        }
        progressBar.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }
}
