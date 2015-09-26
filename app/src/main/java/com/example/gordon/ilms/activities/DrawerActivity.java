package com.example.gordon.ilms.activities;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.gordon.ilms.R;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;

/**
 * Created by gordon on 9/26/15.
 */
public class DrawerActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected AccountHeader accountHeader;
    protected Drawer drawer;

    protected void initDrawer() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);


    }
}
