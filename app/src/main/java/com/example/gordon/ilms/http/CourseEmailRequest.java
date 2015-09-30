package com.example.gordon.ilms.http;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.example.gordon.ilms.model.Course;
import com.example.gordon.ilms.model.CourseEmail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * Created by gordon on 9/29/15.
 */
public class CourseEmailRequest extends BaseRequest<CourseEmail> {

    final static String URL = "http://lms.nthu.edu.tw/course/%s";
    final static String LOG_TAG = "CourseEmailRequest";

    public CourseEmailRequest(Course course, Response.Listener<CourseEmail> listener, Response.ErrorListener errorListener) {
        super(Method.GET, String.format(URL, course.getId()), listener, errorListener);
    }

    @Override
    protected Response<CourseEmail> parseNetworkResponse(NetworkResponse response) {
        CourseEmail courseEmail = new CourseEmail();
        String responseHtml = new String(response.data);
        Document document = Jsoup.parse(responseHtml);

        String courseName = document.select("#main div.infoPath a:first-child").text();
        courseEmail.setCourseName(courseName);

        Log.d(LOG_TAG, courseName);

        Element e1 = document.select("#menu > div.box").last().select("div.boxBody > div:nth-child(5)").first();
        Element e2 = document.select("#menu > div.box").last().select("div.boxBody > div:nth-child(6)").first();

        CourseEmail.Email email1 = new CourseEmail.Email();
        email1.setName(e1.text().split(":")[1].trim());
        email1.setEmail(e1.select("img").attr("title").trim());
        email1.setIsProfessor(true);

        Log.d(LOG_TAG, email1.getName());
        Log.d(LOG_TAG, email1.getEmail());

        courseEmail.addEmail(email1);

        String[] names = e2.text().split(":")[1].split(",");

        if (!names[0].trim().equals("無")) {
            for (int i = 0; i < names.length; i++) {
                CourseEmail.Email email = new CourseEmail.Email();
                email.setName(names[i].trim());
                email.setEmail(e2.select("img").eq(i).attr("title").trim());
                email.setIsProfessor(false);
                courseEmail.addEmail(email);

                Log.d(LOG_TAG, email.getName());
                Log.d(LOG_TAG, email.getEmail());
            }
        }

        return Response.success(courseEmail, HttpHeaderParser.parseCacheHeaders(response));
    }
}
