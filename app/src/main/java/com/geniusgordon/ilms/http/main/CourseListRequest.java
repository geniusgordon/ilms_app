package com.geniusgordon.ilms.http.main;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.geniusgordon.ilms.http.BaseRequest;
import com.geniusgordon.ilms.model.Course;
import com.geniusgordon.ilms.model.CourseList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gordon on 9/28/15.
 */
public class CourseListRequest extends BaseRequest<CourseList> {
    final static String URL = "http://lms.nthu.edu.tw/home.php";
    final static String LOG_TAG = "CourseListRequest";

    public CourseListRequest(Response.Listener<CourseList> listener, Response.ErrorListener errorListener) {
        super(Request.Method.GET, URL, listener, errorListener);
    }

    @Override
    protected CourseList parseResponseHtml(String responseHtml) {
        CourseList courseList = new CourseList();
        List<Course> courses = new ArrayList<Course>();
        Document document = Jsoup.parse(responseHtml);

        if (responseHtml.contains("權限不足"))
            return null;

        String semester = document.select("#left > div:nth-child(1) > div.mnuBody > div.hint").html();
        Log.d(LOG_TAG, "semester: " + semester);
        Elements elements = document.select(".mnuItem a");
        for (Element element: elements) {
            Log.d(LOG_TAG, element.html());
            //    Course course = new Course();
            //    course.setId();
            String[] hrefSplit = element.attr("href").split("/");
            if (hrefSplit.length == 3 && hrefSplit[1].equals("course")) {
                String id = hrefSplit[2];
                Course course = new Course();
                course.setTitle(element.html());
                course.setId(Long.parseLong(id));
                courses.add(course);
                //Log.d(LOG_TAG, course.getChi_title());
                //Log.d(LOG_TAG, course.getEng_title());
                //Log.d(LOG_TAG, String.valueOf(course.getId()));
            }
        }
        courseList.setSemester(semester);
        courseList.setCourses(courses);
        return courseList;
    }
}
