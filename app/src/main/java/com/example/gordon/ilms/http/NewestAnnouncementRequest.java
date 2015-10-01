package com.example.gordon.ilms.http;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Announcement;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.HomeItem;
import com.example.gordon.ilms.model.Preferences;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gordon on 10/1/15.
 */

public class NewestAnnouncementRequest extends HomeItemListRequest {

    public NewestAnnouncementRequest(Response.Listener<List<HomeItem>> listener, Response.ErrorListener errorListener) {
        super(listener, errorListener);
        String[] cssSelect = {
                "#right > div:nth-child(4) > div:nth-child(2) > div.BlockR",
        };
        int[] types = {
                HomeItem.ANNOUNCE,
        };
        this.cssSelect = cssSelect;
        this.types = types;
    }

}

