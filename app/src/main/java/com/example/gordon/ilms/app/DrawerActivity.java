package com.example.gordon.ilms.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Response;
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
public class DrawerActivity extends AppCompatActivity {
    protected String semester;
    protected List<Course> courses;

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

        SectionDrawerItem courseHeader = new SectionDrawerItem()
                .withDivider(true)
                .withName("課程");

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
                        home,
                        calendarHeader,
                        myCalendar,
                        schoolCalendar,
                        courseHeader)
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
        CourseListRequest request = new CourseListRequest(
                new Response.Listener<CourseList>() {
                    @Override
                    public void onResponse(CourseList response) {
                        for (Course course: response.getCourses()) {
                            drawer.addItem(new SecondaryDrawerItem()
                            .withIcon(R.drawable.ic_view_list_black_24dp)
                            .withName(course.getChi_title()));
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }
}
