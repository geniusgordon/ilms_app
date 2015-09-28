package com.example.gordon.ilms.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.CourseList;
import com.example.gordon.ilms.model.Material;
import com.example.gordon.ilms.model.Preferences;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gordon on 9/28/15.
 */
public class MaterialListRequest extends Request<List<Material>> {
    final static String URL = "http://lms.nthu.edu.tw/course.php?courseID=%s&f=doclist";
    final static String LOG_TAG = "MaterialListRequest";

    private Response.Listener mListener;

    public MaterialListRequest(Long courseId, Response.Listener<List<Material>> listener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, String.format(URL, courseId), errorListener);
        this.mListener = listener;
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        mListener = null;
    }

    @Override
    protected void deliverResponse(List<Material> response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected Response<List<Material>> parseNetworkResponse(NetworkResponse response) {
        List<Material> materials = new ArrayList<Material>();
        String responseHtml = new String(response.data);
        Document document = Jsoup.parse(responseHtml);
        Elements tr = document.select("tr");

        for (int i = 1; i < tr.size(); i++) {
            Elements td = tr.eq(i).select("td");

            if (tr.size() == 2 && td.size() == 1)
                return Response.success(materials, HttpHeaderParser.parseCacheHeaders(response));
/*
            Log.d(LOG_TAG, td.eq(0).html());
            Log.d(LOG_TAG, td.eq(1).select("a").eq(0).html());
            Log.d(LOG_TAG, td.eq(2).select("div").html());
            Log.d(LOG_TAG, td.eq(3).html());
            Log.d(LOG_TAG, td.eq(5).select("span").attr("title"));
*/
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Material material = new Material();
            material.setId(Long.parseLong(td.eq(0).html()));
            material.setTitle(td.eq(1).select("a").eq(0).html());
            material.setAuthor(td.eq(2).select("div").html());
            material.setPopularity(Long.parseLong(td.eq(3).html()));
            try {
                material.setTime(df.parse(td.eq(5).select("span").attr("title")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            materials.add(material);
        }

        return Response.success(materials, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = new HashMap<String, String>();
        Preferences prefs = Preferences.getInstance();
        if (prefs != null) {
            String cookie = prefs.getCookie();
            if (!cookie.equals(""))
                headers.put("cookie", cookie);
        }
        return headers;
    }
}
