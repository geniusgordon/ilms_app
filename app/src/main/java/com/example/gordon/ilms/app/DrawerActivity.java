package com.example.gordon.ilms.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.gordon.ilms.R;
import com.example.gordon.ilms.http.CourseListRequest;
import com.example.gordon.ilms.http.LoginRequest;
import com.example.gordon.ilms.http.RequestQueueSingleton;
import com.example.gordon.ilms.model.Account;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.CourseList;
import com.example.gordon.ilms.model.Preferences;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileSettingDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.util.List;

/**
 * Created by gordon on 9/26/15.
 */
public class DrawerActivity extends BaseActivity {
    protected CourseList courseList;

    protected Toolbar toolbar;
    protected AccountHeader accountHeader;
    protected Drawer drawer;
    protected Account account;

    final static int LOGIN = 1;

    protected void initDrawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bg_account)
                .addProfiles(
                        new ProfileSettingDrawerItem()
                        .withName("Log In")
                        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                                Intent intent = new Intent(DrawerActivity.this, LoginActivity.class);
                                startActivityForResult(intent, LOGIN);
                                return true;
                            }
                        })
                )
                .build();

        PrimaryDrawerItem home = new PrimaryDrawerItem()
                .withName("首頁")
                .withIcon(R.drawable.ic_home_black_24dp)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        Intent intent = new Intent(DrawerActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });

        SectionDrawerItem calendarHeader = new SectionDrawerItem()
                .withDivider(true)
                .withName("行事曆");

        SecondaryDrawerItem myCalendar = new SecondaryDrawerItem()
                .withName("個人")
                .withIcon(R.drawable.ic_event_black_24dp);

        SecondaryDrawerItem schoolCalendar = new SecondaryDrawerItem()
                .withName("校園")
                .withIcon(R.drawable.ic_event_black_24dp);

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        home
                        //calendarHeader,
                        //myCalendar,
                        //schoolCalendar
                )
                .withSelectedItem(-1)
                .build();

        checkHasAccount();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == LOGIN) {
            if (resultCode == RESULT_OK) {
                account = (Account) data.getSerializableExtra("account");
                Log.d("On Result", account.getStudentId());
                Log.d("On Result", account.getEmail());
                addAccount(account);
            }
        }
    }

    private void checkHasAccount() {
        Account account = Preferences.getInstance(getApplicationContext()).getAccount();
        if (account != null)
            addAccount(account);
    }

    private void addAccount(Account account) {
        accountHeader.removeProfile(0);
        accountHeader.addProfiles(
                new ProfileDrawerItem()
                        .withName(account.getStudentId())
                        .withEmail(account.getEmail())
        );
        getCourseList();
    }

    private void getCourseList() {
        courseList = Preferences.getInstance(getApplicationContext()).getCourseList();
        if (courseList != null) {
            updateDrawerAfterLogin();
            return;
        }

        CourseListRequest request = new CourseListRequest(
                new Response.Listener<CourseList>() {
                    @Override
                    public void onResponse(CourseList response) {
                        courseList = response;
                        Preferences.getInstance(getApplicationContext()).saveCourseList(courseList);
                        updateDrawerAfterLogin();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Toast.makeText(getApplicationContext(), "網路不穩，請稍後再試", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void updateDrawerAfterLogin() {
        SectionDrawerItem courseHeader = new SectionDrawerItem()
                .withDivider(true)
                .withName("課程  " + courseList.getSemester());
        drawer.addItem(courseHeader);

        Course c = new Course();
        c.setId(19025);
        c.setChi_title("CPE for testing");
        courseList.getCourses().add(c);
        for (final Course course: courseList.getCourses()) {
            drawer.addItem(new SecondaryDrawerItem()
                    .withIcon(R.drawable.ic_view_list_black_24dp)
                    .withName(course.getChi_title())
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                            Intent intent = new Intent(DrawerActivity.this, CourseActivity.class);
                            intent.putExtra("course", course);
                            startActivity(intent);
                            return true;
                        }
                    }));
        }
    }
}
