package com.example.gordon.ilms.app;

import android.os.Bundle;
import android.util.Log;

import com.example.gordon.ilms.model.Homework;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by gordon on 9/28/15.
 */
public class HomeworkDetailActivity extends DetailActivity<Homework> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");

        Log.d(LOG_TAG, item.getTitle());

        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        titleTxt.setText(item.getTitle());
        authorTxt.setText("作業");
        timeTxt.setText(df.format(item.getDeadline()));
    }


}
