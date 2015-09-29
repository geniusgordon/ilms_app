package com.example.gordon.ilms.app;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.transition.Explode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.app.adapter.CourseFragmentPagerAdapter;
import com.example.gordon.ilms.http.CourseEmailRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.CourseEmail;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class CourseActivity extends DrawerActivity {
    final static String LOG_TAG = "CourseActivity";


    private Course course;

    private CourseFragmentPagerAdapter pagerAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionMenu floatingActionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            getWindow().setEnterTransition(new Explode());
            getWindow().setExitTransition(new Explode());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initDrawer();

        course = (Course) getIntent().getSerializableExtra("course");
        Log.d(LOG_TAG, course.getChi_title());

        getSupportActionBar().setTitle(course.getChi_title());

        pagerAdapter = new CourseFragmentPagerAdapter(getSupportFragmentManager(), course);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
        floatingActionMenu = (FloatingActionMenu) findViewById(R.id.send_email_btn);
        floatingActionMenu.setBackgroundColor(Color.TRANSPARENT);

        CourseEmailRequest request = new CourseEmailRequest(course,
            new Response.Listener<CourseEmail>() {
                @Override
                public void onResponse(CourseEmail response) {
                    for (final CourseEmail.Email email: response.getEmails()) {
                        FloatingActionButton btn = new FloatingActionButton(getApplicationContext());
                        btn.setImageResource(R.drawable.ic_edit_white_24dp);
                        btn.setButtonSize(FloatingActionButton.SIZE_MINI);
                        btn.setLabelText(email.getName());
                        btn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Uri uri = Uri.parse("mailto:" + email.getEmail());
                                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                                startActivity(intent);
                            }
                        });
                        floatingActionMenu.addMenuButton(btn);
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        toolbar.inflateMenu(R.menu.menu_course);
        return true;
    }

}
