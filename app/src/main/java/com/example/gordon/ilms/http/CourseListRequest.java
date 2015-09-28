package com.example.gordon.ilms.http;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.CourseList;
import com.example.gordon.ilms.model.LoginStatus;
import com.example.gordon.ilms.model.Preferences;

import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gordon on 9/28/15.
 */
public class CourseListRequest extends Request<CourseList> {
    final static String HOME_URL = "http://lms.nthu.edu.tw/home.php";
    final static String LOG_TAG = "CourseListRequest";

    private Response.Listener mListener;

    public CourseListRequest(Response.Listener<CourseList> listener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, HOME_URL, errorListener);
        this.mListener = listener;
    }

    @Override
    protected void onFinish() {
        super.onFinish();
        mListener = null;
    }

    @Override
    protected void deliverResponse(CourseList response) {
        if (mListener != null) {
            mListener.onResponse(response);
        }
    }

    @Override
    protected Response<CourseList> parseNetworkResponse(NetworkResponse response) {
        CourseList courseList = new CourseList();
        List<Course> courses = new ArrayList<Course>();
        String responseHtml = new String(response.data);
        Document document = Jsoup.parse(responseHtml);
        String semester = document.select("#left > div:nth-child(1) > div.mnuBody > div.hint").html();
        Log.d(LOG_TAG, "semester: " + semester);
        Elements elements = document.select(".mnuItem a");
        for (Element element: elements) {
        //    Course course = new Course();
        //    course.setId();
            if (element.attr("href").split("/").length == 3) {
                String id = element.attr("href").split("/")[2];
                Course course = new Course();
                course.setTitle(element.html());
                course.setId(Long.parseLong(id));
                courses.add(course);
                Log.d(LOG_TAG, course.getChi_title());
                Log.d(LOG_TAG, course.getEng_title());
                Log.d(LOG_TAG, String.valueOf(course.getId()));
            }
        }
        courseList.setSemester(semester);
        courseList.setCourses(courses);
        return Response.success(courseList, HttpHeaderParser.parseCacheHeaders(response));
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
