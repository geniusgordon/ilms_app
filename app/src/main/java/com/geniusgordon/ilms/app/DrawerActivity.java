package com.geniusgordon.ilms.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.geniusgordon.ilms.R;
import com.geniusgordon.ilms.app.course.CourseActivity;
import com.geniusgordon.ilms.app.main.MainActivity;
import com.geniusgordon.ilms.app.main.NewestForumActivity;
import com.geniusgordon.ilms.app.main.NewestMaterialActivity;
import com.geniusgordon.ilms.http.main.CourseListRequest;
import com.geniusgordon.ilms.http.RequestQueueSingleton;
import com.geniusgordon.ilms.model.Account;
import com.geniusgordon.ilms.model.Course;
import com.geniusgordon.ilms.model.CourseList;
import com.geniusgordon.ilms.model.Preferences;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
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
import com.mikepenz.materialdrawer.util.DrawerImageLoader;

import java.util.Locale;

import it.sephiroth.android.library.picasso.Picasso;
import it.sephiroth.android.library.picasso.PicassoTools;

/**
 * Created by gordon on 9/26/15.
 */
public class DrawerActivity extends BaseActivity {
    protected CourseList courseList;

    protected Toolbar toolbar;
    protected AccountHeader accountHeader;
    protected Drawer drawer;
    protected Account account;
    protected PrimaryDrawerItem newestAnnouncement;
    protected PrimaryDrawerItem newestForum;
    protected PrimaryDrawerItem newestMaterial;
    protected PrimaryDrawerItem mySchedule;

    public final static int LOGIN_REQUEST = 1;

    protected void initDrawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        account = Preferences.getInstance(getApplicationContext()).getAccount();

        initImageLoader();

        accountHeader = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.bg_account)
                .addProfiles(
                        new ProfileDrawerItem()
                                .withName(getString(R.string.login))
                                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                                    @Override
                                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                                        Intent intent = new Intent(DrawerActivity.this, LoginActivity.class);
                                        startActivity(intent);
//                                        startActivityForResult(intent, LOGIN_REQUEST);
                                        return true;
                                    }
                                })
                )
                .build();

        newestAnnouncement = new PrimaryDrawerItem()
                .withName(getString(R.string.latest_announcement))
                .withIcon(R.drawable.ic_info_yellow_24dp)
                .withIconColor(getResources().getColor(R.color.yellow_primary))
                .withSelectedTextColor(getResources().getColor(R.color.yellow_primary))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        Intent intent = new Intent(DrawerActivity.this, MainActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });

        newestForum = new PrimaryDrawerItem()
                .withName(getString(R.string.latest_discussion))
                .withIcon(R.drawable.ic_group_green_36dp)
                .withIconColor(getResources().getColor(R.color.green_primary))
                .withSelectedTextColor(getResources().getColor(R.color.green_primary))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        Intent intent = new Intent(DrawerActivity.this, NewestForumActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });

        newestMaterial = new PrimaryDrawerItem()
                .withName(getString(R.string.latest_document))
                .withIcon(R.drawable.ic_description_blue_36dp)
                .withIconColor(getResources().getColor(R.color.blue_primary))
                .withSelectedTextColor(getResources().getColor(R.color.blue_primary))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        Intent intent = new Intent(DrawerActivity.this, NewestMaterialActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });


        mySchedule = new PrimaryDrawerItem()
                .withIcon(R.drawable.ic_event_black_24dp)
                .withName(getString(R.string.my_schedule))
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        Intent intent = new Intent(DrawerActivity.this, MyScheduleActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });

        drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(accountHeader)
                .addDrawerItems(
                        newestAnnouncement,
                        newestForum,
                        newestMaterial,
                        mySchedule
                )
                .withSelectedItem(-1)
                .build();

        checkHasAccount();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == LOGIN_REQUEST) {
//            if (resultCode == RESULT_OK) {
//                account = (Account) data.getSerializableExtra("account");
//                Log.d("On Result", account.getStudentId());
//                Log.d("On Result", account.getEmail());
//                Intent intent = new Intent(DrawerActivity.this, MainActivity.class);
//                addAccount(account);
//            }
//        }
    }

    private void checkHasAccount() {
        Account account = Preferences.getInstance(getApplicationContext()).getAccount();
        if (account != null)
            addAccount(account);
    }

    private void addAccount(Account account) {
        accountHeader.removeProfile(0);

        ProfileDrawerItem item = new ProfileDrawerItem()
                    .withName(account.getStudentId())
                    .withIcon(account.getAvatarUrl())
                    .withEmail(account.getEmail());

        if (account.getAvatarUrl() != null)
            item.withIcon(account.getAvatarUrl());

        item.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                openProfile();
                return true;
            }
        });

        accountHeader.addProfiles(item,
                new ProfileSettingDrawerItem()
                        .withName(getString(R.string.logout))
                        .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                                Preferences.getInstance(getApplicationContext()).logout();
                                finishAffinity();
                                Intent intent = new Intent(DrawerActivity.this, MainActivity.class);
                                startActivity(intent);
                                return true;
                            }
                        })
        );
        getCourseList();
    }

    public void initImageLoader() {
        DrawerImageLoader.init(new DrawerImageLoader.IDrawerImageLoader() {
            @Override
            public void set(ImageView imageView, Uri uri, Drawable placeholder) {
                Log.d("ImageLoader set", uri.toString());
                PicassoTools.clearCache(Picasso.with(getApplicationContext()));
                Picasso.with(imageView.getContext()).load(uri).placeholder(placeholder).into(imageView);
            }

            @Override
            public void cancel(ImageView imageView) {
                Picasso.with(imageView.getContext()).cancelRequest(imageView);
            }

            @Override
            public Drawable placeholder(Context ctx) {
                return null;
            }

            @Override
            public Drawable placeholder(Context context, String s) {
                return null;
            }
        });
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
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(getApplicationContext(), getString(R.string.timeout),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void updateDrawerAfterLogin() {
        SectionDrawerItem courseHeader = new SectionDrawerItem()
                .withDivider(true)
                .withName(getString(R.string.course) + "  " + courseList.getSemester());
        drawer.addItem(courseHeader);

        /*Course c = new Course();
        c.setId(23091);
        c.setChi_title("testing");
        courseList.getCourses().add(c);*/
        Locale current = getResources().getConfiguration().locale;

        for (final Course course: courseList.getCourses()) {
            SecondaryDrawerItem item = new SecondaryDrawerItem()
                    .withIcon(R.drawable.ic_view_list_black_24dp)
                    .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                        @Override
                        public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                            Intent intent = new Intent(DrawerActivity.this, CourseActivity.class);
                            intent.putExtra("course", course);
                            startActivity(intent);
                            return true;
                        }
                    });
            if (current.getLanguage().equals("zh"))
                item.withName(course.getChi_title());
            else
                item.withName(course.getEng_title());
            drawer.addItem(item);
        }
    }

    private void openProfile() {
        Tracker mTracker = AnalyticsApplication.tracker();
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory("Drawer")
                .setAction("Open Profile")
                .setLabel(this.getClass().getSimpleName())
                .build());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.profile_card, null);
        ((TextView) view.findViewById(R.id.name)).setText(account.getName());
        ((TextView) view.findViewById(R.id.stdudentId)).setText(account.getStudentId());
        ((TextView) view.findViewById(R.id.email)).setText(account.getEmail());
        ((TextView) view.findViewById(R.id.lastLogin)).setText(account.getLastLogin());
        ((TextView) view.findViewById(R.id.loginCount)).setText(account.getLoginCount());
        ImageView imageView = (ImageView) view.findViewById(R.id.profile_image);
        Picasso.with(imageView.getContext()).load(Uri.parse(account.getAvatarUrl())).into(imageView);
        builder.setView(view);
        builder.show();
    }
}
