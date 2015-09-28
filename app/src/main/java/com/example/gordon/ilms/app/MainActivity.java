package com.example.gordon.ilms.app;

import android.os.Bundle;

import com.example.gordon.ilms.R;

public class MainActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initDrawer();
    }


}
